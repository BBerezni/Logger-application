package com.example.Logger.application.Repository;

import com.example.Logger.application.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {

    String findPasswordById(UUID id);

}
