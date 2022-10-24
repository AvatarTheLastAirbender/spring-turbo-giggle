package com.giggle.api.service;

import com.giggle.api.model.component.UserInfo;
import com.giggle.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuperUserService implements UserDetailsService {
    @Autowired
    private ApplicationPropertyService configProp;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        String _name = configProp.getConfigValue("spring.security.user.name");
        String _password = configProp.getConfigValue("spring.security.user.password");
        String _email = configProp.getConfigValue("spring.security.user.email");
        String _role = configProp.getConfigValue("spring.security.user.role");

        return new UserInfo(_name, _password, _email, _role);
    }

    public void addUser(UserInfo user) {
        userRepository.save(user);
    }

    public List<UserInfo> getUsers() {
        List<UserInfo> allUsers = new ArrayList<>();
        userRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }

    public UserInfo getUserById(int id) {
        UserInfo ExistingUser = getUsers().stream().filter(userInfo -> userInfo.getUserid() == id).findFirst().orElse(null);
        if (ExistingUser == null) throw new UsernameNotFoundException("Invalid user id");
        return ExistingUser;
    }

    public void updateUser(UserInfo user) {
        UserInfo ExistingUser = getUsers().stream().filter(userInfo -> userInfo.getUserid() == user.getUserid()).findFirst().orElse(null);
        if (ExistingUser == null) throw new UsernameNotFoundException("Invalid user id");
        addUser(user);
    }

    public void deleteUser(int id) {
        UserInfo ExistingUser = getUsers().stream().filter(userInfo -> userInfo.getUserid() == id).findFirst().orElse(null);
        if (ExistingUser == null) throw new UsernameNotFoundException("Invalid user id");
        userRepository.delete(ExistingUser);
    }
}
