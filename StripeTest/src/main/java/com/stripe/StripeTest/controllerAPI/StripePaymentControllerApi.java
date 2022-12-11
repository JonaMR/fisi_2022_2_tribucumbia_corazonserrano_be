package com.stripe.StripeTest.controllerAPI;

import com.stripe.Stripe;
import com.stripe.StripeTest.Service.StripeCustomer;
import com.stripe.StripeTest.model.CustomerData;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/stripe/customer")
public class StripePaymentControllerApi {
    @Value("${stripe.apikey}")
    String apikey;

    private final StripeCustomer stripeCustomer;

    @Autowired
    public StripePaymentControllerApi(StripeCustomer stripeCustomer){
        this.stripeCustomer = stripeCustomer;
    }

    @PutMapping("/createCustomer/{id}")
    public ResponseEntity<CustomerData> createCustomer(@PathVariable Long id) throws StripeException {
        CustomerData customerData = stripeCustomer.getById(id);
        //Validar si ya estaba creado en stripe a traves del campo (nuevo metodo de servicio).
        Stripe.apiKey = apikey;
        Map<String, Object> params = new HashMap<>();
        params.put("name",customerData.getName());
        params.put("email",customerData.getEmail());
        params.put("payment_method","pm_card_visa");
        Customer customerStripe = Customer.create(params);
        //Campos a agregar del cliente luego de creado
        customerData.setCustomerId(customerStripe.getId());
        customerData.accessToStripe();//True el campo isStripeCustomer
        stripeCustomer.update(customerData);//Actualizamos dato CustomerId - se genera una vez creado en stripe
        return new ResponseEntity<>(customerData, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllCustomers")
    public List<CustomerData> getAllCustomers() throws StripeException{
        Stripe.apiKey = apikey;
        Map<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        CustomerCollection customers = Customer.list(params);
        List<CustomerData> allCustomer = new ArrayList<CustomerData>();
        for (int i = 0; i < customers.getData().size(); i++) {
            CustomerData customerData = new CustomerData();
            customerData.setCustomerId(customers.getData().get(i).getId());
            customerData.setName(customers.getData().get(i).getName());
            customerData.setEmail(customers.getData().get(i).getEmail());
            allCustomer.add(customerData);
        }
        return allCustomer;
    }


}
