package com.tidyday.TidyDay.Project.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate suscriptionStartDate;
    private LocalDate getSuscriptionEndDate;
    private PlanType planType;
    private boolean isValid;

    @OneToOne
    private User user;

}
