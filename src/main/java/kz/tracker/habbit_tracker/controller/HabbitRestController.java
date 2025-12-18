package kz.tracker.habbit_tracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.tracker.habbit_tracker.dto.ErrorHabitDto;
import kz.tracker.habbit_tracker.dto.HabbitDto;
import kz.tracker.habbit_tracker.mapper.HabbitMapper;
import kz.tracker.habbit_tracker.service.HabbitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tracker")
@Tag(name = "Habit Tracker Controller", description = "API для работы с трекером привычек")
public class HabbitRestController {

    private final HabbitMapper habbitMapper;
    private final HabbitService habbitService;


    @GetMapping(value = "/habits") // localhost:8080/tracker/habits
    @Operation(summary = "Получение всех привычек", description = "Получение всех привычек с базы по параметрам habitName, duration. Данные параметры необязательны.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычки получены", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabbitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples =
                    @ExampleObject(value = """
                            {
                                  "status": 400,
                                  "title": "PROBLEM FORMAT",
                                  "details": "INCORRECT FORMAT"
                            }
                            """),
                            schema = @Schema(implementation = ErrorHabitDto.class)
                    )
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                      {
                                              "status": 500,
                                              "title": "INTERNAL ERROR",
                                              "details": "SERVER FORMAT"
                                      }
                                    """)
                    }, schema = @Schema(implementation = ErrorHabitDto.class))
            })
    })
    public ResponseEntity<List<HabbitDto>> getAllHabits(@RequestParam(required = false) String habitName,
                                                        @RequestParam(required = false) Double duration) {
        List<HabbitDto> habbitDtos = habbitMapper.toDtoList(habbitService.getAllHabits(habitName, duration));

        return ResponseEntity.ok(habbitDtos);
    }

    @GetMapping(value = "/get-habit/{id}")
    @Operation(summary = "Получение привычки", description = "Получение привычки из базы по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычка получена", content = {
                    @Content(mediaType  = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabbitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос данных", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    {
                                     "status": 400,
                                     "title": "PROBLEM FORMAT",
                                     "details": "INCORRECT FORMAT"
                                    }
                                    """)
                    },  schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Привычка не найдена", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": 404,
                                        "title": "HABIT NOT FOUND",
                                        "details": "OBJECT NOT FOUND"
                                    }
                                    """)
                    },  schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                      {
                                              "status": 500,
                                              "title": "INTERNAL ERROR",
                                              "details": "SERVER FORMAT"
                                      }
                                    """)
                    },  schema = @Schema(implementation = ErrorHabitDto.class))
            })
    })
    public ResponseEntity<HabbitDto> getHabitById(@PathVariable int id){
        return ResponseEntity.ok(habbitMapper.toDto(habbitService.getHabitById(id)));
    }


    @PostMapping(value = "/add-habit")
    @Operation(summary = "Добавление привычки", description = "Добавление в базу привычки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычка добавлена", content = {
                    @Content(mediaType  = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabbitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос данных", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    {
                                     "status": 400,
                                     "title": "PROBLEM FORMAT",
                                     "details": "INCORRECT FORMAT"
                                    }
                                    """)
                    },  schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                      {
                                              "status": 500,
                                              "title": "INTERNAL ERROR",
                                              "details": "SERVER FORMAT"
                                      }
                                    """)
                    },  schema = @Schema(implementation = ErrorHabitDto.class))
            })
    })
    public ResponseEntity<HabbitDto> addHabit(@Valid @RequestBody HabbitDto habbitDto){

        habbitService.addHabit(habbitMapper.toModel(habbitDto));

        return ResponseEntity.ok(habbitDto);
    }



    @PutMapping(value = "/update-habit")
    @Operation(summary = "Обновление привычки", description = "Обновление в базе привычки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычка изменена", content = {
                    @Content(mediaType  = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabbitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос данных", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    {
                                     "status": 400,
                                     "title": "PROBLEM FORMAT",
                                     "details": "INCORRECT FORMAT"
                                    }
                                    """)
                    },  schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Привычка не найдена", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": 404,
                                        "title": "HABIT NOT FOUND",
                                        "details": "OBJECT NOT FOUND"
                                    }
                                    """)
                    },  schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                      {
                                              "status": 500,
                                              "title": "INTERNAL ERROR",
                                              "details": "SERVER FORMAT"
                                      }
                                    """)
                    }, schema = @Schema(implementation = ErrorHabitDto.class))
            })
    })
    public ResponseEntity<HabbitDto> updateHabit(@RequestBody HabbitDto habbitDto){
        return ResponseEntity.ok(habbitMapper.toDto(habbitService.updateHabit(habbitMapper.toModel(habbitDto))));
    }


    @DeleteMapping(value = "/delete-habit/{id}")
    @Operation(summary = "Удаление привычки", description = "Обновление в базе привычки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Привычка удалена"),
            @ApiResponse(responseCode = "404", description = "Привычка не найдена", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": 404,
                                        "title": "HABIT NOT FOUND",
                                        "details": "OBJECT NOT FOUND"
                                    }
                                    """)
                    },  schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                      {
                                              "status": 500,
                                              "title": "INTERNAL ERROR",
                                              "details": "SERVER FORMAT"
                                      }
                                    """)
                    }, schema = @Schema(implementation = ErrorHabitDto.class))
            })
    })
    public ResponseEntity<Void> deleteHabit(@PathVariable int id){
        habbitService.deleteHabit(id);
       return ResponseEntity.noContent().build();
    }

}
