package com.es.api.pokedex.service;


import com.es.api.pokedex.dto.AtaqueDTO;
import com.es.api.pokedex.dto.PokemonDTO;
import com.es.api.pokedex.dto.TipoDTO;
import com.es.api.pokedex.model.Pokemon;
import com.es.api.pokedex.model.Tipo;
import com.es.api.pokedex.repository.PokemonRepositoryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepositoryApi repository;

    public PokemonDTO getById(String id) {
        Long idEntity = Long.parseLong(id);
        //Pokemon p = repository.findById(idEntity).orElse(null);
        Pokemon p = repository.findById(idEntity).orElse(null);
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setNombre(p.getNombre());
        pokemonDTO.setVida(p.getVida());

        TipoDTO tipoDTO = new TipoDTO();
        tipoDTO.setNombre(p.getTipo().getTipo());

        TipoDTO tipoAtaqueDTO = new TipoDTO();
        tipoAtaqueDTO.setNombre(p.getAtaques().get(0).getTipo().getTipo());
        pokemonDTO.setTipo(tipoDTO);

        AtaqueDTO ataqueDTO = new AtaqueDTO();
        ataqueDTO.setNombre(p.getAtaques().get(0).getNombre());
        ataqueDTO.setEspecial(p.getAtaques().get(0).isEspecial());
        ataqueDTO.setDanioBase(p.getAtaques().get(0).getDanioBase());
        ataqueDTO.setTipo(tipoAtaqueDTO);
        pokemonDTO.setAtaque(ataqueDTO);

        return  pokemonDTO;
    }

    public List<PokemonDTO> getAll(){
        List listaPokemonDTO = new ArrayList();
        List<Pokemon> listaPokemon = (List<Pokemon>) repository.findAll();
        for (Pokemon p : listaPokemon) {
            PokemonDTO pokemonDTO = new PokemonDTO();
            pokemonDTO.setNombre(p.getNombre());
            pokemonDTO.setVida(p.getVida());

            TipoDTO tipoDTO = new TipoDTO();
            tipoDTO.setNombre(p.getTipo().getTipo());

            TipoDTO tipoAtaqueDTO = new TipoDTO();
            tipoAtaqueDTO.setNombre(p.getAtaques().get(0).getTipo().getTipo());
            pokemonDTO.setTipo(tipoDTO);

            AtaqueDTO ataqueDTO = new AtaqueDTO();
            ataqueDTO.setNombre(p.getAtaques().get(0).getNombre());
            ataqueDTO.setEspecial(p.getAtaques().get(0).isEspecial());
            ataqueDTO.setDanioBase(p.getAtaques().get(0).getDanioBase());
            ataqueDTO.setTipo(tipoAtaqueDTO);
            pokemonDTO.setAtaque(ataqueDTO);

            listaPokemonDTO.add(pokemonDTO);

        }
        return listaPokemonDTO;
    }


    public PokemonDTO insert(PokemonDTO pokemonDTO) {

        return null;
    }

    public PokemonDTO update(PokemonDTO  pokemonDTO, String id) {
        Pokemon p = repository.findById(Long.parseLong(id)).get();
        p.setNombre(pokemonDTO.getNombre());
        p.setVida(pokemonDTO.getVida());
        p.setShiny(true);
        p.setTipo(pokemonDTO.getTipo().getNombre());
        repository.save(p);
        


        return pokemonDTO;
    }
}
