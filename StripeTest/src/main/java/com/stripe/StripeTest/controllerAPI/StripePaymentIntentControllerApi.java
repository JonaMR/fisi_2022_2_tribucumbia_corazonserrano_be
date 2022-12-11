package com.stripe.StripeTest.controllerAPI;

import com.stripe.Stripe;
import com.stripe.StripeTest.Service.StripeCustomer;
import com.stripe.StripeTest.model.CustomerData;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/stripe/paymentIntent")
public class StripePaymentIntentControllerApi {

    @Value("${stripe.apikey}")
    String apikey;

    private final StripeCustomer stripeCustomer;

    @Autowired
    public StripePaymentIntentControllerApi(StripeCustomer stripeCustomer){
        this.stripeCustomer = stripeCustomer;
    }

    @PutMapping("/createpayment/{id}")
    public ResponseEntity<CustomerData> createIntentOfPayment(@PathVariable Long id) throws StripeException {
        CustomerData customerData = stripeCustomer.getById(id);
        Stripe.apiKey = apikey;
        //Agrega los datos de tarjeta
        Map<String, Object> card = new HashMap<>();
        card.put("number", "4242424242424242");
        card.put("cvc","123");
        card.put("exp_month", 12);
        card.put("exp_year", 2023);
        Map<String, Object>  paymentMethod = new HashMap<>();
        paymentMethod.put("type", "card");
        paymentMethod.put("card", card);
        PaymentMethod paymentMethodCreate = PaymentMethod.create(paymentMethod);
        String idPayment = paymentMethodCreate.getId();//Obtenemos el id del metodo de pago (agregar columna)
        List<Object> paymenthMethodTypes = new ArrayList<>();
        paymenthMethodTypes.add("card");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", 23000);
        params.put("currency", "mxn");
        params.put("customer", customerData.getCustomerId());
        params.put("receipt_email", "prish.dominguez@unmsm.edu.pe");
        params.put("payment_method", idPayment);
        params.put("payment_method_types",paymenthMethodTypes);
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        //Confirmar el metodo de pago - a través del ID de paymentIntent
        PaymentIntent paymentIntent1 = PaymentIntent.retrieve(paymentIntent.getId());
        Map<String, Object> params2 = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        String prueba = "hola";
        PaymentIntent updatedPaymentIntent = paymentIntent.confirm(params2);
        return new ResponseEntity<>(customerData, HttpStatus.ACCEPTED);
    }

}
