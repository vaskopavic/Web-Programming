//package mk.ukim.finki.wp.lab1.repository.inMemory;
//
//import mk.ukim.finki.wp.lab1.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab1.model.Album;
//import mk.ukim.finki.wp.lab1.model.Artist;
//import mk.ukim.finki.wp.lab1.model.Song;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Repository
//public class InMemorySongRepository {
//
//    public List<Song> findAll(){
//        return DataHolder.songsList;
//    }
//
//    public Song findByTrackId(String trackId){
//        return DataHolder.songsList.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst().orElse(null);
//    }
//
//    public List<Song> findByTitle(String title){
//        return DataHolder.songsList.stream().filter(i -> i.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
//    }
//
//
//
//
//    public Artist addArtistToSong(Artist artist, Song song) {
//        DataHolder.songsList.stream().filter(i-> i.getTrackId().equals(song.getTrackId())).findFirst().get().addPerformer(artist);
//        return artist;
//    }
//
//    public void save(String title, String genre, int releaseYear, Album album){
//        Song song = new Song(title, genre, releaseYear, album);
//        DataHolder.songsList.removeIf(s -> Objects.equals(s.getTitle(), title));
//        DataHolder.songsList.add(song);
//    }
//
//    public void deleteById(String id) {
//        DataHolder.songsList.removeIf(s -> s.getTrackId().equals(id));
//    }
//
//    public int counter(String id) {
//
//        int count = findByTrackId(id).getCounter();
//        findByTrackId(id).setCounter(++count);
//        return count;
//
////        return DataHolder.songsList.stream()
////                .filter(s -> s.getTrackId().equals(trackId))
////                .findFirst()
////                .map(song -> {
////                    song.setCounter(song.getCounter() + 1);
////                    return song.getCounter(); // Return the updated counter
////                })
////                .orElse(0);
//
//    }
//}
