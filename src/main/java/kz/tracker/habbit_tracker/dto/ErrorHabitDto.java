package kz.tracker.habbit_tracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "schemaErrorDto")
public class ErrorHabitDto {

    @Schema(example = "400", description = "Код ошибки")
    private int status;

    @Schema(example = "Неверный формат данных", description = "Краткое описание ошибки")
    private String title;

    @Schema(example = "Поле id неверное", description = "Подробности ошибки")
    private String details;
}
