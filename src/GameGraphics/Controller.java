package GameGraphics;

import Buildings.Shop;
import Buildings.WorkShop;
import Globals.Globals;
import Items.Item;
import Player.InventorySlot;
import World.AnimalIndeks;
import World.Quiz;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

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
        inventoryList.getSelectionModel().selectedItemProperty().addListener(selectionChangedListener);
    }

    @FXML
    private ListView<InventorySlot> inventoryList;

    @FXML
    Button upgradeInventoryButton;

    @FXML
    Button upgradeFuelButton;

    @FXML
    Button upgradePickupButton;

    @FXML
    Button upgradeOxygenButton;

    @FXML
    Button upgradeHullButton;
    @FXML
    Button sellButton;

    @FXML
    Button buyButton;

    @FXML
    Button smeltButton;

    @FXML
    public Canvas gameImage;

    @FXML
    Label fuelCapacityLabel;

    @FXML
    Label balanceLabel;

    @FXML
    Label oxygenLabel;

    @FXML
    Label messageLabel;

    @FXML
    Label scoreLabel;

    @FXML
    Label itemDescribtionLabel;

    void updateLabels() {
        // fuel
        int fuelLevel = (int)DrawGame.context.player.sub.getFuel();
        int fuelCapacity = (int)DrawGame.context.player.sub.getFuelCapacity();
        String fuelText = "Fuel: " + fuelLevel + "/" + fuelCapacity;
        fuelCapacityLabel.setText(fuelText);

        // player balance
        double balance = (double) DrawGame.context.player.getBalance() / 100;
        balanceLabel.setText("Balance: $" + balance);

        // oxygen level
        int oxygenLevel = (int) DrawGame.context.player.sub.getOxygen();
        int oxygenCapacity = (int) DrawGame.context.player.sub.getOxygenCapacity();
        oxygenLabel.setText("Oxygen: " + oxygenLevel + "/" + oxygenCapacity);

        // messageLabel
        messageLabel.setText(Globals.globalMessage);
        messageLabel.setTextFill(Color.WHITE);

        // score label
        scoreLabel.setText("Score: "+AnimalIndeks.getAnimalIndeks());


        // buttons
        String level = String.valueOf(DrawGame.context.player.sub.getInventoryCapacityLevel());
        if (DrawGame.context.player.sub.getInventoryCapacityLevel() == Globals.inventoryUpgrades.length - 1)
            level = "max";
        upgradeInventoryButton.setText("Upgrade Inventory Capacity |  " + level);

        level = String.valueOf(DrawGame.context.player.sub.getFuelCapacityLevel());
        if (DrawGame.context.player.sub.getFuelCapacityLevel() == Globals.fuelUpgrades.length - 1)
            level = "max";

        upgradeFuelButton.setText("Upgrade Fuel Capacity |  " + level);

        level = String.valueOf(DrawGame.context.player.sub.getPickupRadiusLevel());
        if (DrawGame.context.player.sub.getPickupRadiusLevel() == Globals.pickupRadiusUpgrades.length - 1)
            level = "max";

        upgradePickupButton.setText("Upgrade Pickup Radius |  " + level);

        level = String.valueOf(DrawGame.context.player.sub.getOxygenCapacityLevel());
        if (DrawGame.context.player.sub.getOxygenCapacityLevel() == Globals.oxygenUpgrades.length - 1)
            level = "max";

        upgradeOxygenButton.setText("Upgrade Oxygen Capacity |  " + level);

        level = String.valueOf(DrawGame.context.player.sub.getHullStrengthLevel());
        if (DrawGame.context.player.sub.getHullStrengthLevel() == Globals.hullStrengthUpgrades.length - 1)
            level = "max";

        upgradeHullButton.setText("Upgrade Hull Strength |  " + level);

        // disable if not near workshop
        if (DrawGame.context.player.sub.isByWorkShop(DrawGame.context.world.currentScreen) == null) {
            upgradeInventoryButton.setDisable(true);
            upgradeFuelButton.setDisable(true);
            upgradePickupButton.setDisable(true);
            upgradeOxygenButton.setDisable(true);
            upgradeHullButton.setDisable(true);
            smeltButton.setDisable(true);
        }
        else {
            upgradeInventoryButton.setDisable(false);
            upgradeFuelButton.setDisable(false);
            upgradePickupButton.setDisable(false);
            upgradeOxygenButton.setDisable(false);
            upgradeHullButton.setDisable(false);
            smeltButton.setDisable(false);
        }

        // make these disabled if not near shop
        if (DrawGame.context.player.sub.isByShop(DrawGame.context.world.currentScreen) == null) {
            sellButton.setDisable(true);
            buyButton.setDisable(true);
        }
        else {
            sellButton.setDisable(false);
            buyButton.setDisable(false);
        }

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
    private void handleDropButton(ActionEvent event) {
        int slotIndex = inventoryList.getSelectionModel().getSelectedIndex();
        if (slotIndex == -1)
            return;
        if (DrawGame.context.player.sub.inventory.slots[slotIndex] == null)
            return;

        int amount = DrawGame.context.player.sub.inventory.slots[slotIndex].amount;
        DrawGame.context.player.deleteItem(slotIndex, amount);
        updateFields();
        inventoryList.getSelectionModel().select(slotIndex);
    }

    @FXML
    private void handleSmeltButton(ActionEvent event) {
        int slotIndex = inventoryList.getSelectionModel().getSelectedIndex();
        if (slotIndex == -1)
            return;
        // if workshop is near
        WorkShop workShop = DrawGame.context.player.sub.isByWorkShop(DrawGame.context.world.currentScreen);
        if (workShop == null)
            return;

        workShop.smelt(DrawGame.context.player, slotIndex);
        updateFields();
        inventoryList.getSelectionModel().select(slotIndex);
    }

    @FXML
    private void handleBuyButton(ActionEvent event) {
        // if shop is near
        Shop shop = DrawGame.context.player.sub.isByShop(DrawGame.context.world.currentScreen);
        if (shop == null)
            return;

        shop.buyFuel(DrawGame.context.player, 10.0);
        updateFields();

    }

    @FXML
    private void handleSellButton(ActionEvent event) {
        int slotIndex = inventoryList.getSelectionModel().getSelectedIndex();
        if (slotIndex == -1)
            return;
        // if shop is near
        Shop shop = DrawGame.context.player.sub.isByShop(DrawGame.context.world.currentScreen);
        if (shop == null)
            return;

        int amount = DrawGame.context.player.sub.inventory.slots[slotIndex].amount;
        shop.sellItem(DrawGame.context.player, slotIndex, amount);
        updateFields();
        inventoryList.getSelectionModel().select(slotIndex);
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

    @FXML
    private void handleUpgradeOxygenCapacity(ActionEvent event) {
        WorkShop workShop = DrawGame.context.player.sub.isByWorkShop(DrawGame.context.world.currentScreen);
        if (workShop == null)
            return;

        workShop.upgradeOxygenCapacity(DrawGame.context.player);
        updateFields();
    }

    @FXML
    private void handleUpgradePickupRadius(ActionEvent event) {
        WorkShop workShop = DrawGame.context.player.sub.isByWorkShop(DrawGame.context.world.currentScreen);
        if (workShop == null)
            return;

        workShop.upgradePickupRadius(DrawGame.context.player);
        updateFields();
    }

    @FXML
    private void handleUpgradeHullStrength(ActionEvent event) {
        WorkShop workShop = DrawGame.context.player.sub.isByWorkShop(DrawGame.context.world.currentScreen);
        if (workShop == null)
            return;

        workShop.upgradeHullStrength(DrawGame.context.player);
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

            if(DrawGame.context.isDone()){
                World.Quiz quiz = new World.Quiz();
            }

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
                else if (e.getCode() == KeyCode.Q) {
                    DrawGame.context.player.move("up", DrawGame.context);
                }
                else if (e.getCode() == KeyCode.E) {
                    DrawGame.context.player.move("down", DrawGame.context);
                }
                else if (e.getCode() == KeyCode.P) {
                    DrawGame.context.player.pickUp(DrawGame.context.world.currentScreen);
                }
                else return;

                e.consume();

                if(DrawGame.context.player.sub.getFuel() <= 0){
                    DrawGame.context.makeDone();
                }
        }
    };

    public ChangeListener<InventorySlot> selectionChangedListener = (observable, oldValue, newValue) -> {
        Item item = ((InventorySlot)newValue).item;
        itemDescribtionLabel.setText(item.getDescription());
    };

    public ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
        DrawGame.pixelsPerTile = gameImage.getHeight() / (double)Globals.tilesPerScreen;
        DrawGame.drawGame();
    };
}