package com.festoi.festoi.repositories;

import com.festoi.festoi.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends CrudRepository<Role,Long> {
    Optional<Role> findByAuthority(String authority);
}
