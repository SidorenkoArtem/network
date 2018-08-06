package com.social.network.services;

import com.social.network.model.dao.PagePermission;
import com.social.network.model.dao.User;
import com.social.network.model.requests.RegistrationRequest;
import com.social.network.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void userRegistration(final RegistrationRequest registrationRequest) {
        final User user = new User();
        user.setLogin(registrationRequest.getLogin());//todo check on existing login
        user.setPassword(registrationRequest.getPassword());
        user.setFirstName(registrationRequest.getFirstName());
        user.setName(registrationRequest.getName());
        user.setSurname(registrationRequest.getSurname());
        user.setBirthday(registrationRequest.getBirthday());
        user.setSex(registrationRequest.getSex());
        user.setEmail(registrationRequest.getEmail());
        user.setPhotoUrl(registrationRequest.getPhotoUrl());
        user.setRole(registrationRequest.getRole());
//        userRepository.(user);
        userRepository.save(user);
        final PagePermission pagePermission = new PagePermission();
        pagePermission.setUserId(user.getId());
        pagePermission.setShowWall(registrationRequest.getShowWall());
        pagePermission.setShowLocation(registrationRequest.getShowLocation());
        pagePermission.setShowGift(registrationRequest.getShowGift());
        user.setPagePermission(pagePermission);
        userRepository.save(user);
        System.out.println(user);

    }
}
