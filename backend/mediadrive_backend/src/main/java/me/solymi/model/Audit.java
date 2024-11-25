package me.solymi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    private AuditAction action;

    @Column(name = "message")
    private String message;

    public Audit(User user, AuditAction action, String message) {
        this.user = user;
        this.action = action;
        this.message = message;
    }
}
