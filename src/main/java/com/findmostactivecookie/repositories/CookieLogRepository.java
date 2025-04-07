package com.findmostactivecookie.repositories;

import com.findmostactivecookie.Entities.CookieLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@Repository
public interface CookieLogRepository extends JpaRepository<CookieLog, Long> {
    List<CookieLog> findAllByTimestampBetween(OffsetDateTime start, OffsetDateTime end);
}

