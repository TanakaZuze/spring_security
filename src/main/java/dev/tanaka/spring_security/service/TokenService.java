package dev.tanaka.spring_security.service;

import dev.tanaka.spring_security.entity.Token;
import dev.tanaka.spring_security.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public Token save(Token token) {
        return tokenRepository.save(token);
    }
}
