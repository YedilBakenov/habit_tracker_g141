package kz.tracker.habbit_tracker.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kz.tracker.habbit_tracker.dto.HabbitDto;
import kz.tracker.habbit_tracker.mapper.HabbitMapper;
import kz.tracker.habbit_tracker.service.HabbitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Habit Tracker", description = "API для работы с трекером привычек")
public class HabbitRestController {

    private final HabbitMapper habbitMapper;
    private final HabbitService habbitService;


    @GetMapping
    public ResponseEntity<List<HabbitDto>> getAllHabits(@RequestParam(required = false) String habitName,
                                                        @RequestParam(required = false) Double duration){
        List<HabbitDto> habbitDtos = habbitMapper.toDtoList(habbitService.getAllHabits(habitName, duration));

        return ResponseEntity.ok(habbitDtos);
    }
}
