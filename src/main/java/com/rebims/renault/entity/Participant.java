package com.rebims.renault.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class Participant {
    @Id
    private String participantId;
    @NotEmpty(message = "username cannot be null")
    private String username;
    @NotEmpty(message = "device uuid cannot be null")
    private String deviceUuid;
}
