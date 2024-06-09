package com.memory.Models;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class GameTimer {
    private Timeline timeline;
    int seconds=0;
    int minutes=0;


    public void start(Label label) {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> incrementLabel(label)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.playFromStart();
    }

    public void incrementLabel(Label label) {
        seconds++;
        if (seconds==60) {
            seconds=0;
            minutes++;
        }
        if (seconds > 9) {
            label.setText("0"+ minutes + ":" + seconds);
        } else {
            label.setText("0"+ minutes + ":0" + seconds);
        }
    }

    public void stop() {
        timeline.stop();
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}

