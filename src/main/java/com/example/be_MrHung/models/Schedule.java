package com.example.be_MrHung.models;


import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
    public class Schedule {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "schedule_id")
        private int scheduleId;

        @Column(name = "movie_id")
        private int movieId;

        @Column(name = "room_id")
        private int roomId;

        @Column(name = "schedule_date")
        private String scheduleDate;

        @Column(name = "schedule_start")
        private String scheduleStart;

        @Column(name = "schedule_end")
        private String scheduleEnd;



        public Schedule() {
        }

        public Schedule(int movieId, int roomId, String scheduleDate, String scheduleStart, String scheduleEnd) {
            this.movieId = movieId;
            this.roomId = roomId;
            this.scheduleDate = scheduleDate;
            this.scheduleStart = scheduleStart;
            this.scheduleEnd = scheduleEnd;
        }

        public int getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(int scheduleId) {
            this.scheduleId = scheduleId;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public String getScheduleDate() {
            return scheduleDate;
        }

        public void setScheduleDate(String scheduleDate) {
            this.scheduleDate = scheduleDate;
        }

        public String getScheduleStart() {
            return scheduleStart;
        }

        public void setScheduleStart(String scheduleStart) {
            this.scheduleStart = scheduleStart;
        }

        public String getScheduleEnd() {
            return scheduleEnd;
        }

        public void setScheduleEnd(String scheduleEnd) {
            this.scheduleEnd = scheduleEnd;
        }


}


