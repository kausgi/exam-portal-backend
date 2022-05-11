package com.example.examportal.controller;

import com.example.examportal.model.Role;
import com.example.examportal.model.User;
import com.example.examportal.model.UserRole;
import com.example.examportal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin("*")
@Transactional
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public User registerUser(@RequestBody User user) throws Exception {
        Role role = new Role();
        role.setRole_id(12L);
        role.setRole("USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setUsername(user.getUsername());
        user.setProfile_pic(user.getProfile_pic());
        user.setLast_name(user.getLast_name());
        user.setFirst_name(user.getFirst_name());
        user.setAbout(user.getAbout());
        user.setEmail(user.getEmail());
        user.setPhone(user.getPhone());
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);

        return this.userService.createUser(user, userRoles);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) throws Exception {
        return this.userService.getUser(username);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username){
        this.userService.delete_user(username);
    }

    @PutMapping("/{username}")
    public User updateUser(@PathVariable("username") String username, @RequestBody User user) throws Exception {
        return this.userService.updateUser(username, user);
    }
}
