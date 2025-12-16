package kz.tracker.habbit_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "habbits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Habbit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String meta;

    private String habbitName;

    private double duration;

    private LocalDate createdAt;

    private LocalDate updatedAt;


    @PrePersist
    public void created(){
        createdAt = LocalDate.now();
    }

    @PreUpdate
    public void updated(){
        updatedAt = LocalDate.now();
    }
}
