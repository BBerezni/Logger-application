package com.example.Logger.application.Repository;

import com.example.Logger.application.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findAll();

    @Query(value = "SELECT COUNT(*) FROM Users WHERE username=:username", nativeQuery = true)
    Integer existsByUsername(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM Users WHERE email=:email", nativeQuery = true)
    Integer existsByEmail(@Param("email") String email);

    @Query(value = "SELECT COUNT(*) FROM Users WHERE password=:password", nativeQuery = true)
    Integer existsByPassword(@Param("password") String password);

    @Query(value = "SELECT COUNT(*) FROM Users WHERE id=:id", nativeQuery = true)
    Integer findId(@Param("id") UUID id);
    String findByUsername(String username);
    String findByPassword(String password);
    String findByEmail(String email);
    String findIdByEmail(String email);
    Optional<Client> findById(UUID uuid);
    @Query(value = "SELECT * FROM Users WHERE id=:id", nativeQuery = true)
    Client findClientById(@Param("id") UUID id);;
}
