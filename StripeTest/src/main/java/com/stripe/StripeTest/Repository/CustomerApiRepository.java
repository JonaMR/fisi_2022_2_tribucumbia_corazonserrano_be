package com.stripe.StripeTest.Repository;

import com.stripe.StripeTest.model.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerApiRepository extends JpaRepository<CustomerData, Long> {

}
