package me.solymi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Getter
@Entity
@Table(name = "invite_token")
@NoArgsConstructor
public class InviteToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "created_By")
    private String username;

    @Column(name = "token_value")
    private String token;

    @Column(name = "expiration_date")
    private ZonedDateTime expiresAt;

    @Column(name = "used_date")
    private ZonedDateTime usedAt;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "is_used")
    private boolean isUsed;

    @Column(name = "is_valid")
    private boolean isValid;

    public InviteToken(User user) {
        this.user = user;
        this.username = user.getUsername();
        this.token = UUID.randomUUID().toString();
        this.expiresAt = ZonedDateTime.now().plus(7, TimeUnit.DAYS.toChronoUnit());
        this.createdAt = ZonedDateTime.now();
        this.isUsed = false;
        this.isValid = true;
    }

    public void useToken(){
        validateToken();
        if (this.isValid) {
            this.isUsed = true;
            this.isValid = false;
            this.usedAt = ZonedDateTime.now();
        }
    }

    public void validateToken(){
        if (this.isUsed) this.isValid = false;
        if (expiresAt.isBefore(ZonedDateTime.now())) this.isValid = false;
    }
}
