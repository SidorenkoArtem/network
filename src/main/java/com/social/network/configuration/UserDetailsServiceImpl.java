package com.social.network.configuration;

//import com.social.network.exceptions.UserNotExistsException;
//import com.social.network.model.dao.User;
//import com.social.network.services.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.core.userdetails.User.UserBuilder;

public class UserDetailsServiceImpl {}/*implements UserDetailsService {

    @Autowired
    private UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
    final User user = findUserByLogin(login);
    UserBuilder builder = null;
    if (user != null) {
        builder = org.springframework.security.core.userdetails.User.withUsername(login);
        builder.password(new BCryptPasswordEncoder().encode("123"));
        builder.roles(user.getRole().toString());
    } else {
        throw new UsernameNotFoundException("User not found.");
    }
    return builder.build();
}

    private User findUserByLogin(final String login) {
        return usersService.getUserByLogin(login).orElseThrow(UserNotExistsException::new);
    }
}*/
