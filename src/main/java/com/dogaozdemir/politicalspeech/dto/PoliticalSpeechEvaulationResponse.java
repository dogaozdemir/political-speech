package com.dogaozdemir.politicalspeech.dto;

public class PoliticalSpeechEvaulationResponse {

    private String mostSpeeches;
    private String mostSecurity;
    private String leastWordy;

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
