package kz.tracker.habbit_tracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "schemaHabitDto")
public class HabbitDto {

    private Integer id;

    private String habbitName;

    private double duration;

    private LocalDate addTime;

    private LocalDate updateTime;

}
