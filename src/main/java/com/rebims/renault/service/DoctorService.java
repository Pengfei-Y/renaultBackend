package com.rebims.renault.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.rebims.renault.dto.AttackListDTO;
import com.rebims.renault.entity.Attack;
import com.rebims.renault.entity.Doctor;
import com.rebims.renault.entity.Participant;
import com.rebims.renault.exception.RenaultException;
import com.rebims.renault.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AttackService attackService;
    @Autowired
    private ParticipantService participantService;

    public Doctor login(Doctor doctor) {
        Doctor find = doctorRepository.findDoctorByUsernameAndPassword(
                doctor.getUsername(),
                doctor.getPassword()
        );
        if (ObjUtil.isNull(find)) {
            throw new RenaultException("username or password error");
        }
        return find;
    }

    public List<Attack> findAttackListsByParticipantId(String participantId) {
        if (StrUtil.isEmpty(participantId)) {
            throw new RenaultException("participant cannot be empty");
        }
        List<Attack> attackList = attackService.findByParticipantId(participantId);
        return attackList;
    }

    public List<AttackListDTO> attackList() {
        List<AttackListDTO> result = new ArrayList<>();
        // search all users
        List<Participant> participantList = participantService.findAllParticipant();
        for (Participant participant : participantList) {
            AttackListDTO attackListDTO = new AttackListDTO();
            // set ID
            attackListDTO.setParticipantId(participant.getParticipantId());
            // set username
            attackListDTO.setUsername(participant.getUsername());
            attackListDTO.setAlert(false);
            // get newest record
            List<Attack> attackList = findAttackListsByParticipantId(participant.getParticipantId());
            if (!attackList.isEmpty()) {
                Attack attack = attackList.get(0);
                attackListDTO.setAttackDate(attack.getAttackDate());
                // get two days before today
                DateTime twoDaysBefore = DateUtil.offsetDay(new Date(), -2);
                int num = DateUtil.compare(twoDaysBefore, attack.getAttackDate());
                if (num == 1) {
                    attackListDTO.setAlert(true);
                }
            }
            result.add(attackListDTO);
        }
        // remove null date
        result.removeIf(attackListDTO -> ObjUtil.isNull(attackListDTO.getAttackDate()));
        // sort
        Collections.sort(result, Comparator.comparing(AttackListDTO::getAttackDate));
        return result;
    }
}
