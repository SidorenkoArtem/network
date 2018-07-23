package com.social.network.services;

import com.social.network.exceptions.UserNotExistsException;
import com.social.network.model.dao.Post;
import com.social.network.model.dao.User;
import com.social.network.model.dto.LinkDataDto;
import com.social.network.model.enums.Roles;
import com.social.network.model.requests.RegistrationRequest;
import com.social.network.model.responces.SocialGroupResponse;
import com.social.network.model.responces.UserResponse;
import com.social.network.model.responces.UsersResponse;
import com.social.network.repositories.PostRepository;
import com.social.network.repositories.SocialGroupRepository;
import com.social.network.repositories.UserRepository;
import com.social.network.utils.ConvertUtil;
import com.social.network.utils.OpenGraphUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private static final String TEMPLATE = "(http|https|ftp|ftps)\\:\\/\\/[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(\\/\\S*)?";

    private final UserRepository userRepository;
    //private final SocialGroupRepository socialGroupRepository;
    private final PostRepository postRepository;

    public SocialGroupResponse getUserSocialGroups(final Long userId) {
        //final List<UserGroup> socialGroups = socialGroupRepository.
        return new SocialGroupResponse(null, null);
    }

    public UsersResponse getUsers(final String searchText) {
        return new UsersResponse(null, null);
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

    public List<LinkDataDto> checkLinkData(final Long groupId) {
        final Long userId =0L;
        final String template = ;
        final List<Post> posts = postRepository.findPostByUserIdEqualsAndGroupIdEquals(userId, groupId);
        for(Post post: posts) {
            post.getText().
        }
        return OpenGraphUtil.parseHtmlMetaTags(url);
    }
}
