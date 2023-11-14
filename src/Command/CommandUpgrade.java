package Command;

import Buildings.WorkShop;
import Context.Context;
import Entity.Entity;
import World.*;

public class CommandUpgrade extends BaseCommand implements Command {

    public CommandUpgrade() {
        description = "Upgrade the submarine";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (guardEq(parameters, 1)) {
            System.out.println("Input is not a valid upgrade");
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
            System.out.println("Could not upgrade. You are not near a workshop!");
            return;
        }

        if (parameters[0].equals("inventory")){
            if (workShop.upgradeInventoryCapacity(context.player)) {
                System.out.println("Upgraded inventory capacity");
                return;
        }

        }

        if (parameters[0].equals("fuel")){
            if (workShop.upgradeFuelCapacity(context.player)) {
                System.out.println("Upgraded fuel capacity");
                return;
            }
        }

        System.out.println("Not a valid upgrade");
    }

}
