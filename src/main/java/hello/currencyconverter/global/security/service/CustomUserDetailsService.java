package hello.currencyconverter.global.security.service;

import hello.currencyconverter.application.user.domain.User;
import hello.currencyconverter.application.user.service.port.UserRepository;
import hello.currencyconverter.global.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> _user = userRepository.findUserByEmail(username);
        if (_user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(_user.get());
    }
}
