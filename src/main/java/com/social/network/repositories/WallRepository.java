package com.social.network.repositories;

import com.social.network.model.dao.WallPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WallRepository extends CrudRepository<WallPost, Long> {
    List<WallPost> findWallPostsByWallOwnerIdEquals(final Long wallOwnerId, final Pageable pageable);
    int countWallPostsByWallOwnerIdEquals(final Long wallOwnerId);
}
