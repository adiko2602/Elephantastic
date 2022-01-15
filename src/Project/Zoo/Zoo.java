package Project.Zoo;

import Project.Animals.Animals;
import Project.Output;
import java.util.ArrayList;

public class Zoo {
    private ArrayList<Animals> animals = new ArrayList<>();         // List for animals in zoo
    private int zooNumberOfActualVisitor = 0;                       // Number of visitors in zoo
    private int zooDirtiness = 0;                                   // Number of zoo dirtiness
    private int zooAttractiveness = 0;                              // Number of zoo attractiveness

    public void GetAnimalsList() {                                          // Method for print all animals in zoo
        if(!this.animals.isEmpty()) {                                       // Check if list is empty
            int i = 1;                                                      // Int for iterate
            for (Animals animal : this.animals) {                           // for loop for all animals in zoo
                Output.Set("[" + (i++) + "] " + animal.GetAnimalName());    // Output
            }
        } else {
            Output.Set("Zoo is empty!");                            // If zoo is empty
        }
    }

    public Animals GetAnimal(int number) {                          // Method for pick animal from zoo
        if(!this.animals.isEmpty()) {                               // Check if zoo is empty
            if(number >= 0 && number < this.animals.size()) {       // Check if selected number is correct
                return animals.get(number);                         // Get animal at index
            } else {
                Output.Set("Selected number of animal is wrong!");  // If wrong number
            }
        } else {
            Output.Set("Zoo is empty!");                            // If empty
        }
        return null;
    }

    public void AddAnimalToList(Animals animal) {                               // Method for add animal to zoo
        this.animals.add(animal);                                               // Add animal
        Output.Set("Animal: " + animal.GetAnimalName() + " was added to zoo."); // Output
    }

    public void RemoveAnimalFromList(int animalNumber) {                        // Method for remove animal from zoo
        if(!this.animals.isEmpty()) {                                           // Check if zoo is empty
            if(animalNumber >= 0 && animalNumber < this.animals.size()) {       // Check if number is correct
                Output.Set("Animal: " + this.animals.get(animalNumber).GetAnimalName() + " was removed from zoo.");     // Output
                this.animals.remove(animalNumber);                              // Remove animal from zoo at index
            } else {
                Output.Set("Selected number of animal is wrong!");               // Output
            }
        } else {
            Output.Set("Zoo is empty!");                                         // Output
        }
    }

    public void IncreaseZooDirtiness() {        // Method for zoo dirtiness increase
        this.zooDirtiness++;
    }

    public void DecreaseZooDirtiness() {        // Method for zoo dirtiness decrease
        this.zooDirtiness--;
    }

    public void IncreaseZooAttractiveness() {   // Method for zoo attractiveness increase
        this.zooAttractiveness++;
    }

    public void DecreaseZooAttractiveness() {   // Method for zoo attractiveness increase
        this.zooAttractiveness--;
    }

    public void VisitorLetIn() {                // Method for visitor number increase
        this.zooNumberOfActualVisitor++;
    }

    public void VisitorLetOut() {               // Method for visitor number decrease
        this.zooNumberOfActualVisitor--;
    }

    public int GetZooNumberOfActualVisitor() {  // Method for visitor actual visitors number in zoo
        return this.zooNumberOfActualVisitor;
    }

    public int GetZooNumberOfAnimal() {         // Method for visitor actual animals number in zoo
        return this.animals.size();
    }
    public int GetZooDirtiness() {
        return this.zooDirtiness;
    }

    public String GetZooName() {
        return "Elephantastic";
    }
}
