package com.es.api.pokedex.utils;

import com.es.api.pokedex.dto.AtaqueDTO;
import com.es.api.pokedex.dto.PokemonDTO;
import com.es.api.pokedex.dto.TipoDTO;
import com.es.api.pokedex.model.Ataque;
import com.es.api.pokedex.model.Pokemon;
import com.es.api.pokedex.model.Tipo;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static PokemonDTO entityToDTO(Pokemon p) {
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setNombre(p.getNombre());
        pokemonDTO.setVida(p.getVida());
        TipoDTO tipoDTO = new TipoDTO();
        tipoDTO.setNombre(p.getTipo().getTipo());
        pokemonDTO.setTipo(tipoDTO);
        AtaqueDTO ataqueDTO = new AtaqueDTO();
        TipoDTO tipoAtaqueDTO = new TipoDTO();
        tipoAtaqueDTO.setNombre(p.getAtaques().get(0).getTipo().getTipo());
        ataqueDTO.setNombre(p.getAtaques().get(0).getNombre());
        ataqueDTO.setEspecial(p.getAtaques().get(0).isEspecial());
        ataqueDTO.setDanioBase(p.getAtaques().get(0).getDanioBase());
        ataqueDTO.setTipo(tipoAtaqueDTO);
        pokemonDTO.setAtaque(ataqueDTO);
        return pokemonDTO;
    }

    public static Pokemon DTOToEntity(PokemonDTO dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setNombre(dto.getNombre());
        pokemon.setVida(dto.getVida());
        pokemon.setTipo(new Tipo(dto.getTipo().getNombre()));
        List<Ataque> ataques = new ArrayList<>();
        ataques.add((new Ataque(dto.getAtaque().getDanioBase(), dto.getAtaque().isEspecial(),
                new Tipo(dto.getAtaque().getTipo().getNombre()), dto.getAtaque().getNombre())));
        pokemon.setAtaques(ataques);
        return pokemon;
    }
}
