package com.stripe.StripeTest.Service;

import com.stripe.Stripe;
import com.stripe.StripeTest.Repository.CustomerApiRepository;
import com.stripe.StripeTest.model.CustomerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StripeCustomerImpl implements StripeCustomer{

    @Value("${stripe.apikey}")
    String API_KEY_STRIPE;
    private final CustomerApiRepository customerApiRepository;

    @Autowired
    public StripeCustomerImpl(CustomerApiRepository customerApiRepository){
        Stripe.apiKey = API_KEY_STRIPE;
        this.customerApiRepository = customerApiRepository;
    }

    @Override
    public List<CustomerData> listAll(){
        Stripe.apiKey = API_KEY_STRIPE;
        return customerApiRepository.findAll();
    }
    @Override
    public CustomerData getById(Long Id){
        Stripe.apiKey = API_KEY_STRIPE;
        return customerApiRepository.findById(Id).orElseThrow(()-> new NoSuchElementException("No existe cliente con id: " + String.valueOf(Id)));
    }
    @Override
    public CustomerData save(CustomerData customerData){
        Stripe.apiKey = API_KEY_STRIPE;
        boolean exists = customerData.getId()!=null && customerApiRepository.existsById(customerData.getId());
        if(exists){
            throw new IllegalArgumentException("Cliente con id: "+ customerData.getId() + " ya existe");
        }
        return customerApiRepository.save(customerData);
    }
    @Override
    public CustomerData update(CustomerData customerData){
        Stripe.apiKey = API_KEY_STRIPE;
        boolean exists = customerApiRepository.existsById(customerData.getId());
        if(!exists){
            throw new NoSuchElementException("Cliente con id: "+ customerData.getId() +" no existe");
        }
        return customerApiRepository.save(customerData);
    }

    @Override
    public void delete(Long id){
        Stripe.apiKey = API_KEY_STRIPE;
        boolean exists = customerApiRepository.existsById(id);
        if(!exists){
            throw new NoSuchElementException("Cliente con id: "+ id +" no existe");
        }
        customerApiRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id){
        boolean exists = customerApiRepository.existsById(id);
        if(!exists){
            throw new NoSuchElementException("Cliente con id: "+ id +" no existe");
        }
        return exists;
    }

}
