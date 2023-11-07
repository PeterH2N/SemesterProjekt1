package Command;

import Context.Context;

public class CommandUpgrade extends BaseCommand implements Command {

    public CommandUpgrade() {
        description = "Upgrade the submarine"; // add description
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (guardEq(parameters, 1)) {
            System.out.println("Input is not a valid location");
            return;
        }

    }
}
