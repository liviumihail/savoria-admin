package com.example.restaurantadmin.repository;


import com.example.restaurantadmin.entity.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation, Long> {

    @Query("SELECT SUM(tableReservation.guestsNo) FROM TableReservation tableReservation where tableReservation.reserved=true")
    Long occupiedSeats();

    @Query("select tableReservation from TableReservation tableReservation")
    List<TableReservation> reservations();

    @Query("SELECT tableReservation FROM TableReservation tableReservation where tableReservation.id=?1")
    TableReservation getReservationById(Long id);

    @Query("SELECT SUM(tableReservations.guestsNo) from TableReservation tableReservations WHERE tableReservations.online=true")
    Long getOnlineReservations();

    @Query("SELECT SUM(tableReservations.guestsNo) from TableReservation tableReservations WHERE tableReservations.online=false")
    Long getInStoreReservations();

}
