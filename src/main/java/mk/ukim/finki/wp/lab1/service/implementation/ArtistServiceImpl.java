package mk.ukim.finki.wp.lab1.service.implementation;

import mk.ukim.finki.wp.lab1.model.Artist;
import mk.ukim.finki.wp.lab1.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab1.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) {
        return artistRepository.findById(id).orElse(null);
    }
}
