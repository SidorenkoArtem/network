package com.social.network.repositories;

import com.social.network.model.dao.UploadFile;
import org.springframework.data.repository.CrudRepository;

public interface FilesRepository extends CrudRepository<UploadFile, Long> {
}
