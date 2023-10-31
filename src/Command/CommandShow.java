package Command;

import Context.Context;

public class CommandShow extends BaseCommand implements Command {

    public CommandShow() {
        description = ""; // add description
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (guardEq(parameters, 1)) {
            System.out.println("Input is not valid");
            return;
        }

        // inventory
        if (parameters[0].equals("inventory")) {
            context.player.sub.inventory.printInventory();
        }
        // fuel
        if (parameters[0].equals("fuel")) {
            System.out.println("Fuel level: " + context.player.sub.getFuelLevel() + " litr");
        }

    }
}
