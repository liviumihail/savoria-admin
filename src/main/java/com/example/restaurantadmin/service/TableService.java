package com.example.restaurantadmin.service;

import com.example.restaurantadmin.dto.TableDto;
import com.example.restaurantadmin.entity.Products;
import com.example.restaurantadmin.entity.Table;
import com.example.restaurantadmin.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    @Autowired
    TableRepository tableRepository;

    public void save(Table table) {
        tableRepository.save(table);
    }

    public List<Table> findAllTables() {
        return tableRepository.findAll();
    }

    public Long totalSeatsNo() {
        return tableRepository.totalSeatsNo();
    }

    public void saveTable(TableDto tableDto) {
        Table table = new Table();
        table.setTableNo(tableDto.getTableNo());
        table.setInside(tableDto.getInside());
        table.setSeatsNumber(tableDto.getSeatsNumber());
        save(table);
    }

    public void deleteTable(Long id) {
        tableRepository.deleteById(id);
    }

    public Table getTableById(Long id) {
        return tableRepository.getTableById(id);
    }

    public Table existsTableWithSameNumber(Long no) {
        return tableRepository.existsTableWithSameNumber(no);
    }

    public Table findTableByNumber(Long no) {
       return tableRepository.findByNumber(no);
    }
}
