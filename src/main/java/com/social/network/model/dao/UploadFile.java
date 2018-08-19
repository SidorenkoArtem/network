package com.social.network.model.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UploadFile {
    @Id
    @SequenceGenerator(name = "upload_id_seq", sequenceName = "upload_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "upload_id_seq")
    private Long id;
    private String path;
}
