package com.example.demo;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Canvas canvas;
    public AnchorPane mainLayout;
    GraphicsContext gc;
    Rectangle myRect;
    AnimationTimer mainLoop;
    ArrayList<String> input;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        myRect = new Rectangle(10,10,30,40, Paint.valueOf("GREEN"), 5.1f);
        input = new ArrayList<>();
        Platform.runLater(() -> mainLayout.requestFocus());
        mainLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                //clear
                gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

                //update
                if (input.contains("w")) {
                    myRect.setY(myRect.getY() - myRect.getV());
                }

                if (input.contains("s")) {
                    myRect.setY(myRect.getY() + myRect.getV());
                }
                if (input.contains("a")) {
                    myRect.setX(myRect.getX() - myRect.getV());
                }
                if (input.contains("d")) {
                    myRect.setX(myRect.getX() + myRect.getV());
                }
                //render
                gc.setFill(myRect.getC());
                gc.fillRect(myRect.getX(), myRect.getY(), myRect.getW(), myRect.getH());
            }
        };
        mainLoop.start();
    }

    public void keyPressed(KeyEvent keyEvent) {
        if(!input.contains(keyEvent.getText())){
            input.add(keyEvent.getText());
        }
       myRect.update(keyEvent);
       clearCanvas();
        myRect.draw(gc);
    }

    private void clearCanvas() {
        gc.setFill(Paint.valueOf("WHITE"));
        gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
    }

    public void keyReleased(KeyEvent keyEvent) {
        input.remove(keyEvent.getText());
    }
}