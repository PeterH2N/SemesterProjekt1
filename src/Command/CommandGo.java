package Command;

import Context.Context;

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
            System.out.println("Moved to");
            context.player.printPosition();
        }
        else
            System.out.println("Could not move " + parameters[0]);
        // after having moved, we print the number of items in the space
        int[] p = context.player.getPosition();
        int wasteAmount = context.world.map[p[1]][p[0]][p[2]].entities.size(); // todo: subtract entities that are not items
        System.out.println("There are " + wasteAmount + " pieces of waste here");

    }
}
