package com.rebims.renault.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.rebims.renault.dto.AttackDTO;
import com.rebims.renault.entity.Attack;
import com.rebims.renault.exception.RenaultException;
import com.rebims.renault.service.AttackService;
import com.rebims.renault.utils.Result;
import com.rebims.renault.utils.ResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attack")
public class AttackController {
    @Autowired
    private AttackService attackService;

    @PostMapping("/save")
    public Result<Attack> saveAttack(@RequestBody @Valid Attack attack, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RenaultException(bindingResult.getFieldError().getDefaultMessage());
        }
        Attack save = attackService.save(attack);
        return ResultUtil.success(save);
    }
    @PostMapping("/edit")
    public Result<Attack> editAttack(@RequestBody @Valid Attack attack, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RenaultException(bindingResult.getFieldError().getDefaultMessage());
        }
        Attack save = attackService.edit(attack);
        return ResultUtil.success(save);
    }

    @GetMapping("/findByDate")
    public Result<List<Attack>> findTodayAttack(AttackDTO attack) {
        if (attack.getParticipantId() == null || "".equals(attack.getParticipantId())) {
            throw new RenaultException("participant cannot be null");
        }
        if (attack.getAttackDate() == null) {
            attack.setAttackDate(DateUtil.date());
        }
        List<Attack> attackList = attackService.findToday(attack);
        return ResultUtil.success(attackList);
    }
    @GetMapping("/findSevenDay")
    public Result<Map<String, Integer>> findSevenAttack(AttackDTO attack) {
        if (attack.getParticipantId() == null || "".equals(attack.getParticipantId())) {
            throw new RenaultException("participant cannot be null");
        }
        if (attack.getAttackDate() == null) {
            attack.setAttackDate(DateUtil.date());
        }
        Map<String, Integer> result = attackService.findSeven(attack);
        return ResultUtil.success(result);
    }

}
