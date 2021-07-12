package org.launchcode.final_KIM_STAHL.controller;

import org.apache.catalina.Store;
import org.launchcode.final_KIM_STAHL.model.User;
import org.launchcode.final_KIM_STAHL.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
//    the next line combines this line:User user = new User() and model.addAttribute("user", user);
        model.addAttribute("user", new User());

        List<String> listMedium = Arrays.asList("Metals", "Wood", "Drawing", "Painting", "Photography", "Textiles", "Printmaking", "Other");
        model.addAttribute("listMedium", listMedium);
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(User.getPwHash());
        user.setPwHash(encodedPassword);

        userRepository.save(user);
        System.out.println(user);
        return "success";
    }




    /**
     * Delete user map.
     *
     * @param userId the user id
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId) throws Exception {
        User user =
                userRepository
                        .findById(Math.toIntExact(userId))
                        .orElseThrow(() -> new NotFoundException("User not found on :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
}



