package com.example.restaurantadmin.service;

import com.example.restaurantadmin.dto.AppUserDto;
import com.example.restaurantadmin.entity.AppUser;
import com.example.restaurantadmin.entity.ConfirmationToken;
import com.example.restaurantadmin.entity.Products;
import com.example.restaurantadmin.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public int enableAppUser(String email) {

        return appUserRepository.enableAppUser(email);
    }

    public Optional<AppUser> findUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public AppUser findUserByEmailObject(String email) {
        return appUserRepository.findByEmailObject(email);
    }

    public String getLoggedInUser() {
        return appUserRepository.getLoggedInUser();
    }

    public List<String> usersEmail() {
        return appUserRepository.finUsersEmail();
    }

    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    public void saveUser(AppUserDto userDto) {
        AppUser appUser = new AppUser();
        appUser.setEmail(userDto.getEmail());
        String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
        appUser.setPassword(encodedPassword);
        appUser.setAppUserRole(userDto.getAppUserRole());
        appUser.setLocked(userDto.getLocked());
        appUser.setEnabled(userDto.getEnabled());
        appUser.setFirstName(userDto.getFirstName());
        appUser.setLastName(userDto.getLastName());
        appUserRepository.save(appUser);
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    public AppUser getUserById(Long id) {
        return appUserRepository.getAppUserById(id);
    }

}
