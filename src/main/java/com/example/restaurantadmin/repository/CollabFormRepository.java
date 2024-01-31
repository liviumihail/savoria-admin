package com.example.restaurantadmin.repository;


import com.example.restaurantadmin.entity.CollabForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollabFormRepository extends JpaRepository<CollabForm, Long> {
}
