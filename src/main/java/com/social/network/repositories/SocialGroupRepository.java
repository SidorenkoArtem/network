package com.social.network.repositories;

import com.social.network.model.dao.SocialGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.List;

@Repository
public interface SocialGroupRepository extends CrudRepository<SocialGroup, Long> {
    List<SocialGroup> findSocialGroupsByIdIn(final Collection<Long> userIds);
}
