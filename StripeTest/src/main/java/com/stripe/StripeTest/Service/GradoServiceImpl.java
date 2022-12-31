package com.stripe.StripeTest.Service;

import com.stripe.StripeTest.Repository.GradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stripe.StripeTest.model.Grado;

import java.util.Optional;

@Service
public class GradoServiceImpl implements  GradoService{

    private final GradoRepository gradoRepository;

    @Autowired
    public GradoServiceImpl(GradoRepository gradoRepository){
        this.gradoRepository = gradoRepository;
    }

    public Boolean superaLimiteMatriculadosPorGrado(Integer id){
        Optional<Grado> grado = gradoRepository.findById(id);
        return grado.isPresent() ? grado.get().getMatriculados() >= grado.get().getLimite()
                : null;
    }


}
