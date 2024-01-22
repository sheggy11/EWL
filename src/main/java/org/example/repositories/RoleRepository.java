package org.example.repositories;

import org.example.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);

    @Query(nativeQuery = true, value = "select r.* from roles r join accounts_roles ar on r.id = ar.role_id " +
            "join accounts a on ac.account_id = a.id where a.id = :id")
    List<Role> findByUserIdAndRoleId(@Param("id") Long id);
}