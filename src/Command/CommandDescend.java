package Command;

import Context.Context;


public class CommandDescend extends BaseCommand implements Command {

    public CommandDescend() {
        description = ""; // add description
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (guardEq(parameters, 0)) {
            System.out.println("Too many parameters");
            return;
        }

    }
}
