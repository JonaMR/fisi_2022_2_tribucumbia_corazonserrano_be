package com.stripe.StripeTest.controllerAPI;

import com.stripe.Stripe;
import com.stripe.StripeTest.Service.UserAlumnoService;
import com.stripe.StripeTest.model.Alumno;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/stripe/user")
public class UserStripeController {

    String apikey = "sk_test_51MCtGOKZHzweQQf9T01Ynb8iqU2gnCOsdbq01NbkxdxDw784CJ1hBSoycyVMZPmpQh7v7BZxN6c79WNCqnvV7kjz00S3eWhxAW";

    private final UserAlumnoService userAlumnoService;

    @Autowired
    public UserStripeController(UserAlumnoService userAlumnoService){
        this.userAlumnoService = userAlumnoService;
    }

    //Creacion de cliente Stripe
    @PutMapping("/createCustomer/{idAlumno}")
    public ResponseEntity<Alumno> createCustomer(@PathVariable Integer idAlumno) throws StripeException {
        Alumno alumno = userAlumnoService.getById(idAlumno);
        //Validar si ya estaba creado en stripe a traves del campo (nuevo metodo de servicio).
        if(alumno.getIsStripeCustomer()){
            //Retornar que no se puede por q ya es usuario
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        }
        Stripe.apiKey = apikey;
        Map<String, Object> params = new HashMap<>();
        params.put("name",alumno.getNombreApoderado());
        params.put("email",alumno.getCorreoApoderado());
        params.put("payment_method","pm_card_visa");
        Customer customerStripe = Customer.create(params);
        //Campos a agregar del cliente luego de creado
        alumno.setCustomerid(customerStripe.getId());
        alumno.setIsStripeCustomer(Boolean.TRUE);//True el campo isStripeCustomer
        userAlumnoService.update(alumno);//Actualizamos dato CustomerId - se genera una vez creado en stripe
        return new ResponseEntity<>(alumno, HttpStatus.ACCEPTED);
    }


}
