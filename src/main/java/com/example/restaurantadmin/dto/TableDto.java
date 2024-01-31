package com.example.restaurantadmin.dto;

public class TableDto {
    private Long id;

    private Long tableNo;

    private Integer seatsNumber;

    private Boolean inside;

    private Long tablesWithSameSeatsNr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public Long getTablesWithSameSeatsNr() {
        return tablesWithSameSeatsNr;
    }

    public void setTablesWithSameSeatsNr(Long tablesWithSameSeatsNr) {
        this.tablesWithSameSeatsNr = tablesWithSameSeatsNr;
    }

    public Long getTableNo() {
        return tableNo;
    }

    public void setTableNo(Long tableNo) {
        this.tableNo = tableNo;
    }

    public Boolean getInside() {
        return inside;
    }

    public void setInside(Boolean inside) {
        this.inside = inside;
    }
}
