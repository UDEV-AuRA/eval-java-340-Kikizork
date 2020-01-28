package com.ipiecoles.java.eval.mdd050.service;

import com.ipiecoles.java.eval.mdd050.model.Artist;
import com.ipiecoles.java.eval.mdd050.repository.AlbumRepository;
import com.ipiecoles.java.eval.mdd050.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public Page<Artist> findAllArtists(Integer page, Integer size, String sortProperty, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortProperty);
        Pageable pageable = PageRequest.of(page,size,sort);
        return artistRepository.findAll(pageable);
    }

    public Artist findById(Long id) {
        Optional<Artist> artist = this.artistRepository.findById(id);
        if(!artist.isPresent()){
            throw new EntityNotFoundException("Impossible de trouver l'artiste d'identifiant " + id);
        }
        return artist.get();
    }
    
    public boolean findByName(String name) {
    	Optional<Artist> artist = this.artistRepository.findByName(name);
        if(!artist.isPresent()){
            return false;
        }
        return true;
    }
    public Long countAllArtists() {
        return artistRepository.count();
    }
    
    @Valid
    public Artist creerArtiste(Artist artist) {
    	if (findByName(artist.getName())) {
    		throw new DataIntegrityViolationException("Erreur : Artiste existant déjà en base de donnée");
    	}
        return artistRepository.save(artist);
    }

    public void deleteArtist(Long id) {
    	if (findById(id).equals(null)) {
    		throw new DataIntegrityViolationException("L'artiste n'existe pas et ne peut donc être supprimé (logique).");
    	}
        artistRepository.deleteById(id);
    }
    
    @Valid
    public Artist updateArtiste(Long id, Artist artist) {
    	if (findById(id).equals(null)) {
    		throw new DataIntegrityViolationException("Erreur : Impossible de trouver l'artiste d'identifiant "+id);
    	}
        return artistRepository.save(artist);
    }

    public Page<Artist> findByNameLikeIgnoreCase(String name, Integer page, Integer size, String sortProperty, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortProperty);
        Pageable pageable = PageRequest.of(page,size,sort);
        return artistRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}
