package com.example.Logger.application.Repository;

import com.example.Logger.application.Model.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends JpaRepository<Logger, Integer> {

}
