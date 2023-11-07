package Command;

import Buildings.WorkShop;
import Context.Context;
import World.*;
import Entity.*;

public class CommandSmelt extends BaseCommand implements Command {

    public CommandSmelt() {
        description = ""; // add description
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        // expected input parameters: Item material key, Item amount
        if (guardEq(parameters, 1)) {
            System.out.println("Input is not valid");
            return;
        }

        // assert that input is an integer
        int slotIndex = 0;
        try {
        slotIndex = Integer.parseInt(parameters[0]);

        } catch (NumberFormatException ex){
            System.out.println("Input amount is not a number");
            return;
        }
        slotIndex--;
        int invCapacity = context.player.sub.inventory.slots.length;
        if (slotIndex > invCapacity || slotIndex < 0) {
            System.out.println("Input outside of inventory capacity");
            return;
        }
        // make sure that player is in a space that contains a workshop
        Space current = context.getCurrentSpace();
        WorkShop workShop = null;
        for (Entity entity : current.entities) {
            if (entity instanceof WorkShop) {
                workShop = (WorkShop)entity;
                break;
            }
        }
        if (workShop == null) {
            System.out.println("Could not smelt items. You are not near a workshop!");
            return;
        }

        if (workShop.smelt(context.player, slotIndex)){
            System.out.println("Smelted items");
        }
    }




}
