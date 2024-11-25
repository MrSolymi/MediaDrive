package me.solymi.service.impl;

import lombok.RequiredArgsConstructor;
import me.solymi.model.Audit;
import me.solymi.model.AuditAction;
import me.solymi.model.User;
import me.solymi.repository.AuditRepo;
import me.solymi.service.AuditService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepo auditRepo;

    @Override
    public void audit(User user, AuditAction action, String message) {
        Audit audit = new Audit(user, action, message);
        auditRepo.save(audit);
    }
}
