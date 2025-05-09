package com.example.be_MrHung.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScheduleTimeDTO {
    @JsonProperty("schedule_id")
    private int scheduleId;
    @JsonProperty("schedule_start")
    private String scheduleStart;
    @JsonProperty("seat_empty")
    private String seatEmpty;

    public ScheduleTimeDTO() {
    }

    public ScheduleTimeDTO(int scheduleId, String scheduleStart, String seatEmpty) {
        this.scheduleId = scheduleId;
        this.scheduleStart = scheduleStart;
        this.seatEmpty = seatEmpty;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(String scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public String getSeatEmpty() {
        return seatEmpty;
    }

    public void setSeatEmpty(String seatEmpty) {
        this.seatEmpty = seatEmpty;
    }
}
