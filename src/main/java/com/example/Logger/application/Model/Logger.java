package com.example.Logger.application.Model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "LogsTable")
public class Logger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String message;
    private logType logType;
    @Column(name = "date")
    private LocalDate createdDate;
}
