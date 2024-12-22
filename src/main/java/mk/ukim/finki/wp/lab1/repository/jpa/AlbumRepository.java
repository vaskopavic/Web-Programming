package mk.ukim.finki.wp.lab1.repository.jpa;

import mk.ukim.finki.wp.lab1.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

}
