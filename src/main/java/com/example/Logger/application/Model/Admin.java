package com.example.Logger.application.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "Users")
@Validated
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    @JsonProperty("username")
    public String username;
    @JsonProperty("password")
    public String password;
    @JsonProperty("email")
    public String email;
    private UserAuth userAuth;
}
