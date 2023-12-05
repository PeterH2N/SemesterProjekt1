package GameGraphics;

import Globals.Globals;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
        updateLabels();
    }

    @FXML
    private Button upgradeFuelCapacityButton;

    @FXML
    private TextField smeltTextField;

    @FXML
    private Button smeltButton;

    @FXML
    public Canvas gameImage;

    @FXML
    Label fuelCapacityLabel;

    void updateLabels() {
        double fuelLevel = DrawGame.context.player.sub.getFuel();
        double fuelCapacity = DrawGame.context.player.sub.getFuelCapacity();
        String fuelText = "Fuel: " + fuelLevel + "/" + fuelCapacity;
        fuelCapacityLabel.setText(fuelText);
    }

    public EventHandler<KeyEvent> anyKeyEvent = new EventHandler<KeyEvent>()
    {
        @Override
        public void handle(KeyEvent event)
        {
            updateLabels();
            DrawGame.setLayerImage();
            DrawGame.drawGame();

        }
    };

    public EventHandler<KeyEvent> movementHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            if (e.getCode() == KeyCode.W) {
                // move up
                DrawGame.context.player.move("north", DrawGame.context);
                DrawGame.context.player.sub.setRotation(0);
            }
            else if (e.getCode() == KeyCode.A) {
                DrawGame.context.player.move("west", DrawGame.context);
                DrawGame.context.player.sub.setRotation(-90);
            }
            else if (e.getCode() == KeyCode.S) {
                DrawGame.context.player.move("south", DrawGame.context);
                DrawGame.context.player.sub.setRotation(180);
            }
            else if (e.getCode() == KeyCode.D) {
                DrawGame.context.player.move("east", DrawGame.context);
                DrawGame.context.player.sub.setRotation(90);
            }
            else if (e.getCode() == KeyCode.P) {
                DrawGame.context.player.pickUp(DrawGame.context.world.currentScreen);
            }
            else return;

            e.consume();
        }
    };
    public ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
        DrawGame.pixelsPerTile = gameImage.getHeight() / (double)Globals.tilesPerScreen;
        DrawGame.drawGame();
    };
}