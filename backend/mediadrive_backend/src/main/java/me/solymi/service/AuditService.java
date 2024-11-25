package me.solymi.service;

import me.solymi.model.AuditAction;
import me.solymi.model.User;

public interface AuditService {
    void audit(User user, AuditAction action, String message);
}
