package Project.Zoo;

import Project.Animals.Animals;
import Project.Output;
import Project.Visitors.Visitor;

import java.util.ArrayList;

public class Zoo {
    private ArrayList<Animals> animals = new ArrayList<>();
    private int zooNumberOfActualVisitor = 0;
    private int zooNumberOfFoodVegetable = 0;
    private int zooNumberOfFoodMeat = 0;
    private int zooMaxAttractiveness = 0;
    private int zooMaxDirtiness = 0;
    private int zooDirtiness = 0;
    private int zooAttractiveness = 0;
    private int zooVisitorsCapacity = 0;
    private int zooAnimalsCapacity = 3;
    private int zooLevel = 0;

    public Zoo() {

    }

    public boolean GetAnimalsList() {
        if(!this.animals.isEmpty()) {
            int i = 1;
            for (Animals animal : this.animals) {
                Output.Set("[" + (i++) + "] " + animal.GetAnimalName());
            }
            return true;
        } else {
            Output.Set("Zoo is empty!");
        }
        return false;
    }

    public boolean AddAnimalToList(Animals animal) {
        if(animal.GetAnimalBuyLevel() <= this.zooLevel) {
            if (this.animals.size() < this.zooAnimalsCapacity) {
                this.animals.add(animal);
                Output.Set("Animal: " + animal.GetAnimalName() + " was added to zoo.");
                return true;
            } else {
                Output.Set("Maximum zoo capacity is reached!");
                Output.Set("Zoo capacity: " + this.zooAnimalsCapacity);
                Output.Set("Animals in zoo: " + this.animals.size());
            }
        } else {
            Output.Set("Zoo level is to low for " + animal.GetAnimalName());
            Output.Set("Zoo level: " + this.zooLevel);
            Output.Set(animal.GetAnimalName() + " level: " + animal.GetAnimalBuyLevel());
        }
        return false;
    }

    public boolean RemoveAnimalFromList(int animalNumber) {
        if(!this.animals.isEmpty()) {
            if(animalNumber >= 0 && animalNumber < this.animals.size()) {
                Output.Set("Animal: " + this.animals.get(animalNumber).GetAnimalName() + " was removed from zoo.");
                this.animals.remove(animalNumber);
                return true;
            } else {
                Output.Set("Selected number of animal is wrong!");
            }
        } else {
            Output.Set("Zoo is empty!");
        }
        return false;
    }

/*    public void AddVegetable(int vegetableNumber) {
        this.zooNumberOfFoodVegetable += vegetableNumber;
    }*/
/*
    public void AddMeat(int meatNumber) {
        this.zooNumberOfFoodMeat += meatNumber;
    }*/

/*    public boolean RemoveVegetable(int vegetableNumber) {
        if(this.zooNumberOfFoodVegetable >= vegetableNumber && this.zooNumberOfFoodVegetable > 0) {
            this.zooNumberOfFoodVegetable -= vegetableNumber;
            return true;
        }
        return false;
    }*/

/*    public boolean RemoveMeat(int meatNumber) {
        if(this.zooNumberOfFoodMeat >= meatNumber && this.zooNumberOfFoodMeat > 0) {
            this.zooNumberOfFoodMeat -= meatNumber;
            return true;
        }
        return false;
    }*/

    public void IncreaseZooDirtiness() {
        if(this.zooDirtiness < this.zooMaxDirtiness) {
            this.zooDirtiness++;
        }
    }

    public void DecreaseZooDirtiness() {
        if(this.zooDirtiness > 0) {
            this.zooDirtiness--;
        }
    }

    public void IncreaseZooAttractiveness() {
        if(this.zooDirtiness < this.zooMaxDirtiness) {
            this.zooAttractiveness++;
        }
    }

    public void DecreaseZooAttractiveness() {
        if(this.zooDirtiness > 0) {
            this.zooAttractiveness--;
        }
    }

/*    public void VisitorLetIn(Visitors visitor) {
        if(this.zooNumberOfActualVisitor < this.zooVisitorsCapacity) {
            if(this.zooCashOffice.CheckVisitor(visitor)) {
                this.zooNumberOfActualVisitor++;
            }
        } else {
            Output.Set("Maximum zoo capacity is reached!");
            Output.Set("Zoo capacity: " + this.zooVisitorsCapacity);
            Output.Set("Visitors in zoo: " + this.zooNumberOfActualVisitor);
        }
    }*/

    public void VisitorLetOut() {
        this.zooNumberOfActualVisitor--;
        Output.Set("Visitor come out.");
    }

    public int GetZooNumberOfActualVisitor() {
        return this.zooNumberOfActualVisitor;
    }

    public int GetZooNumberOfAnimal() {
        return this.animals.size();
    }

    public int GetZooAnimalCapacity() {
        return this.zooAnimalsCapacity;
    }

    public int GetZooLevel() {
        return this.zooLevel;
    }

    public Animals GetAnimal(int number) {
        if(!this.animals.isEmpty()) {
            if(number >= 0 && number < this.animals.size()) {
                return animals.get(number);
            } else {
                Output.Set("Selected number of animal is wrong!");
            }
        } else {
            Output.Set("Zoo is empty!");
        }
        return null;
    }
}
