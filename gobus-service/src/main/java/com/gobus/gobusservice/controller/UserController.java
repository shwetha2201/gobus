package com.gobus.gobusservice.controller;

import com.gobus.gobusservice.config.JwtUtils;
import com.gobus.gobusservice.dto.ChangePasswordRequest;
import com.gobus.gobusservice.dto.LoginResponse;
import com.gobus.gobusservice.dto.UserDto;
import com.gobus.gobusservice.service.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Validated
@Slf4j
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtil;

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userDto));
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String email, @RequestParam String pwd) {
        try {
            log.info("Login request received for the email {}", email);
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pwd));
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            String token = jwtUtil.createToken(user);
            LoginResponse loginRes = LoginResponse.builder().user(userService.getByEmailId(email)).token(token).build();
            return ResponseEntity.ok(loginRes);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(LoginResponse.builder().error(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(LoginResponse.builder().error(e.getMessage()).build());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> get(@PathVariable int id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> update(@PathVariable int id, @RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @PutMapping("/{id}/action/reset-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> resetPassword(@PathVariable int id, @RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        userService.resetPassword(id, changePasswordRequest);
        return ResponseEntity.ok().build();
    }

}
