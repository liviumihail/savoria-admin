package com.example.restaurantadmin.controller;

import com.example.restaurantadmin.dto.AppUserDto;
import com.example.restaurantadmin.dto.RecoverPasswordDto;
import com.example.restaurantadmin.entity.AppUser;
import com.example.restaurantadmin.enums.AppUserRole;
import com.example.restaurantadmin.repository.AppUserRepository;
import com.example.restaurantadmin.service.AppUserService;
import com.example.restaurantadmin.service.ConfirmationTokenService;
import com.example.restaurantadmin.service.RecoverPasswordService;
import com.example.restaurantadmin.service.RegistrationService;
import com.example.restaurantadmin.utils.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Autowired
    AppUserService appUserService;

    @Autowired
    RecoverPasswordService recoverPasswordService;

    private static String emailInUse;

    private final RegistrationService registrationService;

    @GetMapping(path = "/registration/confirm/{token}")
    public String confirm(@PathVariable String token) {
        registrationService.confirmToken(token);

        if (!confirmationTokenService.getToken(token).get().getAppUser().getEnabled()) {
            return "redirect:/pages-signin?error";
        }
        return "confirmToken";
    }

    @GetMapping("/pages-signin")
    public String login(Model model) {
        AppUserDto appUser = new AppUserDto();
        model.addAttribute("user", appUser);
        return "pages-signin";
    }

    @PostMapping("/pages-signin")
    public String loginSubmit(@Valid @ModelAttribute("user") AppUserDto customerDto,
                              BindingResult result,
                              Model model) {

        AppUser existingUser = appUserService.findUserByEmailObject(customerDto.getEmail());

        if (existingUser == null || existingUser.getAppUserRole() != AppUserRole.ADMIN) {
            result.rejectValue("email", null,
                    "Adresa de email este invalidă");
        }

        if (existingUser == null) {
            return "redirect:/pages-signin?error";
        }

        if (!(existingUser.getEmail().equals(customerDto.getEmail()))) {
            result.rejectValue("email", null,
                    "Email address in invalid");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(customerDto.getPassword(), existingUser.getPassword())) {
            result.rejectValue("password", null, "Parola nu este validă!");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", customerDto);
            return "redirect:/pages-signin?error";
        }

        if (!existingUser.getEnabled()) {
            return "redirect:/pages-signin?error";
        }

        return "redirect:/index";
    }

    @GetMapping("/pages-signup")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        AppUser appUser = new AppUser();
        model.addAttribute("user", appUser);
        return "pages-signup";
    }

    @PostMapping("/pages-signup/save")
    public String registration(@Valid @ModelAttribute("user") AppUserDto customerDto,
                               BindingResult result,
                               Model model) throws Exception {
        Optional<AppUser> existingUser = appUserService.findUserByEmail(customerDto.getEmail());

        if (existingUser.isPresent() && existingUser != null) {
            result.rejectValue("email", null,
                    "Există deja un cont creat cu acest email!");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", customerDto);
            return "redirect:/pages-signup?error";
        }

        RegistrationRequest request = new RegistrationRequest(customerDto.getFirstName(),
                customerDto.getLastName(), customerDto.getEmail(), customerDto.getPassword());
        registrationService.register(request);
        return "redirect:/pages-signup?success";
    }

    @PostMapping("/pages-recover-password/save")
    public String sendEmailWithToken(@Valid @ModelAttribute("user") RecoverPasswordDto recoverPasswordDto,
                                     BindingResult result) throws Exception {

        if (result.hasErrors()) {
            return "redirect:/pages-recover-password?error";
        }

        recoverPasswordService.sendEmailWithToken(recoverPasswordDto);
        emailInUse = recoverPasswordDto.getEmail();
        return "redirect:/pages-recover-password?success";
    }

    @PostMapping("/recover-password-confirmation")
    public String resetPassword(@Valid @ModelAttribute("user") RecoverPasswordDto recoverPasswordDto,
                                BindingResult result) throws Exception {

        if (result.hasErrors()) {
            return "redirect:/recover-password-confirmation?error";
        }

        if (!recoverPasswordDto.getPassword().equals(recoverPasswordDto.getPwdConfirm())) {
            return "redirect:/recover-password-confirmation?error";
        }

        //getCurrentLoggedInUser
        AppUser existingUser = appUserService.findUserByEmailObject(emailInUse);
        if (existingUser!=null) {
            existingUser.setPassword(recoverPasswordDto.getPassword());
        } else {
            return "redirect:/recover-password-confirmation?error";
        }
        return "redirect:/recover-password-confirmation?success";
    }

    @GetMapping("recover-password-confirmation")
    public String shoeResetPassword() {
        return "recover-password-confirmation";
    }

}
