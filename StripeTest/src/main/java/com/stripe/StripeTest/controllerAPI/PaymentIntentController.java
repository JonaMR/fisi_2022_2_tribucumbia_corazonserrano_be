package com.stripe.StripeTest.controllerAPI;

import com.stripe.Stripe;
import com.stripe.StripeTest.Repository.GradoRepository;
import com.stripe.StripeTest.Repository.MatriculaRepository;
import com.stripe.StripeTest.Service.UserAlumnoService;
import com.stripe.StripeTest.model.*;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin
@RequestMapping("/api/stripe/paymentIntent")
public class PaymentIntentController {


    String apikey = "sk_test_51MCtGOKZHzweQQf9T01Ynb8iqU2gnCOsdbq01NbkxdxDw784CJ1hBSoycyVMZPmpQh7v7BZxN6c79WNCqnvV7kjz00S3eWhxAW";

    private final UserAlumnoService userAlumnoService;

    private final MatriculaRepository matriculaRepository;

    private final GradoRepository gradoRepository;
    @Autowired
    public PaymentIntentController(UserAlumnoService userAlumnoService, MatriculaRepository matriculaRepository,
                                   GradoRepository gradoRepository){
        this.userAlumnoService = userAlumnoService;
        this.matriculaRepository = matriculaRepository;
        this.gradoRepository = gradoRepository;
    }

    @PutMapping("/createpayment/{idAlumno}")
    public ResponseEntity<Matricula> createIntentOfPayment(@PathVariable Integer idAlumno, @RequestBody Card cardUser) throws StripeException {
        //Debería haber una validacion en el back
        Alumno alumno = userAlumnoService.getById(idAlumno);
        Stripe.apiKey = apikey;
        //Agrega los datos de tarjeta
        Map<String, Object> card = new HashMap<>();
        card.put("number", String.valueOf(cardUser.getNumber()));//424242424242424
        card.put("cvc", String.valueOf(cardUser.getCvc()));
        card.put("exp_month", cardUser.getMes_expiracion());
        card.put("exp_year", cardUser.getAnio_expiracion());
        //===================================
        Map<String, Object>  paymentMethod = new HashMap<>();
        paymentMethod.put("type", "card");
        paymentMethod.put("card", card);
        PaymentMethod paymentMethodCreate = PaymentMethod.create(paymentMethod);
        String idPayment = paymentMethodCreate.getId();//Obtenemos el id del metodo de pago (agregar columna)
        List<Object> paymenthMethodTypes = new ArrayList<>();
        paymenthMethodTypes.add("card");
        Map<String, Object> params = new HashMap<>();
        //Buscar el precio de grado en base a la matricula - campo aula
        Matricula optmatricula = matriculaRepository.findByIdAlumno(alumno);
        Aula aula = optmatricula.getIdAula();
        int grado = aula.getGrado();
        Optional<Grado> optgrado = gradoRepository.findById(grado);
        Integer precio = optgrado.get().getPrecio().intValue() * 100;
        params.put("amount", precio);//Precio determinado por el grado (select de tabla grado)
        //==================================
        params.put("currency", "mxn");
        params.put("customer", alumno.getCustomerid());
        params.put("receipt_email", "prish.dominguez@unmsm.edu.pe");
        params.put("payment_method", idPayment);
        params.put("payment_method_types",paymenthMethodTypes);
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        //Confirmar el metodo de pago - a través del ID de paymentIntent
        PaymentIntent paymentIntent1 = PaymentIntent.retrieve(paymentIntent.getId());
        Map<String, Object> params2 = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        String prueba = "hola_mundo";
        //Confirmar el pago
        PaymentIntent updatedPaymentIntent = paymentIntent.confirm(params2);
        //Update de la matricula
        optmatricula.setEstado("PAGADO");
        Matricula mat = userAlumnoService.update(optmatricula);
        //===============================
        return new ResponseEntity<>(mat, HttpStatus.ACCEPTED);
    }

}
