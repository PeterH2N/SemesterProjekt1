package GameGraphics;

import Context.Context;
import GameGraphics.DrawGame;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

public class Controller
{
    public Controller() {
        System.out.println("controller constructed");


    }

    @FXML
    public void initialize() {
        gameImage.getGraphicsContext2D().setImageSmoothing(false);
        DrawGame.canvas = gameImage;
        DrawGame.setLayerImage();
    }

    @FXML
    private Button heyButton;

    @FXML
    public Canvas gameImage;

    @FXML
    protected void onHelloButtonClick()
    {
        heyButton.setStyle("-fx-background-color: green");

    }
}