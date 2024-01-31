package com.example.restaurantadmin.service;

import com.example.restaurantadmin.dto.TableReservationDto;
import com.example.restaurantadmin.entity.TableReservation;
import com.example.restaurantadmin.repository.TableReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableReservationService {

    @Autowired
    TableService tableService;

    @Autowired
    TableReservationRepository tableReservationRepository;

    @Autowired
    AppUserService appUserService;

    private Long occupiedSeatsPostReservation(TableReservationDto tableReservationDto) {
        Long preOccupiedSeats = 0L;
        if (tableReservationRepository.occupiedSeats() != null) {
            preOccupiedSeats = tableReservationRepository.occupiedSeats();
        }
        Long postOccupiedSeats = preOccupiedSeats + tableReservationDto.getGuestsNo();
        return postOccupiedSeats;
    }

    public Long availableSeats(TableReservationDto tableReservationDto) {

        Long totalSeatsNumber = tableService.totalSeatsNo();
        Long occupiedSeats = occupiedSeatsPostReservation(tableReservationDto);
        Long availableSeatsNo = totalSeatsNumber - occupiedSeats;

        return availableSeatsNo;
    }

    public Long availableSeatsPreReservation(TableReservationDto tableReservationDto) {
        Long totalSeatsNumber = tableService.totalSeatsNo();
        Long occupiedSeats = tableReservationRepository.occupiedSeats();
        Long availableSeats = totalSeatsNumber - occupiedSeats;
        return availableSeats;
    }

    public void saveTableReservation(TableReservationDto tableReservationDto) {
        TableReservation tableReservation = new TableReservation();

        String reservationDateTime = tableReservationDto.getReservationDateTime();
        LocalDateTime rdt = LocalDateTime.parse(reservationDateTime);
        tableReservation.setReservationDateTime(rdt);
        tableReservation.setName(tableReservationDto.getName());
        tableReservation.setPhone(tableReservationDto.getPhone());
        tableReservation.setGuestsNo(tableReservationDto.getGuestsNo());
        tableReservationDto.setReserved(true);
        tableReservation.setReserved(tableReservationDto.getReserved());
        tableReservation.setOnline(tableReservationDto.getOnline());

        tableReservationRepository.save(tableReservation);
    }

    public List<TableReservation> reservations() {
        return tableReservationRepository.findAll();
    }

    public void deleteReservation(Long id) {
        tableReservationRepository.deleteById(id);
    }

    public List<TableReservation> showTable() {
        return tableReservationRepository.findAll();
    }

    public Long getTotalSeats() {
        return tableService.totalSeatsNo();
    }

    public TableReservation getReservationById(Long id) {
        return tableReservationRepository.getReservationById(id);
    }

    public void save(TableReservationDto tableReservationDto) {
        TableReservation tableReservation = tableReservationRepository.getReservationById(tableReservationDto.getId());

        String reservationDateTime = tableReservationDto.getReservationDateTime();
        LocalDateTime rdt = LocalDateTime.parse(reservationDateTime);
        tableReservation.setReservationDateTime(rdt);
        tableReservation.setName(tableReservationDto.getName());
        tableReservation.setPhone(tableReservationDto.getPhone());
        tableReservation.setGuestsNo(tableReservationDto.getGuestsNo());
        tableReservationDto.setReserved(tableReservationDto.getReserved());
        tableReservation.setReserved(tableReservationDto.getReserved());
        tableReservationRepository.save(tableReservation);
    }

    public List<TableReservation> findAllReservations() {
        return tableReservationRepository.findAll();
    }

    public Long getOnlineReservations() {
        return tableReservationRepository.getOnlineReservations();
    }

    public Long getInStoreReservations() {
        return tableReservationRepository.getInStoreReservations();
    }

    public List<Long> calculatePercentage(Long number1, Long number2, Long number3) {
        List<Long> list = new ArrayList<>();
        Long total = getTotalSeats();

        Double percentage1 = ((double) number1 / total) * 100;
        Double percentage2 = ((double) number2 / total) * 100;

        long roundingError = Math.round(percentage1) + Math.round(percentage2) - 100;

        // distribute the rounding error to the percentages
/*        if (roundingError != 0) {
            if (roundingError > 0) {
                // add the error to the largest percentage
                if (percentage1 >= percentage2) {
                    percentage1 -= (double) roundingError;
                } else {
                    percentage2 -= (double) roundingError;
                }
            } else {
                // subtract the error from the smallest percentage
                if (percentage1 <= percentage2) {
                    percentage1 -= (double) roundingError;
                } else {
                    percentage2 -= (double) roundingError;
                }
            }
        }*/

        number1 = Math.round(percentage1);
        number2 = Math.round(percentage2);
        number3= 100-(number1+number2);
        list.add(number1);
        list.add(number2);
        list.add(number3);
        return list;
    }

    public void test(Long n1, Long n2, Long n3) {
        n1=1L;
        n2=2L;
        n3=97L;
    }

}

