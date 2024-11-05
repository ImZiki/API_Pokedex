package com.es.api.pokedex.repository;


import com.es.api.pokedex.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepositoryApi extends CrudRepository<Pokemon, Long> {

}
