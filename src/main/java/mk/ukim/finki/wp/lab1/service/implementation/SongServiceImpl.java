package mk.ukim.finki.wp.lab1.service.implementation;

import mk.ukim.finki.wp.lab1.model.Album;
import mk.ukim.finki.wp.lab1.model.Artist;
import mk.ukim.finki.wp.lab1.model.Comment;
import mk.ukim.finki.wp.lab1.model.Song;
import mk.ukim.finki.wp.lab1.repository.jpa.CommentRepository;
import mk.ukim.finki.wp.lab1.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab1.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final CommentRepository commentRepository;

    public SongServiceImpl(SongRepository songRepository, CommentRepository commentRepository) {
        this.songRepository = songRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        Song existingSong = songRepository.findById(song.getId()).orElseThrow(() -> new IllegalArgumentException("Song not found"));

        existingSong.addPerformer(artist);
        songRepository.save(existingSong);
        return artist;
    }

    @Override
    public List<Song> findByTitle(String title) {
        return songRepository.findAll().stream().filter(s -> s.getTitle().toLowerCase().contains(title)).toList();
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    public void save(String title, String genre, Integer releaseYear, Album album) {

        Song song = new Song();
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        songRepository.save(song);
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }

    @Override
    public int counter(Long id) {

        Song song = songRepository.findById(id).orElse(null);

        if (song != null) {
            int count = song.getCounter() + 1;
            song.setCounter(count);
            songRepository.save(song);
            return count;
        }
        return 0;
    }

    @Override
    public void save(Song song) {
        songRepository.save(song);
    }

    @Override
    public void addCommentToSong(Long songId, String text) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new IllegalArgumentException("Song not found"));

        Comment comment = new Comment();
        comment.setText(text);
        comment.setSong(song);

        song.getComment().add(comment);
        commentRepository.save(comment);
    }
}
