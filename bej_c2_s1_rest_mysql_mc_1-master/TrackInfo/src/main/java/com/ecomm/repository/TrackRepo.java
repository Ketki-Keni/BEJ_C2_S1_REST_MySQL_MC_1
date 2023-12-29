package com.ecomm.repository;

import com.ecomm.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepo extends JpaRepository<Track,Integer> {
    List<Track> findByArtist(String trackArtist);
}
