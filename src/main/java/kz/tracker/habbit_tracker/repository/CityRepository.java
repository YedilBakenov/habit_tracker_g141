package kz.tracker.habbit_tracker.repository;

import kz.tracker.habbit_tracker.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
