package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ExtendWith(MockitoExtension.class) // 1. Inicializa o ambiente isolado do Mockito (sem Spring)
public class ControlerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private Repository_feed repositoryFeed; // 2. Cria um dublê puro do seu repositório

    @InjectMocks
    private Controler controler; // 3. Instancia o seu controlador injetando o mock acima dentro dele

    @BeforeEach
    public void setup() {
        // 4. Configura o MockMvc focado exclusivamente no seu Controller instanciado acima
        this.mockMvc = MockMvcBuilders.standaloneSetup(controler).build();
    }

    @Test
    public void deveAbrirPaginaDeContatoComSucesso() throws Exception {
        // Testa se a rota GET "/contato" processa corretamente e retorna o nome da View correspondente
        mockMvc.perform(get("/contato"))
                .andExpect(status().isOk())
                .andExpect(view().name("contatos"));
    }

    @Test
    public void deveSalvarDadosERedirecionarAoEnviarContato() throws Exception {
        // Simulação do comportamento do mock
        Feed feedMockado = new Feed();
        Mockito.when(repositoryFeed.save(Mockito.any(Feed.class))).thenReturn(feedMockado);

        // Executa a chamada POST passando parâmetros simulados do formulário HTML
        mockMvc.perform(post("/contato/enviar")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nome", "Lucas Souza")
                .param("email", "lucas@email.com")
                .param("mensagem", "Mensagem enviada via Teste Unitário!")
                .param("avaliacao", "4"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/contato"));

        // Garante que o método do repositório foi acionado uma vez
        Mockito.verify(repositoryFeed, Mockito.times(1)).save(Mockito.any(Feed.class));
    }
}