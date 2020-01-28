package com.ipiecoles.java.eval.mdd050.repository;

import com.ipiecoles.java.eval.mdd050.model.Album;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
	
	Optional<Album> findById(Long id);
}
