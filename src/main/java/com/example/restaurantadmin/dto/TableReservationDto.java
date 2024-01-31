package com.example.restaurantadmin.dto;

public class TableReservationDto {

    private Long id;

    private String phone;

    private String reservationDateTime;

    private Integer guestsNo;

    private Boolean reserved;

    private String name;

    private Boolean online;

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(String reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public Integer getGuestsNo() {
        return guestsNo;
    }

    public void setGuestsNo(Integer guestsNo) {
        this.guestsNo = guestsNo;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


