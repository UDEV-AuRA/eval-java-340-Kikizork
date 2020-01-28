package com.ipiecoles.java.eval.mdd050.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipiecoles.java.eval.mdd050.model.Album;
import com.ipiecoles.java.eval.mdd050.service.AlbumService;

@RestController
@RequestMapping(value ="/albums")
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	
	@Valid
	@PostMapping(value ="")
	public Album createAlbum(@RequestBody Album album) {
		return albumService.creerAlbum(album);
	}
	
	@DeleteMapping(value ="{id}")
	public void deleteAlbum(@PathVariable (value="id")Long id) {
		albumService.deleteAlbum(id);
	}
}
