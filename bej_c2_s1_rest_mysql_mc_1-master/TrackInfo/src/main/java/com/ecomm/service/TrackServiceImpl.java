/*
 * Author : Ketki Keni
 * Date : 26-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.ecomm.service;

import com.ecomm.model.Track;
import com.ecomm.repository.TrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    TrackRepo trackRepo;
    @Autowired
    public TrackServiceImpl(TrackRepo trackRepo) {
        this.trackRepo = trackRepo;
    }

    @Override
    public Track addTrack(Track track) {
        return trackRepo.save(track);
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepo.findAll();
    }

    @Override
    public Track getTrack(int trackId) {
        return trackRepo.findById(trackId).get();
    }

    @Override
    public boolean deleteTrack(int trackId) {
        if(trackRepo.findById(trackId).isPresent()){
            Track track=trackRepo.findById(trackId).get();
            trackRepo.delete(track);
            return true;
        }
        return false;
    }

    @Override
    public List<Track> getTrackByArtist(String trackArtist) {
        return trackRepo.findByArtist(trackArtist);
    }

    @Override
    public Track updateTrackDetails(Track track, int trackId) {
        if(trackRepo.findById(trackId).isPresent()){
            Track existingTrack=trackRepo.findById(trackId).get();
            if(track.getTrackName()!=null){
                existingTrack.setTrackName(track.getTrackName());
            }
            if(track.getTrackArtist() !=null){
                existingTrack.setTrackArtist(track.getTrackArtist());
            }
            return trackRepo.save(existingTrack);
        }
        return null;
    }
}
