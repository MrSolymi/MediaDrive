package me.solymi.repository;

import me.solymi.model.Audit;
import me.solymi.model.AuditAction;
import me.solymi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditRepo extends JpaRepository<Audit, Long> {
    List<Audit> findAllByUser(User user);
    List<Audit> findAllByAction(AuditAction action);
}
