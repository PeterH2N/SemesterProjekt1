package Command;

import Context.Context;

public class CommandSmelt extends BaseCommand implements Command {

    public CommandSmelt() {
        description = ""; // add description
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        // expected input parameters: Item material key, Item amount
        if (guardEq(parameters, 2)) {
            System.out.println("Input is not valid");
            return;
        }
        try {
        if (context.player.sub.inventory.removeItem(parameters[0], Integer.parseInt(parameters[1]))){

        }
        } catch (NumberFormatException ex){
            System.out.println("Input amount is not a number");
        }
    }




}
