package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.PlanType;
import com.tidyday.TidyDay.Project.modal.Subscription;
import com.tidyday.TidyDay.Project.modal.User;

public interface SubscriptionService {
    Subscription createSubscription(User user);
    Subscription getUserSubscription(Long userId) throws Exception;
    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}
