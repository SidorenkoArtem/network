package com.social.network.services;

import com.social.network.exceptions.UserNotExistsException;
import com.social.network.model.dao.User;
import com.social.network.model.dto.UserDto;
import com.social.network.model.enums.Roles;
import com.social.network.model.requests.RegistrationRequest;
import com.social.network.model.responces.SimpleUsersResponse;
import com.social.network.model.responces.SocialGroupResponse;
import com.social.network.model.responces.UserResponse;
import com.social.network.model.responces.UsersResponse;
import com.social.network.repositories.PostRepository;
import com.social.network.repositories.UserRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private static final String TEMPLATE = "(http|https|ftp|ftps)\\:\\/\\/[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(\\/\\S*)?";

    private final UserRepository userRepository;

    public SocialGroupResponse getUserSocialGroups(final Long userId) {
        return new SocialGroupResponse(null, null);
    }

    public SimpleUsersResponse getUsers(final String searchText, final Integer offset, final Integer limit) {
        //final Page<User> users = userRepository.findUserByFirstNameLikeOrNameLike(searchText, PageRequest.of(offset, limit));
        return null;//new SimpleUsersResponse(users.stream().map(ConvertUtil::convertToUserDto).collect(Collectors.toList()));
    }

    public UserResponse getUser(final Long id) {
        final User user = userRepository.findById(id).orElseThrow(UserNotExistsException::new);
        return new UserResponse(ConvertUtil.convertToUserDto(user));
    }

    public Optional<User> getUserByLogin(final String login) {
        return userRepository.findUserByLoginEquals(login);
    }

    public void registration(final RegistrationRequest registration) {
        final User user = new User();
        user.setRole(Roles.USER);
        user.setName(registration.getName());
        user.setFirstName(registration.getFirstName());
        user.setEmail(registration.getEmail());
        user.setLogin(registration.getLogin());
        //user.setPassword(registration.getPassword);TODO
    }

//    public List<LinkDataDto> checkLinkData(final Long groupId) {
//        final Long userId =0L;
//        //final String template = ;
//        final List<Post> posts = postRepository.findPostByUserIdEqualsAndGroupIdEquals(userId, groupId);
//        for(Post post: posts) {
//            post.getText().
//        }
//        return OpenGraphUtil.parseHtmlMetaTags(url);
//    }
}
