package com.dogaozdemir.politicalspeech.service;

import com.dogaozdemir.politicalspeech.dto.PoliticalSpeechEvaulationResponse;

import com.dogaozdemir.politicalspeech.model.PoliticalSpeech;
import com.dogaozdemir.politicalspeech.repository.EvaulationRepository;
import com.dogaozdemir.politicalspeech.repository.PoliticalSpeechRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PoliticalSpeechService {



    private static final Logger LOG = LoggerFactory.getLogger(PoliticalSpeechService.class);

    private final PoliticalSpeechRepository politicalSpeechRepository;

    public PoliticalSpeechService(PoliticalSpeechRepository politicalSpeechRepository) {

        this.politicalSpeechRepository = politicalSpeechRepository;
    }

    @PostConstruct
    public void initPoliticalSpeechData() throws IOException {


        CSVReader  reader = new CSVReader(new FileReader("data.csv"),';', '\'', 1);

        LocalDate localDate;
        String[] csvRecord = null;
        List<PoliticalSpeech> politicalSpeechList = new ArrayList<>();

        try{
            while ((csvRecord = reader.readNext()) != null) {
                localDate = LocalDate.parse(csvRecord[2]);
                PoliticalSpeech politicalSpeech = PoliticalSpeech.builder()
                        .speaker(csvRecord[0])
                        .topic(csvRecord[1])
                        .date(localDate)
                        .words(Integer.parseInt(csvRecord[3]))
                        .build();
                politicalSpeechList.add(politicalSpeech);

            }
        }catch (Exception e){
            LOG.error("Something went wrong in initPoliticalSpeechData function: " + e.getMessage());
        }finally {

           politicalSpeechRepository.saveAll(politicalSpeechList);
            reader.close();
        }

    }


    public PoliticalSpeechEvaulationResponse getPoliticalSpeechEvaulation(Integer url1, String url2) {

        PoliticalSpeechEvaulationResponse politicalSpeechEvaulationResponse = new PoliticalSpeechEvaulationResponse();
        if(null !=url1 ){
            getMostSpeechByYear(url1, politicalSpeechEvaulationResponse);
        }
        if(null != url2){
            getMostSpeechByTopic(url2, politicalSpeechEvaulationResponse);
        }
        politicalSpeechEvaulationResponse.setLeastWordy(politicalSpeechRepository.findMinWordsRow().getSpeaker());
        return politicalSpeechEvaulationResponse;
    }



    private void getMostSpeechByYear(Integer url1, PoliticalSpeechEvaulationResponse politicalSpeechEvaulationResponse) {
        Optional<EvaulationRepository> mostSpeechResponse = politicalSpeechRepository.mostSpeechByYear(url1);
        if(mostSpeechResponse.isPresent()) {
            politicalSpeechEvaulationResponse.setMostSpeeches(mostSpeechResponse.get().getSpeaker());
        }
    }

    private void getMostSpeechByTopic(String url2, PoliticalSpeechEvaulationResponse politicalSpeechEvaulationResponse) {
        Optional<EvaulationRepository> mostSpeechResponse = politicalSpeechRepository.mostSpeechByTopic(url2);
        if(mostSpeechResponse.isPresent()){
            politicalSpeechEvaulationResponse.setMostSecurity(mostSpeechResponse.get().getSpeaker());
        }

    }
}
