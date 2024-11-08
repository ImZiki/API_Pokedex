package com.es.api.pokedex.controller;

import com.es.api.pokedex.dto.PokemonDTO;
import com.es.api.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService service;


    @GetMapping("/{id}")
    public PokemonDTO getById(@PathVariable String id) {
        return  service.getById(id);
    }
    @GetMapping("/")
    public List<PokemonDTO> getAll(){
        return null;
    }

    @PostMapping("/")
    public PokemonDTO insert(@RequestBody PokemonDTO pokemonDTO) {
        return service.insert(pokemonDTO);
    }

    @PutMapping("/{id}")
    public PokemonDTO update(@RequestBody PokemonDTO  pokemonDTO, @PathVariable String id) {
        return service.update(pokemonDTO,id);
    }

    @DeleteMapping("/{id}")
    public PokemonDTO delete(@PathVariable String id) {
        if(id == null || id.isEmpty()) return null;

        return service.delete(id);
    }

}
