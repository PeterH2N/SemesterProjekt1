/* Main class for launching the game
 */

import Command.*;
import Context.Context;

import java.util.Scanner;

class Game {
    //static World    world    = new World();
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

    public static void main(String[] args) {
        System.out.println("Welcome to our game!");

        initRegistry();

        while (!context.isDone()) {
            System.out.print("> ");
            String line = scanner.nextLine();
            registry.dispatch(line);
        }
        System.out.println("Game Over 😥");
    }
}
