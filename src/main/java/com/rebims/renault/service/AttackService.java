package com.rebims.renault.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.rebims.renault.dto.AttackDTO;
import com.rebims.renault.entity.Attack;
import com.rebims.renault.exception.RenaultException;
import com.rebims.renault.repository.AttackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AttackService {
    @Autowired
    private AttackRepository attackRepository;

    public Attack save(Attack attack) {
        attack.setAttackId(UUID.randomUUID().toString());
        Attack save = attackRepository.save(attack);
        return save;
    }

    public List<Attack> findToday(AttackDTO attack) {
        Date startOfDay = DateUtil.beginOfDay(attack.getAttackDate());
        DateTime endOfDay = DateUtil.offsetDay(startOfDay, 1);
        List<Attack> attackList = attackRepository.findAttacksByParticipantIdAndAttackDateBetweenOrderByAttackDateDesc(attack.getParticipantId(), startOfDay, endOfDay);
        return attackList;
    }

    public Map<String, Integer> findSeven(AttackDTO attack) {
        Date baseDay = DateUtil.beginOfDay(attack.getAttackDate());
        DateTime startOfDay = DateUtil.offsetDay(baseDay, -6);
        DateTime endOfDay = DateUtil.offsetDay(baseDay, 1);
        List<Attack> attackList = attackRepository.findAttacksByParticipantIdAndAttackDateBetweenOrderByAttackDateDesc(attack.getParticipantId(), startOfDay, endOfDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // deal with Map
        Map<String, Integer> result = new TreeMap<>();
        for (int i = 1; i <= 6; i++) {
            DateTime key = DateUtil.offsetDay(baseDay, -i);
            result.put(sdf.format(key),0);
        }
        result.put(sdf.format(baseDay),0);
        for (Attack count : attackList) {
            String key = sdf.format(count.getAttackDate());
            result.put(key, result.get(key) + 1);
        }
        return result;
    }

    public Attack edit(Attack attack) {
        Optional<Attack> op = attackRepository.findById(attack.getAttackId());
        if (!op.isPresent()) {
            throw new RenaultException("record doesn't exist");
        }
        Attack find = op.get();
        BeanUtil.copyProperties(attack, find);
        Attack edit = attackRepository.save(find);
        return edit;
    }

    public List<Attack> findByParticipantId(String participantId) {
        if (StrUtil.isEmpty(participantId)) {
            throw new RenaultException("participant cannot be null");
        }
        List<Attack> attackList = attackRepository.findAttacksByParticipantIdOrderByAttackDateDesc(participantId);
        return attackList;
    }
}
