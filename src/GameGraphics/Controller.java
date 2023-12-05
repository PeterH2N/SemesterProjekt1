package GameGraphics;

import Buildings.WorkShop;
import Globals.Globals;
import Player.InventorySlot;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import Items.Item;

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
        updateFields();
    }

    @FXML
    private ListView<InventorySlot> inventoryList;

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

    void updateFields() {
        updateLabels();
        initInventoryList();
    }

    void initInventoryList() {
        inventoryList.getItems().clear();
        // add all slots
        for (InventorySlot slot : DrawGame.context.player.sub.inventory.slots) {
            inventoryList.getItems().add(slot);
        }

    }
    @FXML
    private void handleDeleteItemButton(ActionEvent event) {
        int slotIndex = inventoryList.getSelectionModel().getSelectedIndex();
        DrawGame.context.player.deleteItem(slotIndex, 1);
        updateFields();
    }

    @FXML
    private void handleSmeltButton(ActionEvent event) {
        int slotIndex = inventoryList.getSelectionModel().getSelectedIndex();
        // if workshop is near
        WorkShop workShop = DrawGame.context.player.sub.isByWorkShop(DrawGame.context.world.currentScreen);
        if (workShop == null)
            return;

        workShop.smelt(DrawGame.context.player, slotIndex);
        updateFields();
    }

    @FXML
    private void handleUpgradeInventory(ActionEvent event) {
        WorkShop workShop = DrawGame.context.player.sub.isByWorkShop(DrawGame.context.world.currentScreen);
        if (workShop == null)
            return;

        workShop.upgradeInventoryCapacity(DrawGame.context.player);
        updateFields();
    }

    @FXML
    private void handleUpgradeFuelCapacity(ActionEvent event) {
        WorkShop workShop = DrawGame.context.player.sub.isByWorkShop(DrawGame.context.world.currentScreen);
        if (workShop == null)
            return;

        workShop.upgradeFuelCapacity(DrawGame.context.player);
        updateFields();
    }

    public EventHandler<KeyEvent> anyKeyEvent = new EventHandler<KeyEvent>()
    {
        @Override
        public void handle(KeyEvent event)
        {
            updateFields();
            DrawGame.setLayerImage();
            DrawGame.drawGame();

        }
    };

    public EventHandler<KeyEvent> movementHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            if (e.getCode() == KeyCode.W) {
                // move up
                DrawGame.context.player.move("north", DrawGame.context.world);
                DrawGame.context.player.sub.setRotation(0);
            }
            else if (e.getCode() == KeyCode.A) {
                DrawGame.context.player.move("west", DrawGame.context.world);
                DrawGame.context.player.sub.setRotation(-90);
            }
            else if (e.getCode() == KeyCode.S) {
                DrawGame.context.player.move("south", DrawGame.context.world);
                DrawGame.context.player.sub.setRotation(180);
            }
            else if (e.getCode() == KeyCode.D) {
                DrawGame.context.player.move("east", DrawGame.context.world);
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