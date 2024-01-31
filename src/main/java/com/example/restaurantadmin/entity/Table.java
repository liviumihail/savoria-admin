package com.example.restaurantadmin.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@javax.persistence.Table(name = "tables")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "seats_number")
    private Integer seatsNumber;

    @Column(name = "inside")
    private Boolean inside;

    @Column(name="table_no")
    private Long tableNo;

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

    public Boolean getInside() {
        return inside;
    }

    public void setInside(Boolean inside) {
        this.inside = inside;
    }

    public Long getTableNo() {
        return tableNo;
    }

    public void setTableNo(Long tableNo) {
        this.tableNo = tableNo;
    }
}
