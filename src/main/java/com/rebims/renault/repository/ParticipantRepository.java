package com.rebims.renault.repository;

import com.rebims.renault.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, String> {
    Participant findParticipantByDeviceUuid(String deviceUuid);
}
