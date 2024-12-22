package me.solymi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import me.solymi.component.UploadIdGenerator;
import org.hibernate.annotations.GenericGenerator;

import java.time.ZonedDateTime;

@Entity
@Getter
@Table(name = "uploads")
public class Upload {

    @Id
    @GenericGenerator(name = "upload_generator", type = UploadIdGenerator.class)
    @GeneratedValue(generator = "upload_generator")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User uploader;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "upload_date")
    private ZonedDateTime uploadDate;

    @Column(name = "file_size")
    private long size;

    @Column(name = "url")
    private String url;

    @Column(name = "raw_url")
    private String rawUrl;
}
