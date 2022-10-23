package com.giggle.api.service;

import com.giggle.api.model.component.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SuperUserService implements UserDetailsService {
    @Autowired
    ConfigProperties configProp;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String _name = configProp.getConfigValue("spring.security.user.name");
        String _password = configProp.getConfigValue("spring.security.user.password");
        String _email = configProp.getConfigValue("spring.security.user.email");
        String _role = configProp.getConfigValue("spring.security.user.role");

        return new CustomUserDetails(_name, _password, _email, _role);
    }
}
