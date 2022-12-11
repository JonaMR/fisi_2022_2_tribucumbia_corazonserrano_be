package com.stripe.StripeTest.Service;

import com.stripe.StripeTest.model.CustomerData;
import java.util.List;
public interface StripeCustomer {

    List<CustomerData> listAll();

    CustomerData getById(Long Id);

    CustomerData save(CustomerData customerData);

    CustomerData update(CustomerData customerData);

    boolean existsById(Long id);

    void delete(Long id);
}
