package kz.tracker.habbit_tracker.service;

import kz.tracker.habbit_tracker.model.Habbit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HabbitService {
    List<Habbit> getAllHabits(String habbitName, Double duration);
}
