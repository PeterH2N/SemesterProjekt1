package World;



public class AnimalIndeks {
    int animalIndeks;

    AnimalIndeks(){
        this.animalIndeks = 0;
    }
    // Convert picked up trash to points in the indeks
    public void trashToIndeks(int totalTrash){
        animalIndeks += totalTrash;
    }


}
