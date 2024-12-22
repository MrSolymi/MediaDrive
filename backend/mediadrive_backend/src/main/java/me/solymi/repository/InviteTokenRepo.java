package me.solymi.repository;

import me.solymi.model.InviteToken;
import me.solymi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviteTokenRepo extends JpaRepository<InviteToken, Long> {

    InviteToken findByToken(String token);

    List<InviteToken> findAllByUser(User user);
}
