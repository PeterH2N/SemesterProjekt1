package Command;

import Buildings.Shop;
import Context.Context;
import Entity.Entity;


public class CommandSell extends BaseCommand implements Command {

    public CommandSell() {
        description = ""; // add description
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (guardEq(parameters, 2)) {
            System.out.println("Input is not a valid location");
            return;
        }

        /*if (parameters[0].equals("fuel")) {
            int amount = Integer.getInteger(parameters[1]);
            Shop shop = null;
            for (Entity entity : context.getCurrentSpace().entities) {
                if (entity instanceof Shop) {
                    shop = (Shop) entity;
                    break;
                }
            }
            if (shop == null)
                return;

            shop.buyFuel(context.player, amount);
        }*/

    }
}
