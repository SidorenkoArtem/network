package com.social.network.services;

import com.social.network.exceptions.UserNotExistsException;
import com.social.network.model.dao.User;
import com.social.network.model.enums.Roles;
import com.social.network.model.requests.UserRequest;
import com.social.network.model.responces.UserResponse;
import com.social.network.repositories.UserRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private static final String TEMPLATE = "(http|https|ftp|ftps)\\:\\/\\/[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(\\/\\S*)?";

    private final UserRepository userRepository;

    public UserResponse getUser(final Long id) {
        final User user = userRepository.findById(id).orElseThrow(UserNotExistsException::new);
        return new UserResponse(ConvertUtil.convertToUserDto(user));
    }

    public Optional<User> getUserByLogin(final String login) {
        return userRepository.findUserByLoginEquals(login);
    }

    public void registration(final UserRequest registration) {
        final User user = new User();
        user.setRole(Roles.USER);
        user.setName(registration.getName());
        user.setFirstName(registration.getFirstName());
//        user.setEmail(registration.getEmail());
        user.setLogin(registration.getLogin());
        //user.setPassword(registration.getPassword);TODO
    }


}
