package Command;

import Buildings.Building;
import Context.Context;
import Entity.Entity;
import Items.Item;
import World.Space;

import java.util.ArrayList;

public class CommandPickUp extends BaseCommand implements Command
{

    public CommandPickUp() {
        description = "Pick up the items in the current space"; // add description
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (guardEq(parameters, 0)) {
            System.out.println("Input is not valid");
            return;
        }

        Space current = context.getCurrentSpace();
        context.player.pickUp(current);
        int itemAmount = 0;
        ArrayList<Building> buildings = new ArrayList<>();
        for (Entity entity : current.entities) {
            if (entity instanceof Item) {
                itemAmount++;
            }
        }
        System.out.println("There are " + itemAmount + " items here");


    }
}
