package kz.tracker.habbit_tracker.repository;

import kz.tracker.habbit_tracker.model.Habbit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabbitRepository extends JpaRepository<Habbit, Integer> {
}
