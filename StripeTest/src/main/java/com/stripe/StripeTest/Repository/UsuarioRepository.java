package com.stripe.StripeTest.Repository;

import com.stripe.StripeTest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}