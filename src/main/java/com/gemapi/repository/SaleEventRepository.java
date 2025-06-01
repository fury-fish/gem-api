package com.gemapi.repository;

import com.gemapi.entity.SaleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleEventRepository extends JpaRepository<SaleEvent, Long> {
    
    @Query("SELECT se FROM SaleEvent se WHERE se.active = true AND se.startDate <= :now AND se.endDate >= :now")
    List<SaleEvent> findActiveEvents(LocalDateTime now);
    
    List<SaleEvent> findByActive(boolean active);
} 