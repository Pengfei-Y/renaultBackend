package com.rebims.renault.controller;

import com.rebims.renault.entity.Participant;
import com.rebims.renault.exception.RenaultException;
import com.rebims.renault.service.ParticipantService;
import com.rebims.renault.utils.Result;
import com.rebims.renault.utils.ResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participant")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @PostMapping("/save")
    public Result<Participant> saveParticipant(@RequestBody @Valid Participant participant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RenaultException(bindingResult.getFieldError().getDefaultMessage());
        }
        Participant save = participantService.saveParticipant(participant);
        return ResultUtil.success(save);
    }
    @GetMapping("/findByDevice")
    public Result<Participant> findParticipant(@RequestBody Participant participant) {
        if (participant.getDeviceUuid() == null || "".equals(participant.getDeviceUuid())) {
            throw new RenaultException("Device uuid cannot be null");
        }
        Participant find = participantService.findParticipantByDeviceId(participant.getDeviceUuid());
        return ResultUtil.success(find);
    }
}
