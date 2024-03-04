package com.nobroker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerPlan {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="owner_plan_id")
    private long ownerPlanId;
    @Column(name="user_id", unique= true)
    private long userId;
    @Column(name="subscription_active")
    private boolean subscriptionActive;
    @Column(name="subscription_active_date")
    private LocalDate subscriptionActiveDate;
    @Column(name="subscription_expiration_date")
    private LocalDate subscriptionExpirationDate;
    private int numberOfDays;


}