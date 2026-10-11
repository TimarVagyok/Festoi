package com.festoi.festoi.repositories;

import com.festoi.festoi.models.Artist;
import com.festoi.festoi.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component

public interface ArtistRepo extends CrudRepository<Artist,Long> {

    Optional<Artist> findByUsername(String username);

}
