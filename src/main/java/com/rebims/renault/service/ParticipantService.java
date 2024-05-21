package com.rebims.renault.service;

import com.rebims.renault.entity.Participant;
import com.rebims.renault.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository repository;

    public Participant saveParticipant(Participant participant) {
        participant.setParticipantId(UUID.randomUUID().toString());
        Participant save = repository.save(participant);
        return save;
    }

    public Participant findParticipantByDeviceId(String deviceUuid) {
        Participant participant = repository.findParticipantByDeviceUuid(deviceUuid);
        return participant;
    }
}
