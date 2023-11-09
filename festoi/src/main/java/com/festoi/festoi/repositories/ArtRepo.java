package com.festoi.festoi.repositories;

import com.festoi.festoi.models.Art;
import com.festoi.festoi.models.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArtRepo extends CrudRepository<Art,Integer> {
    Optional<Art> findArtByArtName(String artName);
    Iterable<Art> getAllByArtist(Artist artist);

    List<Art> findAll();
}
