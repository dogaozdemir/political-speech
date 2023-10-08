package com.dogaozdemir.politicalspeech.dto;

public class PoliticalSpeechMostSpeechResponseDto {
        private String speaker;
        private Integer speechCount;

        public String getSpeaker() {
                return speaker;
        }

        public void setSpeaker(String speaker) {
                this.speaker = speaker;
        }

        public Integer getSpeechCount() {
                return speechCount;
        }

        public void setSpeechCount(Integer speechCount) {
                this.speechCount = speechCount;
        }



        public PoliticalSpeechMostSpeechResponseDto(String speaker, Integer speechCount) {
                this.speaker = speaker;
                this.speechCount = speechCount;
        }
}
