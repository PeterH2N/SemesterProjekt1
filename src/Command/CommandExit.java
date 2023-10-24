/* Command for exiting program
 */
package Command;

import Context.Context;

public class CommandExit extends BaseCommand implements Command {
    public CommandExit() {
        description = "Quit the game (doesn't save)";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        context.makeDone();
    }
}
