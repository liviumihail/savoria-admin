package com.example.restaurantadmin.repository;

import com.example.restaurantadmin.entity.Products;
import com.example.restaurantadmin.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TableRepository extends JpaRepository<Table, Long> {

    @Query("SELECT SUM(table.seatsNumber) FROM Table table")
    Long totalSeatsNo();

    @Query("SELECT table FROM Table table WHERE table.tableNo = ?1")
    Table existsTableWithSameNumber(Long no);

    @Query("SELECT table FROM Table table WHERE table.id=?1")
    Table getTableById(Long id);

    @Query("SELECT table FROM Table table WHERE table.tableNo=?1")
    Table findByNumber(Long no);
}
