package kz.tracker.habbit_tracker.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabbitDto {

    private int id;

    private String habbitName;

    private double duration;

    private LocalDate createdAt;

    private LocalDate updatedAt;

}
