package com.dogaozdemir.politicalspeech.dto;

public class PoliticalSpeechEvaulationResponse {

    private String mostSpeeches;
    private String mostSecurity;
    private String leastWordy;

    public PoliticalSpeechEvaulationResponse() {
    }

    public PoliticalSpeechEvaulationResponse(String mostSpeeches, String mostSecurity, String leastWordy) {
        this.mostSpeeches = mostSpeeches;
        this.mostSecurity = mostSecurity;
        this.leastWordy = leastWordy;
    }

    public String getMostSpeeches() {
        return mostSpeeches;
    }

    public void setMostSpeeches(String mostSpeeches) {
        this.mostSpeeches = mostSpeeches;
    }

    public String getMostSecurity() {
        return mostSecurity;
    }

    public void setMostSecurity(String mostSecurity) {
        this.mostSecurity = mostSecurity;
    }

    public String getLeastWordy() {
        return leastWordy;
    }

    public void setLeastWordy(String leastWordy) {
        this.leastWordy = leastWordy;
    }

}
