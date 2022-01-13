package Project.Zoo;

import Project.Animals.*;
import Project.Input;
import Project.Output;
import Project.Timer.Timer;
import Project.Visitors.Visitor;
import java.util.Arrays;
import java.util.List;

public class ZooManagement implements Runnable {

    private Zoo zoo;
    private ZooCashOffice zooCashOffice;
    private Timer timer;

    private List<Class<?>> animalsToBuy = Arrays.asList(new Class<?>[]
            { Chimpanzee.class, AfricanElephant.class, BoaSnake.class, AfricanLion.class, EuropeanBison.class, Flamingo.class, Horse.class, Penguin.class, RedPanda.class, TigerShark.class}
    );

    public void run() {

    }

    public ZooManagement(Timer timer) {
        this.zoo = new Zoo();
        this.zooCashOffice = new ZooCashOffice();
        this.timer = timer;
        Menu();
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
                    this.zooCashOffice.LowerCash(animal.GetAnimalBuyValue());
                    this.zoo.AddAnimalToList(animal);
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
        this.zoo.GetAnimalsList();
        Output.Set("Select animal to sell: ");
        int numberSelected = Input.GetInt() - 1;
        Animals animal = this.zoo.GetAnimal(numberSelected);
        if (!(animal == null)) {
            int sellValue = animal.GetAnimalSellValue();
            this.zooCashOffice.AddCash(sellValue);
            this.zoo.RemoveAnimalFromList(numberSelected);
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

    private void ListOfAnimalsToBuy() {
        int i=1;
        for (Class<?> clazz: animalsToBuy) {
            Output.Set("["+ (i++) +"] " + clazz.getSimpleName());
        }
    }

    public void EndDay() {
        int tempZooAnimalFun = 0;
        int tempZooAnimalClean = 0;

        while(this.zoo.GetZooNumberOfActualVisitor()>0) {
            this.zoo.VisitorLetOut();
        }

        int i = this.zoo.GetZooNumberOfAnimal()-1;
        while(i >= 0) {
            Animals animal = this.zoo.GetAnimal(i);

            if(animal.GetAnimalWithoutFood() > 2) {
                Output.Set("Animal: " + animal.GetAnimalName() + " die.");
                this.zoo.RemoveAnimalFromList(i);
            } else {
                if (animal.GetAnimalFun() > 5)
                    tempZooAnimalFun++;
                else
                    tempZooAnimalFun--;


                if (animal.GetAnimalCleanLevel() > 7)
                    tempZooAnimalClean++;
                else
                    tempZooAnimalClean--;


                if(animal.GetAnimalHungry()) {
                    animal.IncreaseAnimalWithoutFood();
                }
                animal.DecreaseAnimalClean();
                animal.DecreaseAnimalFun();
                animal.SetAnimalHungry(true);
            }
            i--;
        }

        if(tempZooAnimalFun >= 0 && tempZooAnimalClean >= 0)
            this.zoo.IncreaseZooAttractiveness();
        else
            this.zoo.DecreaseZooAttractiveness();
    }

    public void ShowAnimalStats() {
        this.zoo.GetAnimalsList();
        Output.Set("Select animal to show stats: ");
        int numberSelected = Input.GetInt() - 1;
        Animals animal = this.zoo.GetAnimal(numberSelected);
        if (!(animal == null)) {
            animal.AnimalStats();
        }
    }

    public void LetIn(Visitor visitor) {
        if(this.zooCashOffice.CheckVisitor(visitor))
            this.zoo.VisitorLetIn();
    }

    public int GetAttractiveness() {
        return 3;
    }

    private void GetSummary() {
        Output.Set("Animals in zoo: " + this.zoo.GetZooNumberOfAnimal());
        Output.Set("Visitors in zoo: " + this.zoo.GetZooNumberOfActualVisitor());
        Output.Set("Cash in zoo: " + this.zooCashOffice.GetCash());
    }

    public void Menu() {
        //noinspection InfiniteLoopStatement
        while(true) {
            Output.Set("\nPress enter...\n");
            Input.GetEnter();
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
            Output.Set("[0] Check time");

            int input = Input.GetInt();
            switch (input) {
                case 1 -> BuyAnimal();
                case 2 -> SellAnimal();
                case 3 -> ShowAnimalStats();
                case 4 -> Output.Set("Play with");
                case 5 -> HireWorker();
                case 6 -> FiredWorker();
                case 7 -> GetSummary();
                case 8 -> this.zoo.GetAnimalsList();
                case 9 -> Output.Set("Clean");
                case 11 -> EndDay();
                case 0 -> this.timer.GetActualTime();
                default -> Output.Set("Wrong number selected.");

            }
        }
    }
}
