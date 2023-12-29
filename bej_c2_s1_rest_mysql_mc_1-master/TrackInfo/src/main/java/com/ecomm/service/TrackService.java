package com.ecomm.service;

import com.ecomm.model.Track;

import java.util.List;

public interface TrackService {
    public Track addTrack(Track track);
    public List<Track> getAllTracks();
    public Track getTrack(int trackId);
    public boolean deleteTrack(int trackId);
    public List<Track> getTrackByArtist(String trackArtist);
    public Track updateTrackDetails(Track track,int trackId);

}
