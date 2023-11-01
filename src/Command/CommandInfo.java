package Command;

import Context.Context;
import Items.*;

public class CommandInfo extends BaseCommand implements Command {
    public CommandInfo() {
        description = ""; // add description
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (guardEq(parameters, 1)) {
            System.out.println("Input is not a valid inventory slot");
            return;
        }
        // Show info of item in inventory
        try {
            WasteItem currWasteItem = (WasteItem) context.player.sub.inventory.getItem(Integer.parseInt(parameters[0]) - 1);
            System.out.println(currWasteItem.getWasteItemInfo());
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("You don't have that many slots \uD83E\uDD14");
        }catch(NumberFormatException e){
            System.out.println("Please enter a number");
        }catch(NullPointerException ex){
            System.out.println("This slot is empty");
        }
    }
}
