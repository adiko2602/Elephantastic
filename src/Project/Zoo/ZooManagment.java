package Project.Zoo;

import Project.Animals.Animals;
import Project.Animals.Elephant;
import Project.Animals.Monkey;
import Project.Input;
import Project.Output;
import Project.Workers.Workers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ZooManagment {
    private Zoo zoo = new Zoo();
    private ZooCashOffice zooCashOffice = new ZooCashOffice();
    private ArrayList<Workers> workers = new ArrayList<>();

    private List<Class<?>> animalsToBuy = Arrays.asList(new Class<?>[]
            { Monkey.class, Elephant.class }
    );

    public ZooManagment() {
    }

    public void BuyAnimal() {
        ListOfAnimalsToBuy();
        Output.Set("Select animal to buy: ");
        int numberSelected = Input.GetInt() - 1;
        if(numberSelected >= 0 && numberSelected < this.animalsToBuy.size()) {
            try {
                Class<?> clazz = this.animalsToBuy.get(numberSelected);
                Animals animal = (Animals) clazz.getDeclaredConstructor().newInstance();
                if (animal.GetAnimalBuyValue() <= this.zooCashOffice.GetCash()) {
                    if (this.zoo.AddAnimalToList(animal)) {
                        this.zooCashOffice.LowerCash(animal.GetAnimalBuyValue());
                    }
                } else {
                    Output.Set("You don't have enough cash.");
                    Output.Set("Cash in bank: " + this.zooCashOffice.GetCash());
                    Output.Set("Animal cost: " + animal.GetAnimalBuyValue());
                }
            } catch (Exception exception) {
                Output.Set("" + exception.getMessage());
            }
        } else {
            Output.Set("Wrong number selected.");
        }
    }

    public void SellAnimal() {
        if(this.zoo.GetAnimalsList()) {
            Output.Set("Select animal to sell: ");
            int numberSelected = Input.GetInt() - 1;
            Animals animal = this.zoo.GetAnimal(numberSelected);
            if (!(animal == null)) {
                int sellValue = animal.GetaAnimalSellValue();
                if (this.zoo.RemoveAnimalFromList(numberSelected)) {
                    this.zooCashOffice.AddCash(sellValue);
                }
            }
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
        for (Class<?> clazz: animalsToBuy) {
            Output.Set("["+ (i++) +"] " + clazz.getSimpleName());
        }
    }

    public void Menu() {
        Output.Set("ZOO MENU");
        Output.Set("Select what you want to do: ");
        Output.Set("[1] Buy animal");
        Output.Set("[2] Sell animal");
        Output.Set("[3] Feed animal");
        Output.Set("[4] Play with animal");
        Output.Set("[5] Hire worker");
        Output.Set("[6] Fired worker");
        Output.Set("[7] List all workers");
        Output.Set("[8] List all animals");
        Output.Set("[9] Clean zoo");

        int input = Input.GetInt();
        switch (input) {
            case 1 -> BuyAnimal();
            case 2 -> SellAnimal();
            case 3 -> FeedAnimal();
            case 4 -> Output.Set("Play with");
            case 5 -> HireWorker();
            case 6 -> FiredWorker();
            case 7 -> ListOfWorkers();
            case 8 -> this.zoo.GetAnimalsList();
            case 9 -> Output.Set("Clean");
            default -> Output.Set("Wrong number selected.");
        }
    }
}
