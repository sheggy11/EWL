package org.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.example.models.Account;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail (String email);
    List<Account> findAllByState(Account.State state);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update accounts set first_name = :firstName, last_name = :lastName, password = :password where email = :email, med_center = :medCenter",  nativeQuery = true)
    void updateUser(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("password") String password, @Param("email") String email, @Param("medCenter") String medCenter);

    @Query(value = "select * from accounts a WHERE a.email = :email", nativeQuery = true)
    Optional<Account> getAccountByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.roles = NULL WHERE a.id = :accountId AND :roleId MEMBER OF a.roles")
    void deleteRoleByAccountIdAndRoleId(@Param("accountId") Long accountId, @Param("roleId") Long roleId);


}