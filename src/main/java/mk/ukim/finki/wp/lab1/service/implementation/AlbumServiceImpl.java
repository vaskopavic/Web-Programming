package mk.ukim.finki.wp.lab1.service.implementation;

import mk.ukim.finki.wp.lab1.model.Album;
import mk.ukim.finki.wp.lab1.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab1.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> findById(Long albumId) {
        return albumRepository.findById(albumId);
    }
}