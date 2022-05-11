package com.example.examportal;

import com.example.examportal.model.Role;
import com.example.examportal.model.User;
import com.example.examportal.model.UserRole;
import com.example.examportal.repo.UserRepository;
import com.example.examportal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(ExamPortalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        User admin = new User();
//        admin.setUsername("kaust");
//        admin.setPassword(this.bCryptPasswordEncoder.encode("1234"));
//        admin.setEnabled(true);
//        admin.setPhone("7878787878");
//        admin.setEmail("kaustavmanna4@gmail.com");
//        admin.setAbout("Hi");
//        admin.setFirst_name("Kaustav");
//        admin.setLast_name("Manna");
//        Role role = new Role();
//        role.setRole_id(11L);
//        role.setRole("ADMIN");
//        UserRole userRole = new UserRole();
//        userRole.setRole(role);
//        userRole.setUser(admin);
//        Set<UserRole> set = new HashSet<>();
//        set.add(userRole);
//        this.userService.createUser(admin,set);
    }
}
