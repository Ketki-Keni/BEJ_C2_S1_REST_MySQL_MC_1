/*
 * Author : Ketki Keni
 * Date : 26-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.ecomm.controller;

import com.ecomm.model.Track;
import com.ecomm.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TrackController {
    TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    //Uri : http://localhost:8082/api/v1/track : Method : Post

    @PostMapping("/track")
    public ResponseEntity<?> addCustomer(@RequestBody Track track){

        Track newtrack=trackService.addTrack(track);
        System.out.println("== addCustomer() Method == | New Customer"+newtrack);

        if(newtrack!=null){
            return new ResponseEntity<Track>(newtrack, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<String>("Error Occurred While Inserting",HttpStatus.NOT_FOUND);
        }
    }

    //Uri : http://localhost:8082/api/v1/tracks : Method : Get

    @GetMapping("/tracks")
    public ResponseEntity<?> getAllCustomers(){
        List<Track> allTracks=trackService.getAllTracks();

        if(allTracks.size()>0){
            return new ResponseEntity<List<Track>>(allTracks, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("No tracks Found",HttpStatus.NOT_FOUND);
        }
    }

    //Uri : http://localhost:8082/api/v1/track/102 : Method : Get

    @GetMapping("/track/{trackId}")
    public ResponseEntity<?> getTrack(@PathVariable int trackId){
        Track track=trackService.getTrack(trackId);
        if(track!=null){
            return new ResponseEntity<Track>(track, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No track Found",HttpStatus.NOT_FOUND);
        }
    }

    //Uri : http://localhost:8082/api/v1/trackbyartist/BTS : Method : Get

    @GetMapping("/trackbyartist/{trackArtist}")
    public ResponseEntity<?> getTrackByArtist(@PathVariable String trackArtist){
        List<Track> allTracks=trackService.getTrackByArtist(trackArtist);
        System.out.println("Get tracks by Artist name :"+trackArtist);
        if(allTracks.size()>0){
            return new ResponseEntity<List<Track>>(allTracks, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("No Customers Found",HttpStatus.NOT_FOUND);
        }
    }

    //Uri : http://localhost:8082/api/v1/track/102 : Method : Delete

    @DeleteMapping("/track/{trackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable int trackId){
        if(trackService.deleteTrack(trackId)){
            return new ResponseEntity<String>("Track Deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Error Occurred",HttpStatus.NOT_FOUND);
        }
    }

    //Uri : http://localhost:8082/api/v1/customer/101 : Method : Put

    @PutMapping("/track/{trackId}")
    public ResponseEntity<?> updateCustomer(@RequestBody Track newTrackDetail,@PathVariable int trackId){

        Track updatedTrack=trackService.updateTrackDetails(newTrackDetail,trackId);

        if(updatedTrack!=null){
            return new ResponseEntity<Track>(updatedTrack, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Error Occurred",HttpStatus.NOT_FOUND);
        }
    }
}
