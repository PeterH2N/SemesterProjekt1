package Command;/* Command.Command for exiting program
 */

public class CommandExit extends BaseCommand implements Command {
  @Override
  public void execute (Context context, String command, String parameters[]) {
    context.makeDone();
  }
}
