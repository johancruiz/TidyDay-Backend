package com.tidyday.TidyDay.Project.service;

import com.tidyday.TidyDay.Project.modal.PlanType;
import com.tidyday.TidyDay.Project.modal.Subscription;
import com.tidyday.TidyDay.Project.modal.User;
import com.tidyday.TidyDay.Project.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(User user) {
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setSuscriptionStartDate(LocalDate.now());
        subscription.setGetSuscriptionEndDate(LocalDate.now().plusMonths(12));
        subscription.setValid(true);
        subscription.setPlanType(PlanType.FREE);

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getUserSubscription(Long userId) throws Exception {
       Subscription subscription =  subscriptionRepository.findByUserId(userId);
       if (!isValid(subscription)){
           subscription.setPlanType(PlanType.FREE);
           subscription.setGetSuscriptionEndDate(LocalDate.now().plusMonths(12));
           subscription.setSuscriptionStartDate(LocalDate.now());
       }
       return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription upgradeSubscription(Long userId, PlanType planType) {
        Subscription subscription = subscriptionRepository.findByUserId(userId);
        subscription.setPlanType(planType);
        subscription.setSuscriptionStartDate(LocalDate.now());
        if (planType.equals(PlanType.ANNUALLY)){
            subscription.setGetSuscriptionEndDate(LocalDate.now().plusMonths(12));

        } else {
            subscription.setGetSuscriptionEndDate(LocalDate.now().plusMonths(1));
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {
        if (subscription.getPlanType().equals(PlanType.FREE)){
            return true;
        }
        LocalDate endDate = subscription.getGetSuscriptionEndDate();
        LocalDate currentDate = LocalDate.now();

        return endDate.isAfter(currentDate) || endDate.isEqual(currentDate);
    }
}
