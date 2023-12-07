/* Main class for launching the game
 */

import Command.*;
import Context.Context;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.*;

import GameGraphics.Controller;
import GameGraphics.DrawGame;
import Globals.Globals;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Game extends Application {
    static Context context = new Context();
    static Command fallback = new CommandUnknown();
    static Registry registry = new Registry(context, fallback);
    static Scanner scanner = new Scanner(System.in);

    private static void initRegistry() {
        registry.register("quit", new CommandExit());
        registry.register("go", new CommandGo());
        registry.register("show", new CommandShow());
        registry.register("buy", new CommandBuy());
        registry.register("sell", new CommandSell());
        registry.register("smelt", new CommandSmelt());
        registry.register("upgrade", new CommandUpgrade());
        registry.register("pickup", new CommandPickUp());
        registry.register("info", new CommandInfo());
        registry.register("help", new CommandHelp(registry));


    }

    @Override
    public void start(Stage stage) throws IOException
    {
        double width = 700;
        double height = 500;
        // give the graphics renderer the context
        DrawGame.context = context;
        // load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("FXML/view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);

        // redraw the game when window changes size
        Controller controller = fxmlLoader.getController();
        stage.widthProperty().addListener(controller.stageSizeListener);
        stage.heightProperty().addListener(controller.stageSizeListener);

        // add event handler
        stage.addEventHandler(KeyEvent.KEY_PRESSED, controller.movementHandler);
        stage.addEventHandler(KeyEvent.ANY, controller.anyKeyEvent);

        stage.setTitle("Semesterprojekt");
        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void stop() {
        // delete all the map generation files
        File dir = new File(Globals.mapGenPath + "Images");
        for (File file: dir.listFiles()) {
            if (!file.isDirectory() && !file.getName().equals(".gitignore"))
                file.delete();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    /*public static void main(String[] args) {
        System.out.println("Welcome to our game!");

        initRegistry();

        while (!context.isDone()) {
            System.out.print("> ");
            String line = scanner.nextLine();
            registry.dispatch(line);
        }
        System.out.println("Game Over 😥");
    }*/
}
