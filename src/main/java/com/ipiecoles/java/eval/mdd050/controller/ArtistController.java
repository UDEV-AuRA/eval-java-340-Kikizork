package com.ipiecoles.java.eval.mdd050.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ipiecoles.java.eval.mdd050.model.Artist;
import com.ipiecoles.java.eval.mdd050.service.ArtistService;


@RestController
@RequestMapping(value ="/artists")
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	@GetMapping(value ="{id}")
	public Artist getArtistById(@PathVariable("id")Long id) {
		return artistService.findById(id);
	}
	
	@GetMapping(value="", params = "name")
	public @ResponseBody Page<Artist> getArtistByName(
			@RequestParam(value ="name") String name,
			@RequestParam(value ="page") Integer page,
			@RequestParam(value ="size") Integer size,
			@RequestParam(value ="sortDirection")String sortDirection,
			@RequestParam(value ="sortProperty")String sortProperty
			){
				return artistService.findByNameLikeIgnoreCase(name, page, size, sortProperty, sortDirection);
	}
	
	@GetMapping(value="")
	public @ResponseBody Page<Artist> getAllArtistByName(
			@RequestParam(value ="page") Integer page,
			@RequestParam(value ="size") Integer size,
			@RequestParam(value ="sortDirection")String sortDirection,
			@RequestParam(value ="sortProperty")String sortProperty
			){
				return artistService.findAllArtists(page, size, sortProperty, sortDirection);
	}
	
	@PostMapping(value="")
	public Artist createArtist(@RequestBody Artist artist) {
				return artistService.creerArtiste(artist);
	}
	
	@PutMapping(value="{id}")
	public Artist updateArtist(@RequestBody Artist artist, @PathVariable("id") Long id) {
		return artistService.updateArtiste(id, artist);
	}
	
	@DeleteMapping(value="{id}")
	public void deleteArtist(@PathVariable("id") Long id) {
		artistService.deleteArtist(id);
	}
	
}
