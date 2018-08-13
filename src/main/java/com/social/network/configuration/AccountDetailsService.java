package com.social.network.configuration;

import com.google.common.collect.ImmutableList;
import com.social.network.exceptions.UserNotExistsException;
import com.social.network.model.dao.User;
import com.social.network.model.dao.UserDetail;
import com.social.network.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findUserByLoginEquals(username)
                .orElseThrow(UserNotExistsException::new);
        return UserDetail.builder()
                .userId(user.getId())
                .username(user.getLogin())
                .password("{noop}" + user.getPassword())
                .authorities(ImmutableList.of(user.getRole()))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }
}
