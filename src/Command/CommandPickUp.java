package Command;

import Context.Context;
import World.Space;

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

        int[] pos = context.player.getPosition();
        Space space = context.world.map[pos[1]][pos[0]][pos[2]];
        context.player.pickUp(space);
        System.out.println("There are " + space.entities.size() + " items left in this space");
    }
}
