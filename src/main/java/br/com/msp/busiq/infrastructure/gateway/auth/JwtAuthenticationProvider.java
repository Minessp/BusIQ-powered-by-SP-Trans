package br.com.msp.busiq.infrastructure.gateway.auth;

import br.com.msp.busiq.core.domain.UserPrincipal;
import br.com.msp.busiq.core.usecases.auth.ExtractTokenSubjectCase;
import br.com.msp.busiq.infrastructure.persistence.entities.RoleEntity;
import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final ExtractTokenSubjectCase extractTokenSubjectCase;
    private final UserRepository userRepository;

    public JwtAuthenticationProvider(ExtractTokenSubjectCase extractTokenSubjectCase, UserRepository userRepository) {
        this.extractTokenSubjectCase = extractTokenSubjectCase;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = ((JwtAuthenticationToken) authentication).getToken();
        String userId = extractTokenSubjectCase.execute(token);

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        var authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .toList();

        List<String> roles = user.getRoles().stream()
                .map(RoleEntity::getRole)
                .toList();

        UserPrincipal principal = new UserPrincipal(user.getId(), user.getEmail(), roles);

        return new JwtAuthenticationToken(token, principal, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
