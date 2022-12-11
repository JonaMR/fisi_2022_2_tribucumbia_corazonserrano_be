package com.stripe.StripeTest.controller;

import com.stripe.StripeTest.Service.StripeCustomer;
import com.stripe.StripeTest.model.CustomerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/eduapp/users")
public class StripePaymentController {

    @Value("${stripe.apikey}")
    String apikey;

    private final StripeCustomer stripeCustomer;

    @Autowired
    public StripePaymentController(StripeCustomer stripeCustomer){
        this.stripeCustomer = stripeCustomer;
    }

    @GetMapping
    public ResponseEntity<List<CustomerData>> listAll(){
        return ResponseEntity.ok(stripeCustomer.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerData> getById(@PathVariable Long id){
        return ResponseEntity.ok(stripeCustomer.getById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerData> saveCustomer(@RequestBody CustomerData customerData){
        CustomerData customerDatasaved = stripeCustomer.save(customerData);
        return new ResponseEntity<>(customerDatasaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerData> updateCustomer(@PathVariable Long id, @RequestBody CustomerData customerData){
        customerData.setId(id);
        customerData = stripeCustomer.update(customerData);
        return ResponseEntity.ok(customerData);
    }

    @RequestMapping("/")
    public String index(){
        return "hello "+apikey;
    }
}