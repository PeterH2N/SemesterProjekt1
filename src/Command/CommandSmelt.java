package Command;

import Context.Context;

public class CommandSmelt extends BaseCommand implements Command {

    public CommandSmelt() {
        description = ""; // add description
    }
    @Override
    public void execute (Context context, String command, String[] parameters) {
        if (guardEq(parameters, 2)) {
            System.out.println("Input is not a valid location");
            return;
        }

    }
}
