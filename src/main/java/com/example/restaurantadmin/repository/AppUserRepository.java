package com.example.restaurantadmin.repository;

import com.example.restaurantadmin.entity.AppUser;
import com.example.restaurantadmin.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository
        extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Query("SELECT appUser from AppUser appUser where appUser.email=?1")
    AppUser findByEmailObject(String email);


    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    @Query("SELECT appUser.email FROM AppUser appUser WHERE appUser.loggedInUser=TRUE")
    String getLoggedInUser();

    @Query("SELECT appUser.email FROM AppUser appUser WHERE appUser.appUserRole='USER'")
    List<String> finUsersEmail();

    @Query("SELECT appUser FROM AppUser appUser WHERE appUser.id=?1")
    AppUser getAppUserById(Long id);
}
