package com.ipiecoles.java.eval.mdd050.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.Objects;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlbumId")
    private Long id;

    @NotEmpty
    @Column(name = "Title")
    private String title;

    @ManyToOne
    @JoinColumn( name = "ArtistId" )
    private Artist artist;

    public Album() {
    }

    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id) &&
                Objects.equals(title, album.title) &&
                Objects.equals(artist, album.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, artist);
    }
}
