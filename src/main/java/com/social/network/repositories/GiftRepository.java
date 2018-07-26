package com.social.network.repositories;

import com.social.network.model.dao.Gift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.List;

@Repository
public interface GiftRepository extends CrudRepository<Gift, Long> {
    List<Gift> findGiftByIdIn(final Collection<Long> giftId);
}
