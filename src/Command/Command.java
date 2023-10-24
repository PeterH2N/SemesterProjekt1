package Command;/* Command.Command interface
 */

import Context.*;


public interface Command {
    void execute(Context context, String command, String parameters[]);

    String getDescription();
}

