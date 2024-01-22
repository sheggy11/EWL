package org.example.repositories;

import org.example.models.CallStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CallStatusRepository extends JpaRepository<CallStatus, Long> {

    @Query("SELECT cs FROM CallStatus cs LEFT JOIN FETCH cs.callLogs WHERE cs.id = :id")
    Optional<CallStatus> findByIdWithCallLogs(@Param("id") Long id);
}

