package mk.ukim.finki.wp.lab1.service;

import mk.ukim.finki.wp.lab1.model.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> listArtists();
    Artist findById(Long id);
}
