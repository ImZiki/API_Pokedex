package com.es.api.pokedex.service;


import com.es.api.pokedex.dto.AtaqueDTO;
import com.es.api.pokedex.dto.PokemonDTO;
import com.es.api.pokedex.dto.TipoDTO;
import com.es.api.pokedex.model.Ataque;
import com.es.api.pokedex.model.Pokemon;
import com.es.api.pokedex.model.Tipo;
import com.es.api.pokedex.repository.AtaqueRepositoryApi;
import com.es.api.pokedex.repository.PokemonRepositoryApi;
import com.es.api.pokedex.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class PokemonService {

    @Autowired
    private PokemonRepositoryApi repository;

    @Autowired
    private AtaqueRepositoryApi ataqueRepository;


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
            PokemonDTO pokemonDTO = Mapper.entityToDTO(p);
            listaPokemonDTO.add(pokemonDTO);

        }
        return listaPokemonDTO;
    }


    public PokemonDTO insert(PokemonDTO pokemonDTO) {
        if(pokemonDTO == null) return null;
        Pokemon p = Mapper.DTOToEntity(pokemonDTO);
        Ataque a = p.getAtaques().get(0);
        ataqueRepository.save(a);
        repository.save(p);
        return pokemonDTO;
    }

    public PokemonDTO update(PokemonDTO  pokemonDTO, String id) {
        Pokemon p = repository.findById(Long.parseLong(id)).isPresent() ? repository.findById(Long.parseLong(id)).get() : null;
        if(p != null){
            p.setNombre(pokemonDTO.getNombre());
            p.setVida(pokemonDTO.getVida());
            p.setShiny(true);
            p.setTipo(new Tipo(pokemonDTO.getTipo().getNombre()));
            List<Ataque> ataques = new ArrayList<>();
            ataques.add(new Ataque(pokemonDTO.getAtaque().getDanioBase(), pokemonDTO.getAtaque().isEspecial(),
                    new Tipo(pokemonDTO.getAtaque().getTipo().getNombre()), pokemonDTO.getAtaque().getNombre()));
            p.setAtaques(ataques);
            Ataque a = p.getAtaques().get(0);
            ataqueRepository.save(a);
            repository.save(p);
            return pokemonDTO;
        }

        return null;
    }

    public PokemonDTO delete(String id) {
        long idL = 0L;
        try {
            idL = Long.parseLong(id);
            Pokemon p = repository.findById(idL).isPresent() ? repository.findById(idL).get() : null;
            PokemonDTO pokemonDTO;
            if (p == null) {
                return null;
            } else {
                repository.delete(p);
                pokemonDTO = Mapper.entityToDTO(p);

            }
            return pokemonDTO;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
