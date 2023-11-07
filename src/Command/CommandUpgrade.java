package Command;

import Context.Context;

public class CommandUpgrade extends BaseCommand implements Command {

    public CommandUpgrade() {
        description = "Upgrade the submarine"; // add description
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (guardEq(parameters, 1)) {
            System.out.println("Input is not a valid upgrade");
            return;
        }

        if (parameters[0].equals("inventory")){
            context.player.sub.upgradeInventorylevel();
            System.out.println("Upgraded inventory capacity");
            return;
        }

        if (parameters[0].equals("fuel")){
            context.player.sub.upgradeFuelLevel();
            System.out.println("Upgraded fuel level");
            return;
        }

        System.out.println("Not a valid upgrade");
    }


}
