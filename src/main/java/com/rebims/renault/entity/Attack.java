package com.rebims.renault.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
public class Attack {
    @Id
    private String attackId;
    @NotEmpty(message = "participant cannot be empty")
    private String participantId;
    @NotNull(message = "data cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date attackDate;
    @NotEmpty(message = "location cannot be empty")
    private String location;
}
