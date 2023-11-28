package Command;

import Context.Context;
import World.AnimalIndeks;
import World.World;

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
            System.out.println("Fuel level: " + context.player.sub.getFuel() + " / " + context.player.sub.getFuelCapacity() + " litres");
        }
        // Animal indeks
        if (parameters[0].equals("animal")) {
            System.out.println("Animal Indeks: " + AnimalIndeks.getAnimalIndeks());
        }

    }
}
