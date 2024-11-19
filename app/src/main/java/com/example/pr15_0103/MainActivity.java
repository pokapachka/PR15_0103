package com.example.pr15_0103;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        fileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/record.3gpp";
    }
    private void releaseRecorder(){
        if(mediaRecorder != null){
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
    public void recordStart(View v) {
        try {
            releaseRecorder();
            File outFile = new File(fileName);
            if (outFile.exists()) {
                outFile.delete();
            }
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(fileName);
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void recordStop(View v){
        if(mediaRecorder != null) {
            mediaRecorder.stop();
        }
    }
    private void releasePlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    public void playStart(View v){
        try{
            releasePlayer();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}