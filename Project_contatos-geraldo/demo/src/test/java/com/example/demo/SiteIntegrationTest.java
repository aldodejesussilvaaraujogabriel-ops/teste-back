package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ActiveProfiles("test") 
public class SiteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void usuarioNaoAutenticadoDeveSerRedirecionadoParaLogin() throws Exception {
        // A letra 'a' que estava aqui foi removida para corrigir o erro de sintaxe
        mockMvc.perform(get("/"))
               .andExpect(status().is3xxRedirection()) 
               .andExpect(redirectedUrlPattern("**/login")); 
    }

    @Test
    @WithMockUser(username = "usuario@teste.com", roles = "USER") 
    public void usuarioAutenticadoDeveAcessarPaginaInicialComSucesso() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk()) 
               .andExpect(view().name("index")); 
    }

    @Test
    @WithMockUser(username = "admin@teste.com", roles = "ADMIN")
    public void deveEnviarFormularioComSucessoEvitandoErroDeCSRF() throws Exception {
        mockMvc.perform(post("/salvar") 
                .param("nome", "Item Teste")
                .with(csrf()))
               .andExpect(status().is3xxRedirection());
    }
}