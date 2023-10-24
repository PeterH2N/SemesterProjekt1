package Command;

import Context.Context;

public class CommandBuy extends BaseCommand implements Command {

    public CommandBuy() {
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
