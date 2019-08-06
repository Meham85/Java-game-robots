package com.kodilla.simplejavagame;

import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;

public class RobotCreator {
    String robotName;
    Image image;
    Boolean active;

    public RobotCreator(String robotName, Image image, Boolean active) {
        this.robotName = robotName;
        this.image = image;
        this.active = active;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
