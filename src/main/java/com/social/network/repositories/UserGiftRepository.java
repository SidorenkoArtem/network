package com.social.network.repositories;

import com.social.network.model.dao.UserGift;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGiftRepository extends CrudRepository<UserGift, Long> {
    List<UserGift> findUserGiftByUserIdEquals(final Long userId, final Pageable pageable);
    int countByUserIdEquals(final Long userId);
}
