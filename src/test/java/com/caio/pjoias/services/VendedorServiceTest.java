package com.caio.pjoias.services;

import com.caio.pjoias.exceptions.VendedorException;
import com.caio.pjoias.models.Vendedor;
import com.caio.pjoias.repositories.VendedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.caio.pjoias.builders.VendedorBuilder.umVendedor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
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
    public void devePersistirNovoVendedor() throws VendedorException {
        //cenario
        var vendedor = umVendedor().agora();
        when(this.vendedorRepository.save(any(Vendedor.class)))
                                    .thenReturn(vendedor);

        //acao
        var resultado = this.vendedorService.persistirNovo(vendedor);

        //verificacao
        if(resultado.equals(vendedor)) {
            verify(this.vendedorRepository).save(vendedor);
            return;
        }

        fail("Expected: \n" + vendedor.toString() + "\nbe equal to: \n" + resultado.toString() + "\nBut was not!");
    }

    @Test
    public void deveListarTodosOsVendedores() {
        //cenario
        List<Vendedor> vendedores = Arrays.asList(umVendedor().agora(),
                                    umVendedor().comUid("uidteste").agora());

        when(this.vendedorRepository.findAll()).thenReturn(vendedores);

        //acao
        this.vendedorService.retornarTodos();

        //verificacao
        verify(this.vendedorRepository, times(1)).findAll();
    }

    @Test
    public void naoDevePersistirVendedoresComMesmoUid() {
        //cenario
        when(this.vendedorRepository.findById(anyString())).thenReturn(Optional.of(umVendedor().agora()));

        try {
            //acao
            this.vendedorService.persistirNovo(umVendedor().agora());
            this.vendedorService.persistirNovo(umVendedor().agora());

            fail("Não deu erro esperado!");
        } catch(VendedorException e) {
            //verificacao
            assertThat(e.getMessage()).isEqualTo("Tente novamente!");
        }
    }
}
