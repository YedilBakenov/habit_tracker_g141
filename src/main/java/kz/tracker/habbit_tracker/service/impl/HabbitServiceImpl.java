package kz.tracker.habbit_tracker.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kz.tracker.habbit_tracker.model.Habbit;
import kz.tracker.habbit_tracker.repository.HabbitRepository;
import kz.tracker.habbit_tracker.service.HabbitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabbitServiceImpl implements HabbitService {

    private final HabbitRepository habbitRepository;
    private final EntityManager entityManager;

    @Override
    public List<Habbit> getAllHabits(String habbitName, Double duration) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Habbit> query = cb.createQuery(Habbit.class);
        Root<Habbit> root = query.from(Habbit.class);

        List<Predicate> predicates = new ArrayList<>();

        if(habbitName!=null && !habbitName.isEmpty()){
            predicates.add(cb.like(cb.lower(root.get("habbitName")), habbitName));
        }

        if(duration!=null){
            predicates.add(cb.equal(root.get("duration"), duration));
        }

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));


        return entityManager.createQuery(query).getResultList();
    }
}
