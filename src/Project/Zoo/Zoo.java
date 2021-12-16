package Project.Zoo;

import Project.Animals.Animals;
import Project.Output;
import Project.Visitors.Visitors;

import java.util.ArrayList;

public class Zoo {
    private ArrayList<Animals> animals = new ArrayList<>();
    private int zooNumberOfActualVisitor = 0;
    private int zooNumberOfFoodVegetable = 0;
    private int zooNumberOfFoodMeat = 0;
    private int zooDirtines = 0;
    private int zooAtractive = 0;
    private int zooVisitorsCapacity = 10;
    private int zooAnimalsCapacity = 10;
    private ZooCashOffice zooCashOffice = new ZooCashOffice();
    private ZooManagment zooManagment = new ZooManagment();

    public ArrayList<Animals> GetAnimalsList() {
        return this.animals;
    }

    public void AddAnimalToList(Animals animal) {
        if(this.animals.size() < this.zooAnimalsCapacity) {
            this.animals.add(animal);
            Output.Set("Success!");
            Output.Set("Animal: " + animal.GetAnimalName() + " was added to zoo.");
        } else {
            Output.Set("Maximum zoo capacity is reached!");
            Output.Set("Zoo capacity: " + this.zooAnimalsCapacity);
            Output.Set("Animals in zoo: " + this.animals.size());
        }
    }

    public void RemoveAnimalFromList(int animalNumber) {
        if(!this.animals.isEmpty()) {
            if(animalNumber >= 0 && animalNumber < this.animals.size()) {
                Output.Set("Animal: " + this.animals.get(animalNumber-1).GetAnimalName() + " was removed from zoo.");
                animals.remove(animalNumber - 1);
            } else {
                Output.Set("Selected index of animal is wrong!");
            }
        } else {
            Output.Set("Zoo is empty!");
        }
    }

    public void VisitorLetIn(Visitors visitor) {
        if(this.zooNumberOfActualVisitor < this.zooVisitorsCapacity) {
            if(this.zooCashOffice.CheckVisitor(visitor)) {
                this.zooNumberOfActualVisitor++;
            }
        } else {
            Output.Set("Maximum zoo capacity is reached!");
            Output.Set("Zoo capacity: " + this.zooVisitorsCapacity);
            Output.Set("Visitors in zoo: " + this.zooNumberOfActualVisitor);
        }
    }
}
