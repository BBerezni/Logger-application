package com.example.Logger.application.Repository;

import com.example.Logger.application.Model.Logger;
import com.example.Logger.application.Model.logType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggerRepository extends JpaRepository<Logger, Integer> {

    List<Logger> findAll();
    @Query(value = "SELECT MAX(id) FROM LogsTable WHERE id=:?1", nativeQuery = true)
    Integer coundId(@Param("id") int id);

    @Query(value = "SELECT * FROM LogsTable WHERE (logType=: logType) AND date BETWEEN (dateFrom AND dateTo)", nativeQuery = true)
    List<Logger> findLogsFromTo(@Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo, @Param("logType")logType logType);

}
