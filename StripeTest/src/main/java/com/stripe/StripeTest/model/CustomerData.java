package com.stripe.StripeTest.model;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;

@Entity
public class CustomerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    public String email;
    public String customerId;
    @Column(nullable = false, columnDefinition = "boolean default false")
    public boolean isStripeCustomer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public boolean isStripeCustomer() {
        return isStripeCustomer;
    }

    public void setStripeCustomer(boolean stripeCustomer) {
        isStripeCustomer = stripeCustomer;
    }

    public void accessToStripe(){
        this.isStripeCustomer = true;
    }
}
