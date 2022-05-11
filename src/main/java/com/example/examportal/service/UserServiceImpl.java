package com.example.examportal.service;

import com.example.examportal.model.User;
import com.example.examportal.model.UserRole;
import com.example.examportal.repo.RoleRepository;
import com.example.examportal.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = this.userRepository.findByUsername(user.getUsername());
        if(local != null){
            System.out.println("User already exist.");
            throw new Exception("User already exist.");
        }else {
            //creating a new user
            for(UserRole ur : userRoles){
                this.roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUser(String username) throws Exception {
        User local = this.userRepository.findByUsername(username);
        if(local == null){
            System.out.println("User doesn't exist");
            throw new Exception("User doesn't exist");
        }else{
            return local;
        }
    }

    @Override
    public void delete_user(String username) {
        User local = this.userRepository.findByUsername(username);
        if(local == null){
            System.out.println("User doesn't exist");
        }else{
            this.userRepository.delete(local);
        }
    }

    @Override
    public User updateUser(String username, User user) throws Exception {
        User local = this.userRepository.findByUsername(username);
        if(local == null){
            System.out.println("User doesn't exist");
            throw new Exception("User doesn't exist");
        }else {
            user.setUser_id(local.getUser_id());
            if(Objects.nonNull(user.getUsername()) && !"".equalsIgnoreCase(user.getUsername())){
                local.setUsername(user.getUsername());
            }

            if(Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())){
                local.setEmail(user.getEmail());
            }

            if(Objects.nonNull(user.getFirst_name()) && !"".equalsIgnoreCase(user.getFirst_name())){
                local.setFirst_name(user.getFirst_name());
            }

            if(Objects.nonNull(user.getLast_name()) && !"".equalsIgnoreCase(user.getLast_name())){
                local.setLast_name(user.getLast_name());
            }

            if(Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())){
                local.setPassword(user.getPassword());
            }

            if(Objects.nonNull(user.getPhone()) && !"".equalsIgnoreCase(user.getPhone())){
                local.setPhone(user.getPhone());
            }

            local.setProfile_pic(user.getProfile_pic());
            local.setAbout(user.getAbout());

            return this.userRepository.save(local);
        }
    }


}
