package Project.Zoo;

import Project.Animals.Animals;
import Project.Output;
import Project.Visitors.Visitor;

import java.util.ArrayList;

public class Zoo {
    private final ArrayList<Visitor> visitors = new ArrayList<>();
    private final ArrayList<Animals> animals = new ArrayList<>();         // List for animals in zoo
    //private int zooNumberOfActualVisitor = 0;                       // Number of visitors in zoo
    private int zooDirtiness = 2;                                   // Number of zoo dirtiness
    private int zooAttractiveness = 5;                              // Number of zoo attractiveness

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
                Output.Set("Animal with selected number does not exist!");  // If wrong number
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
                Output.Set("Animal with selected number does not exist!");               // Output
            }
        } else {
            Output.Set("Zoo is empty!");                                         // Output
        }
    }

    public void IncreaseZooDirtiness() {        // Method for zoo dirtiness increase
        if(this.zooDirtiness < 10) {
            this.zooDirtiness++;
        }
    }

    public void DecreaseZooDirtiness() {        // Method for zoo dirtiness decrease
        if(this.zooDirtiness > 1) {
            this.zooDirtiness--;
        }
    }

    public void IncreaseZooAttractiveness() {   // Method for zoo attractiveness increase
        if(this.zooAttractiveness < 10) {
            this.zooAttractiveness++;
        }
    }

    public void DecreaseZooAttractiveness() {   // Method for zoo attractiveness increase
        if(this.zooAttractiveness > 1) {
            this.zooAttractiveness--;
        }
    }

    public void VisitorLetIn(Visitor visitor) {
        this.visitors.add(visitor);
    }
    //public void VisitorLetIn() {                // Method for visitor number increase
    //    this.zooNumberOfActualVisitor++;
    //}
    public void VisitorLetOut(int index) {               // Method for visitor number decrease
        this.visitors.remove(index);
    }
    //public void VisitorLetOut() {               // Method for visitor number decrease
    //    this.zooNumberOfActualVisitor--;
    //}

    public int GetZooNumberOfVisitor() {  // Method for visitor actual visitors number in zoo
        return this.visitors.size();
    }

    //public int GetZooNumberOfActualVisitor() {  // Method for visitor actual visitors number in zoo
     //   return this.zooNumberOfActualVisitor;
    //}

    public int GetZooNumberOfAnimal() {         // Method for visitor actual animals number in zoo
        return this.animals.size();
    }
    public int GetZooDirtiness() {
        return this.zooDirtiness;
    }

    public int GetZooAttractiveness() {
        return this.zooAttractiveness;
    }

    public String GetZooName() {
        return "Elephantastic";
    }
}
