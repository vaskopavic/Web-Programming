//package mk.ukim.finki.wp.lab1.repository.inMemory;
//
//import mk.ukim.finki.wp.lab1.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab1.model.Album;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class InMemoryAlbumRepository {
//
//
//    public List<Album> findAll(){
//        return DataHolder.albumList;
//    }
//
//    public Optional<Album> findAlbumById(Long albumId) {
//        return DataHolder.albumList.stream().filter(a -> a.getId().equals(albumId)).findFirst();
//    }
//}