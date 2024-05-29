package com.tidyday.TidyDay.Project.repository;

import com.tidyday.TidyDay.Project.modal.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByUserId(Long userId);
}
