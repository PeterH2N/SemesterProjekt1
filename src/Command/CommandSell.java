package Command;

import Context.Context;


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

    }
}
