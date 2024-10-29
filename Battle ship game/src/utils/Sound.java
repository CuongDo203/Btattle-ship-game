package utils;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

    private final URL BACKGROUND;
    private final URL CLICK;
    private final URL PLAY_MUSIC;
    private final URL DRAG;
    private final URL MISS;
    private final URL HIT;
    private final URL SHIP_SHUNK;
    private Clip clip;
    
    public Sound() {
        this.BACKGROUND = this.getClass().getClassLoader().getResource("assets/audio/background.wav");
        this.CLICK = this.getClass().getClassLoader().getResource("assets/audio/button_click.wav");
        this.PLAY_MUSIC = this.getClass().getClassLoader().getResource("assets/audio/play.wav");
        this.DRAG = this.getClass().getClassLoader().getResource("assets/audio/drag.wav");
        this.MISS = this.getClass().getClassLoader().getResource("assets/audio/object-drops-in-water.wav");
        this.HIT = this.getClass().getClassLoader().getResource("assets/audio/explosion-sound-effect.wav");
        this.SHIP_SHUNK = this.getClass().getClassLoader().getResource("assets/audio/wooden-ship-break.wav");
    }

    public void soundBackground() {
        playSound(BACKGROUND, -20.0f, true);
    }
    
    public void soundButtonClick() {
        playSound(CLICK, 0f, false);
    }
    
    public void soundPlay() {
        playSound(PLAY_MUSIC, 0.f, true);
    }
    
    public void soundDrag() {
        playSound(DRAG, 0, false);
    }
    
    public void soundMissShot() {
        playSound(MISS, 0, false);
    }
    
    public void soundHitShot() {
        playSound(HIT, 0, false);
    }
    
    public void soundShipSunk() {
        playSound(SHIP_SHUNK, 0, false);
    }
    
    public void stop() {
        if(clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            clip = null;
        }
    }
    
    private void playSound(URL url, float volume, boolean loop) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);

            // Điều chỉnh âm lượng
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);  // Thiết lập âm lượng

            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                }
            });

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);  // Nếu cần lặp lại âm thanh
            }
            audioIn.close();
            clip.start();

        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    
}
