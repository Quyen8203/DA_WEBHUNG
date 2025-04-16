package com.example.be_MrHung.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class MovieDTO {
    @JsonProperty("format_film")
    private String formatFilm;
    @JsonProperty("format_data")
    private ArrayList<ScheduleTimeDTO> formatData;

    public MovieDTO() {
    }

    public MovieDTO(String formatFilm, ArrayList<ScheduleTimeDTO> formatData) {
        this.formatFilm = formatFilm;
        this.formatData = formatData;
    }

    public String getFormatFilm() {
        return formatFilm;
    }

    public void setFormatFilm(String formatFilm) {
        this.formatFilm = formatFilm;
    }

    public ArrayList<ScheduleTimeDTO> getFormatData() {
        return formatData;
    }

    public void setFormatData(ArrayList<ScheduleTimeDTO> formatData) {
        this.formatData = formatData;
    }
}
