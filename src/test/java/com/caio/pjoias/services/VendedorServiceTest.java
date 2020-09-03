package com.caio.pjoias.services;

import com.caio.pjoias.builders.VendedorBuilder;
import com.caio.pjoias.models.Vendedor;
import com.caio.pjoias.repositories.VendedorRepository;
import com.caio.pjoias.utils.PasswordUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class VendedorServiceTest {
    @InjectMocks
    private VendedorService vendedorService;

    @Mock
    private VendedorRepository vendedorRepository;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void devePersistirNovoVendedor() {
        //cenario
        var vendedor = VendedorBuilder.umVendedor().agora();
        when(this.vendedorRepository.save(any(Vendedor.class)))
                                    .thenReturn(vendedor);

        //acao
        var resultado = this.vendedorService.persistirNovo(vendedor);

        //verificacao
        if(resultado.equals(vendedor)) {
            Mockito.verify(this.vendedorRepository).save(vendedor);
            return;
        }

        fail("Expected: \n" + vendedor.toString() + "\nbe equal to: \n" + resultado.toString() + "\nBut was not!");
    }
}
