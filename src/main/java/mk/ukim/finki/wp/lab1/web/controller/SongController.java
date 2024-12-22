package mk.ukim.finki.wp.lab1.web.controller;

import mk.ukim.finki.wp.lab1.model.Album;
import mk.ukim.finki.wp.lab1.model.Artist;
import mk.ukim.finki.wp.lab1.model.Song;
import mk.ukim.finki.wp.lab1.service.AlbumService;
import mk.ukim.finki.wp.lab1.service.ArtistService;
import mk.ukim.finki.wp.lab1.service.AuthService;
import mk.ukim.finki.wp.lab1.service.SongService;
import mk.ukim.finki.wp.lab1.service.implementation.AlbumServiceImpl;
import mk.ukim.finki.wp.lab1.service.implementation.ArtistServiceImpl;
import mk.ukim.finki.wp.lab1.service.implementation.SongServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@Controller
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping({"/songs","/"})
    public String getSongsPage(@RequestParam(value = "search", required = false) String search, Model model) {

        List<Song> songList;

        if (search!=null && !search.isEmpty()) {
            songList = songService.findByTitle(search);
        }
        else{
            songList = songService.listSongs();
        }
        model.addAttribute("songList", songList);



        return "listSongs";
    }
    @GetMapping("/songs/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String addSongPage(Model model) {

        List<Album> albumList = albumService.findAll();
        List<Song> songList = songService.listSongs();

        model.addAttribute("songs", songList);
        model.addAttribute("albums",albumList);
        return "add-song";
    }


    @PostMapping("/songs/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveSong(@RequestParam(value = "id", required = false) Long id,
                           @RequestParam(value = "title") String title,
                           @RequestParam(value = "genre") String genre,
                           @RequestParam(value = "releaseYear") int releaseYear,
                           @RequestParam(value = "albumId") Long albumId) {

        Album album = albumService.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album with ID " + albumId + " not found"));

        if (id == null) {
            this.songService.save(title, genre, releaseYear, album);
        }
        else {
            Song song = songService.findById(id);
            if (song != null) {
                song.setTitle(title);
                song.setGenre(genre);
                song.setReleaseYear(releaseYear);
                song.setAlbum(album);

                songService.save(song);
            }
            else {
                throw new RuntimeException("Song with ID " + id + " not found");
            }
        }

        return "redirect:/songs";
    }

    @PostMapping("/songs/edit/{songId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editSong(@PathVariable Long songId,
                           @RequestParam(value = "title") String title,
                           @RequestParam(value = "genre") String genre,
                           @RequestParam(value = "releaseYear") int releaseYear,
                           @RequestParam(value = "albumId") Long albumId) {

        Song song = songService.findById(songId);
        if (song != null) {
            song.setTitle(title);
            song.setGenre(genre);
            song.setReleaseYear(releaseYear);
            Album album = albumService.findById(albumId)
                    .orElseThrow(() -> new IllegalArgumentException("Album not found"));
            song.setAlbum(album);

            songService.save(song);
        }
        else {
            throw new RuntimeException("Song not found");
        }

        return "redirect:/songs";
    }



    @GetMapping("/songs/edit-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditSongForm(@PathVariable Long id, Model model){

        Song song = songService.findById(id);

        List<Artist> artists = artistService.listArtists();
        List<Album> albums = albumService.findAll();

        model.addAttribute("song", song);
        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);
        return "add-song";
    }


    @GetMapping("/songs/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSong(@PathVariable Long id){

        songService.deleteById(id);
        return "redirect:/songs";
    }


    @GetMapping("/access-denied")
    public String showAccessDeniedPage(Model model){

        model.addAttribute("bodyContent", "access-denied");

        return "access-denied";
    }

}
