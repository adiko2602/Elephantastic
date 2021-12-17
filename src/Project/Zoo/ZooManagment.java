package Project.Zoo;

import Project.Animals.Animals;
import Project.Animals.Monkey;
import Project.Input;
import Project.Output;
import Project.Workers.Workers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ZooManagment {
    Zoo zoo = new Zoo();
    ZooCashOffice zooCashOffice = new ZooCashOffice();
    private ArrayList<Workers> workers = new ArrayList<>();
    private ArrayList<Animals> animalsToBuy = new ArrayList<>();

    public ZooManagment() {
        animalsToBuy.add(new Monkey());
    }

    public void BuyAnimal() {
        ListOfAnimalsToBuy();
        Output.Set("Select animal to buy: ");
        int numberSelected = Input.GetInt() - 1;
        switch (numberSelected) {
            case 0 -> {
                Animals animal = this.animalsToBuy.get(numberSelected);
                if (zoo.AddAnimalToList(animal)) {
                    Output.Set("lower cash");
                }
            }
            case 1 -> {
                Animals animal = this.animalsToBuy.get(numberSelected);
                if (zoo.AddAnimalToList(animal)) {
                    Output.Set("lower cash");
                }
            }
        }
    }

    public void SellAnimal() {
        zoo.GetAnimalsList();
        Output.Set("Select animal to sell: ");
        int numberSelected = Input.GetInt() - 1;
        System.out.println(numberSelected);
        if (zoo.RemoveAnimalFromList(numberSelected)) {
            Output.Set("add cash");
        }
    }

    public void HireWorker() {

    }

    public void FiredWorker() {

    }

    public void CleanZoo() {

    }

    public void FeedAnimal() {

    }

    public void ListOfWorkers() {

    }

    private void ListOfAnimalsToBuy() {
        int i=1;
        for (Animals animal: animalsToBuy) {
            Output.Set("["+ (i++) +"] " + animal.GetAnimalName());
        }
    }
}
