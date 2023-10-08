package com.dogaozdemir.politicalspeech.controller;

import com.dogaozdemir.politicalspeech.dto.PoliticalSpeechEvaulationResponse;
import com.dogaozdemir.politicalspeech.service.PoliticalSpeechService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/political-speech")
public class PoliticalSpeechController {

    private final PoliticalSpeechService politicalSpeechService;

    public PoliticalSpeechController(PoliticalSpeechService politicalSpeechService) {
        this.politicalSpeechService = politicalSpeechService;
    }


    @GetMapping("/evaulation")
    public ResponseEntity<PoliticalSpeechEvaulationResponse> getPoliticalSpeechEvaulation(@RequestParam(required  = false)   Integer url1,
                                                                                          @RequestParam(required = false) String url2){

        return ResponseEntity.ok(politicalSpeechService.getPoliticalSpeechEvaulation(url1,url2));
    }
}
