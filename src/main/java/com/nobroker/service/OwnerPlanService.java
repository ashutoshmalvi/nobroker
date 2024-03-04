package com.nobroker.service;

import com.nobroker.entity.OwnerPlan;
import com.nobroker.repository.OwnerPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class OwnerPlanService {
    @Autowired
    private OwnerPlanRepository ownerPlanRepository;

    public OwnerPlan subscribeOwnerPlan(long userId, int duration) {
        OwnerPlan ownerPlan = new OwnerPlan();
        ownerPlan.setUserId(userId);
        ownerPlan.setSubscriptionActive(true);
        ownerPlan.setSubscriptionActiveDate(LocalDate.now());
        ownerPlan.setSubscriptionExpirationDate(LocalDate.now().plusDays(duration));
        ownerPlan.setNumberOfDays(duration);
        return ownerPlanRepository.save(ownerPlan);
    }

    public OwnerPlan getOwnerPlan(long ownerPlanId) {
        return ownerPlanRepository.findById(ownerPlanId).orElse(null);

    }
}

