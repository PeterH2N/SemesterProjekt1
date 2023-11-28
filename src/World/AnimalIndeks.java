package World;



public class AnimalIndeks {
    static int animalIndeks;

    AnimalIndeks(){
        animalIndeks = 0;
    }
    // Convert picked up trash to points in the indeks
    public static void trashToIndeks(int totalTrash){
        animalIndeks += totalTrash;
    }

    public static int getAnimalIndeks() {
        return animalIndeks;
    }
}
