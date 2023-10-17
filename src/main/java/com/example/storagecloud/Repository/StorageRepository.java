package com.example.storagecloud.Repository;

import com.example.storagecloud.Entity.StorageFiles;
import com.example.storagecloud.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StorageRepository extends JpaRepository<StorageFiles,Integer> {

    StorageFiles findByFilenameAndUsers(String filename, Users users);

    void deleteByFilenameAndUsers(String filename, Users users);

    @Modifying
    @Query("update StorageFiles f set f.filename= :newName where  f.filename=:filename and f.users=:user")
    void editFilenameByUser(@Param("newName") String newName,@Param("filename") String filename, @Param("user") Users user);


    List<StorageFiles> findAllByUsers(Users user);
}
