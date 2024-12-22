package mk.ukim.finki.wp.lab1.service;

import mk.ukim.finki.wp.lab1.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    public List<Album> findAll();

    Optional<Album> findById(Long albumId);
}
