package Globals;

public class Messages {
    private static final String introMessage = "Hello player! Welcome to Marine Junk Removal! \n" +
            "The ocean is full of junk that needs to be removed. \n" +
            "Please use this trusty submarine to collect the junk from the water. \n" +
            "The marine life needs you to get better at cleaning up the ocean! \n"+
            "You can use the junk to upgrade the sub or sell it for money to buy fuel \n" +
            "You should try to find a 'Workshop' and a 'Shop' \n" +
            "Hint: Press 'P' to pickup junk";
    public static String getIntroMessage() {
        return introMessage;
    }
    public static final String inventoryFullMessage = "You dont have space for that!";
    public static final String lowFuelMessage = "Low fuel! find a 'Shop' fast!";
}
