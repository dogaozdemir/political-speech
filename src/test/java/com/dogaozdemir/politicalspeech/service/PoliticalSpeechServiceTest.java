package com.dogaozdemir.politicalspeech.service;

import com.dogaozdemir.politicalspeech.dto.PoliticalSpeechEvaulationResponse;
import com.dogaozdemir.politicalspeech.model.PoliticalSpeech;
import com.dogaozdemir.politicalspeech.repository.EvaulationRepository;
import com.dogaozdemir.politicalspeech.repository.PoliticalSpeechRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;



class PoliticalSpeechServiceTest {

    @Mock
    private PoliticalSpeechRepository politicalSpeechRepository;

    @InjectMocks
    PoliticalSpeechService politicalSpeechService;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testGetPoliticalSpeechEvaulationWithValidUrls() {

        when(politicalSpeechRepository.mostSpeechByYear(any())).thenReturn(Optional.of(new EvaulationRepository() {
            @Override
            public String getSpeaker() {
                return "Speaker1";
            }

            @Override
            public Integer getCount() {
                return 100;
            }
        }));

        when(politicalSpeechRepository.mostSpeechByTopic(any())).thenReturn(Optional.of(new EvaulationRepository() {
            @Override
            public String getSpeaker() {
                return "Speaker2";
            }

            @Override
            public Integer getCount() {
                return 200;
            }
        }));
        when(politicalSpeechRepository.findMinWordsRow()).thenReturn( PoliticalSpeech.builder()
                .speaker("Speaker3").topic( "Topic3").date( LocalDate.now()).words(1000).build());

        PoliticalSpeechEvaulationResponse response=politicalSpeechService.getPoliticalSpeechEvaulation(2012,"Topic1");

        assertEquals("Speaker1", response.getMostSpeeches());
        assertEquals("Speaker2", response.getMostSecurity());
        assertEquals("Speaker3",response.getLeastWordy());
        
    }

    @Test
    void testGetPoliticalSpeechEvaulationWithNullUrls() {
        when(politicalSpeechRepository.findMinWordsRow()).thenReturn(PoliticalSpeech.builder()
                .speaker("Speaker3").topic( "Topic3").date( LocalDate.now()).words(1000).build());

        PoliticalSpeechEvaulationResponse response = politicalSpeechService.getPoliticalSpeechEvaulation(null,null);

        assertEquals("Speaker3",response.getLeastWordy());
        assertNull(response.getMostSpeeches());
        assertNull(response.getMostSecurity());
    }
}