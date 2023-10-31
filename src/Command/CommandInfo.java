package Command;

import Context.Context;

public class CommandInfo extends BaseCommand implements Command
{
    public CommandInfo() {
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
