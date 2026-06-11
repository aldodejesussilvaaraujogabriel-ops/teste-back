package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// Importações estáticas obrigatórias para simular as requisições HTTP
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TesteIntegracao {

    private MockMvc mockMvc; 

    @Autowired
    private WebApplicationContext webApplicationContext; 

    @MockitoBean
    private Repository_feed repositoryFeed;

    // Inicializa e monta o simulador de requisições antes de cada caso de teste
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void deveAbrirPaginaDeContatoComSucesso() throws Exception {
        // Simula o acesso à página e valida o status 200 OK e o arquivo HTML de retorno
        mockMvc.perform(get("/contato"))
                .andExpect(status().isOk())
                .andExpect(view().name("contatos"));
    }

    @Test
    public void deveSalvarDadosERedirecionarAoEnviarContato() throws Exception {
        // Cria um retorno falso para simular o banco de dados JPA
        Feed feedSimulado = new Feed();
        feedSimulado.setId(1L);
        Mockito.when(repositoryFeed.save(Mockito.any(Feed.class))).thenReturn(feedSimulado);

        // Dispara uma requisição POST imitando os inputs reais do seu formulário
        mockMvc.perform(post("/contato/enviar")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nome", "Rodrigo Silva")
                .param("email", "rodrigo@email.com")
                .param("mensagem", "Excelente site!")
                .param("avaliacao", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/contato"));

        // Confirma se o método save do banco foi acionado exatamente 1 vez
        Mockito.verify(repositoryFeed, Mockito.times(1)).save(Mockito.any(Feed.class));
    }
}
