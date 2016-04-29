package com.example.notebookforme.model;

/**
 * Created by Kreliou on 19/04/2016.
 */
public class Movie {

    public String title;

    public String overview;

    public Double vote;

    public String poster;

    public String release;

    public Movie(){

    }

    public String getOverview() {
        return overview;
    }

    public Double getVote() {
        return vote;
    }

    public String getPoster() {
        return poster;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release1) {release = release1; }

    public void cutReleaseHours()
    {
        release = release.substring(0, Math.min(release.length(), 10));
    }

    public String getTitle(){
        return title;
    }



}
