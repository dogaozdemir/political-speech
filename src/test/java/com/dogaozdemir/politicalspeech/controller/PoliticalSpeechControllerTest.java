package com.dogaozdemir.politicalspeech.controller;

import com.dogaozdemir.politicalspeech.dto.PoliticalSpeechEvaulationResponse;
import com.dogaozdemir.politicalspeech.service.PoliticalSpeechService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server.port=0")
@DirtiesContext
class PoliticalSpeechControllerTest {

    @MockBean
    private PoliticalSpeechService politicalSpeechService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetPoliticalSpeechEvaulation_WhenParametersValid_ShouldReturnPoliticalSpeechEvaulationResponse() throws Exception {

        PoliticalSpeechEvaulationResponse expected = new PoliticalSpeechEvaulationResponse("Speaker1","Speaker2","Speaker3");
        mockMvc = MockMvcBuilders.standaloneSetup(new PoliticalSpeechController(politicalSpeechService))
                .build();

        when(politicalSpeechService.getPoliticalSpeechEvaulation(2012,"Topic")).thenReturn(expected);


        mockMvc.perform(get("/v1/api/political-speech/evaulation?url1=2012&url2=Topic")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mostSpeeches").value("Speaker1"))
                .andExpect(jsonPath("$.mostSecurity").value("Speaker2"))
                .andExpect(jsonPath("$.leastWordy").value("Speaker3"));


    }


    @Test
    void testGetPoliticalSpeechEvaulation_WhenParametersNotValid_ShouldReturnPoliticalSpeechEvaulationResponse4() throws Exception {
        // Arrange
        PoliticalSpeechEvaulationResponse expected = new PoliticalSpeechEvaulationResponse(null, null, "Speaker3");

        when(politicalSpeechService.getPoliticalSpeechEvaulation(any(Integer.class), any(String.class))).thenReturn(expected);

        // Act & Assert
        mockMvc.perform(get("/v1/api/political-speech/evaulation")
                        .param("url1", "invalidYear") // Invalid year value
                        .param("url2", "Topic")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }



}