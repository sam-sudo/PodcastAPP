package com.example.pruebatecnicamerlin.util;

import android.media.MediaPlayer;

public class MediaPlayerService {

    private MediaPlayer mp;

    public MediaPlayerService() {

    }

    public MediaPlayer getMp() {
        if(this.mp == null){
            this.mp = new MediaPlayer();
        }
        return mp;
    }
}
