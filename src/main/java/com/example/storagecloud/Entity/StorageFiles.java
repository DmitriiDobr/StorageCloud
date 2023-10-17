package com.example.storagecloud.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table
@Entity(name="StorageFiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String filename;
    @Column(nullable = false)
    private byte[] content;

    @Column(nullable = false)
    private LocalDateTime time;

    @ManyToOne
    private Users users;

    public StorageFiles(String filename, byte[] content, LocalDateTime time, Users users) {
        this.filename = filename;
        this.content = content;
        this.time = time;
        this.users = users;
    }


}
