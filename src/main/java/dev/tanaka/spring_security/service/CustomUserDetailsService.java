package dev.tanaka.spring_security.service;

import dev.tanaka.spring_security.entity.Token;
import dev.tanaka.spring_security.entity.User;
import dev.tanaka.spring_security.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailSenderService emailSenderService;

    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.emailSenderService = emailSenderService;
    }


    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username+" not found"));
    }

    public void registerUser(User user){
        userRepository.findByUsernameOrEmail(user.getUsername(),user.getEmail())
                .ifPresent(
                        existingUser -> {
                            throw  new IllegalStateException("User already exists");
                        }
                );

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);

        Token confirmationToken = new Token(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        tokenService.save(confirmationToken);
        emailSenderService.sendEmail(user.getEmail(),confirmationToken.getToken());

    }

    public void confirmToken(String token){
        Token confirmedToken = tokenService.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Invalid Token"));

        if(confirmedToken.getConfirmedAt() != null){
            throw  new IllegalStateException("Token has already been confirmed");
        }

        LocalDateTime tokenExpiryDate = confirmedToken.getExpiresAt();
        if(tokenExpiryDate.isBefore(LocalDateTime.now())){
            throw  new IllegalStateException("Token has expired");
        }

        confirmedToken.setConfirmedAt(LocalDateTime.now());
        tokenService.save(confirmedToken);

        enableUser(confirmedToken.getUser());

    }

    private void enableUser(User user){
        user.setIs_enabled(true);
        userRepository.save(user);
    }







}
