package com.ipiecoles.java.eval.mdd050.service;

import com.ipiecoles.java.eval.mdd050.model.Album;
import com.ipiecoles.java.eval.mdd050.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
	
    @Autowired
    private AlbumRepository albumRepository;

    
    
    // Gestion d'erreur lazy, je sais !
    
    public Album creerAlbum(Album album) throws DataIntegrityViolationException{
    	try {
        return albumRepository.save(album);
    	} catch (DataIntegrityViolationException e) {
    		throw new DataIntegrityViolationException("L'album existe déjà en base de donnée !");
    	}
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
