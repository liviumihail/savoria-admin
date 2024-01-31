package com.example.restaurantadmin.service;

import com.example.restaurantadmin.entity.CollabForm;
import com.example.restaurantadmin.repository.CollabFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollabFormService {

    @Autowired
    CollabFormRepository collabFormRepository;

    public List<CollabForm> findAll() {
        return collabFormRepository.findAll();
    }

}
