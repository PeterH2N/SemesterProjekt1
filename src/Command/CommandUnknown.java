package Command;/* Fallback for when a command is not implemented
 */
import Context.Context;
public class CommandUnknown extends BaseCommand implements Command {
  @Override
  public void execute (Context context, String command, String[] parameters) {
    System.out.println("Whoopsie, I don't understand '"+command+"' 😕");
  }
}
