package World;



public class AnimalIndeks {
    static int animalIndeks;

    AnimalIndeks(){
        animalIndeks = 0;
    }
    // Convert picked up trash to points in the indeks
    public static void trashToIndeks(int totalTrash, int layer){
        animalIndeks = animalIndeks + (totalTrash * layer);
    }

    public static int getAnimalIndeks() {
        return animalIndeks;
    }


}
