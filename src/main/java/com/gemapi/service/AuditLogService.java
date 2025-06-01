package com.gemapi.service;

import java.util.List;

import com.gemapi.entity.AuditLog;

public interface AuditLogService {
    AuditLog save(AuditLog auditLog);
    List<AuditLog> findAll();
    AuditLog findById(Long id);
    void deleteById(Long id);
} 