package com.rebims.renault.repository;

import com.rebims.renault.entity.Attack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AttackRepository extends JpaRepository<Attack, String> {
    List<Attack> findAttacksByParticipantIdAndAttackDateBetweenOrderByAttackDateDesc(String participantId, Date startDay, Date endDay);
}
