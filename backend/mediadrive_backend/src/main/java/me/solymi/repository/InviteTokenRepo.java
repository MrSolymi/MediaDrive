package me.solymi.repository;

import me.solymi.model.InviteToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteTokenRepo extends JpaRepository<InviteToken, Long> {

    InviteToken findByToken(String token);
}
