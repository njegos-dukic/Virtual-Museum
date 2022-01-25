package org.unibl.etf.virtualmuseumapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.virtualmuseumapi.model.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity getUserByUsernameAndPasswordAndIsApprovedAndIsBlocked(String username, String password, Boolean approved, Boolean blocked);
    UserEntity getUserByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
