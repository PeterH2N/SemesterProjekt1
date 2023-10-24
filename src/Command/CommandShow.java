package Command;

import Context.Context;

public class CommandShow extends BaseCommand implements Command {

    public CommandShow() {
        description = ""; // add description
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (guardEq(parameters, 1)) {
            System.out.println("Input is not a valid location");
            return;
        }

    }
}
