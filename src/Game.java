/* Main class for launching the game
 */

import Command.*;
import Context.Context;

import java.io.IOException;
import java.util.Scanner;

import GameGraphics.Controller;
import GameGraphics.DrawGame;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> DrawGame.drawGame();
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);

        stage.setTitle("Semesterprojekt");
        stage.setScene(scene);
        stage.show();
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
