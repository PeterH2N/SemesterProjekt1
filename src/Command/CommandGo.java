package Command;

import Buildings.Building;
import Context.Context;
import Entity.Entity;
import Items.Item;
import World.Space;

import java.util.ArrayList;

public class CommandGo extends BaseCommand implements Command {

    public CommandGo() {
        description = "Go north, south, east, west, up or down to move around the map";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (guardEq(parameters, 1)) {
            System.out.println("Input is not a valid location");
            return;
        }
        if (context.player.move(parameters[0], context.world)) {
            System.out.print("Moved to ");
            context.player.printPosition();
        }
        else
            System.out.println("Could not move " + parameters[0]);
        // after having moved, we print the number of items in the space
        /*Space current = context.getCurrentSpace();
        int itemAmount = 0;
        ArrayList<Building> buildings = new ArrayList<>();
        for (Entity entity : current.entities) {
            if (entity instanceof Item) {
                itemAmount++;
            }
            if (entity instanceof Building) {
                buildings.add((Building)entity);
            }
        }

        System.out.println("\nThere are " + itemAmount + " items here");

        // if the space contains a building, we print a description
        for (Building building : buildings) {
            System.out.println("There is a " + building.getName() + " here");
        }*/

    }
}
