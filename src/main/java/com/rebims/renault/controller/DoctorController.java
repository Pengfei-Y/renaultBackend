package com.rebims.renault.controller;

import cn.hutool.core.util.StrUtil;
import com.rebims.renault.dto.AttackListDTO;
import com.rebims.renault.entity.Attack;
import com.rebims.renault.entity.Doctor;
import com.rebims.renault.exception.RenaultException;
import com.rebims.renault.service.DoctorService;
import com.rebims.renault.utils.Result;
import com.rebims.renault.utils.ResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/login")
    public Result<Doctor> login(@RequestBody @Valid Doctor doctor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RenaultException(bindingResult.getFieldError().getDefaultMessage());
        }
        Doctor login = doctorService.login(doctor);
        return ResultUtil.success(login);
    }

    @GetMapping("/attackList")
    public Result<List<AttackListDTO>> attackList() {
        List<AttackListDTO> attackListDTOList = doctorService.attackList();
        return ResultUtil.success(attackListDTOList);
    }

    @GetMapping("/findAttacksByParticipantId")
    public Result<List<Attack>> findAttackLists(String participantId) {
        if (StrUtil.isEmpty(participantId)) {
            throw new RenaultException("参与不能为空");
        }
        List<Attack> attackList = doctorService.findAttackListsByParticipantId(participantId);
        return ResultUtil.success(attackList);
    }
}
