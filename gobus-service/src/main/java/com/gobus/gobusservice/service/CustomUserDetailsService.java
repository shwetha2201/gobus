package com.gobus.gobusservice.service;

import com.gobus.gobusservice.model.User;
import com.gobus.gobusservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("CustomUserDetailsService retrieving user by email {}", email);
        Optional<User> user = userRepository.findByEmailId(email);
        user.orElseThrow(() -> new UsernameNotFoundException("Email id doesn't exists"));
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.get().getEmailId())
                        .password(user.get().getPassword())
                        .roles(user.get().getUserType().name())
                        .build();
        return userDetails;
    }

}
