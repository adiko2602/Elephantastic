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
            { Chimpanzee.class, AfricanElephant.class, BoaSnake.class, AfricanLion.class, EuropeanBison.class,
                    Flamingo.class, Horse.class, Penguin.class, RedPanda.class, TigerShark.class}
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
        Output.Set("\nSelect an animal you would like to buy: ");
        ListOfAnimalsToBuy();
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
        Output.Set("\nSelect an animal you would like to sell: ");
        this.zoo.GetAnimalsList();
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
                Output.Set("Animal: " + animal.GetAnimalName() + " died.");
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
        Output.Set("Select which animal statistics you would like to see: ");
        this.zoo.GetAnimalsList();
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
            Output.Set("\nPress enter...");
            Input.GetEnter();
            Output.Set("ZOO MENU");
            Output.Set("What would you like to do?: ");
            Output.Set("[1] Manage your animals");
            Output.Set("[2] Manage your workers");
            Output.Set("[3] Manage your zoo");
            Output.Set("[4] Finish the day");

            int input = Input.GetInt();
            switch (input) {
                case 1 : Output.Set("\nAnimal management menu:");
                         Output.Set("[1] Buy an animal");
                         Output.Set("[2] Sell an animal");
                         Output.Set("[3] Show statistics of an animal");
                         Output.Set("[4] Play with the animal");
                         Output.Set("[5] List all animals");

                int animals = Input.GetInt();
                switch (animals) {
                    case 1 : BuyAnimal(); break;
                    case 2 : SellAnimal(); break;
                    case 3 : ShowAnimalStats(); break;
                    case 4 : Output.Set("Play with"); break;
                    case 5 : this.zoo.GetAnimalsList(); break;
                    default : Output.Set("Wrong number selected."); break;

                }
                break;

                case 2 : Output.Set("\nWorker management menu:");
                         Output.Set("[1] Hire a worker");
                         Output.Set("[2] Fire a worker");
                         Output.Set("[3] List all workers");

                int workers = Input.GetInt();
                switch (workers) {
                    case 1 : HireWorker(); break;
                    case 2 : FiredWorker(); break;
                    case 3 : GetSummary(); break;
                    default : Output.Set("Wrong number selected."); break;
                }
                break;

                case 3 : Output.Set("\nZoo management menu:");
                         Output.Set("[1] Clean the zoo");
                         Output.Set("[2] Check the time");

                int zoo = Input.GetInt();
                switch (zoo) {
                    case 1 : Output.Set("Zoo has been cleaned."); break;
                    case 2 : this.timer.GetActualTime(); break;
                    default : Output.Set("Wrong number selected."); break;
                }
                break;

                case 4 : EndDay(); break;
                default : Output.Set("Wrong number selected."); break;
            }
        }
    }
}
