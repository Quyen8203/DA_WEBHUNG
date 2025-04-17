package com.example.be_MrHung.models;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
@Entity
@Table(name = "cinemas")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cinema_id")
    private int cinemaId;

    @Column(name = "cinema_name")
    private String cinemaName;

    @Column(name = "cinema_address")
    private String cinemaAddress;

    @Column(name = "city")
    private String city;

    public Cinema() {
    }

    public Cinema(String cinemaName, String cinemaAddress, String city) {
        this.cinemaName = cinemaName;
        this.cinemaAddress = cinemaAddress;
        this.city = city;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

