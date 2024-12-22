package mk.ukim.finki.wp.lab1.web.controller;

import mk.ukim.finki.wp.lab1.model.Artist;
import mk.ukim.finki.wp.lab1.model.Song;
import mk.ukim.finki.wp.lab1.service.ArtistService;
import mk.ukim.finki.wp.lab1.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/song/song-details")
public class SongDetailsController {

    private final SongService songService;
    private final ArtistService artistService;

    public SongDetailsController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping
    public String showSongDetails(@RequestParam(value = "id", required = false) Long id, Model model) {
        Song song = id != null ? songService.findById(id) : songService.listSongs().stream().findFirst().orElse(null);

        if (song == null) {
            model.addAttribute("error", "Song not found!");
            return null;
        }

        model.addAttribute("song", song);
        model.addAttribute("count", songService.counter(song.getId()));
        return "songDetails";
    }

    @PostMapping
    public String saveSongDetails(@RequestParam(value = "id") Long id,
                                  @RequestParam(value = "artistId", required = false) Long artistId,
                                  Model model) {
        Song song = songService.findById(id);

        if (song == null) {
            model.addAttribute("error", "Song not found!");
            return null;
        }

        if (artistId != null) {
            Artist artist = artistService.findById(artistId);
            if (artist == null) {
                model.addAttribute("error", "Artist not found!");
                return null;
            }

            if (!song.getPerformers().contains(artist)) {
                song.addPerformer(artist);
                songService.save(song);
            }
        }

        model.addAttribute("song", song);
        model.addAttribute("count", songService.counter(song.getId()));
        return "songDetails";
    }

    @PostMapping("/add-comment")
    public String addComment(@RequestParam(value = "id") Long id,
                             @RequestParam(value = "comment") String text,
                             Model model) {
        Song song = songService.findById(id);

        songService.addCommentToSong(id, text);

        model.addAttribute("song", song);
        model.addAttribute("count", songService.counter(id));
        return "songDetails";
    }
}
