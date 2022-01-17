package Project.Zoo;

import Project.Animals.*;
import Project.Input;
import Project.Output;
import Project.Timer.Timer;
import Project.Visitors.Visitor;
import Project.Workers.Workers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZooManagement implements Runnable {

    private final Zoo zoo;
    private final ZooCashOffice zooCashOffice;
    private final Timer timer;

    private final List<Class<?>> animalsToBuy = Arrays.asList(new Class<?>[]
            { Chimpanzee.class, AfricanElephant.class, BoaSnake.class, AfricanLion.class, EuropeanBison.class,
                    Flamingo.class, Horse.class, Penguin.class, RedPanda.class, TigerShark.class}
    );

    private final ArrayList<Workers> workers = new ArrayList<>();
    private final ArrayList<Workers> workersNotAvailable = new ArrayList<>();

    public void run() {
        Menu();
    }

    public ZooManagement(Timer timer) {
        this.zoo = new Zoo();
        this.zooCashOffice = new ZooCashOffice();
        this.timer = timer;
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
        Workers worker = new Workers();

        if (worker.GetValue() <= this.zooCashOffice.GetCash()) {
            this.zooCashOffice.LowerCash(worker.GetValue());
            this.workers.add(worker);
            Output.Set("Worker " + worker.GetName() + " add successfully to zoo.");
        } else {
            Output.Set("You don't have enough cash.");
            Output.Set("Cash in bank: " + this.zooCashOffice.GetCash());
            Output.Set("Worker cost: " + worker.GetValue());
        }
    }

    public void FireWorker() {
        if(!workers.isEmpty()) {
            GetWorkersList();
            Output.Set("\nSelect a worker you would like to fire: ");
            int numberSelected = Input.GetInt() - 1;
            if(numberSelected >= 0 && numberSelected < this.workers.size()) {
                workers.remove(numberSelected);
                Output.Set("\nSelected worker fired successfully.");
            } else {
                Output.Set("Worker with selected number does not exist!");
            }
        } else {
            Output.Set("There are no hired workers.");
        }
    }

    public void CheckWorkers() {
        if(!workersNotAvailable.isEmpty()) {
            for (int i = workersNotAvailable.size()-1; i>=0; i--) {
                if(workersNotAvailable.get(i).GetWorkEndTime() < timer.CheckRunSeconds()) {
                    workers.get(i).SetWorkEndTime(0);
                    workers.add(workersNotAvailable.get(i));
                    workersNotAvailable.remove(i);
                }
            }
        }
    }

    public void GetWorkersList(){
        if(!this.workers.isEmpty()) {                                       // Check if list is empty
            int i = 1;                                                      // Int for iterate
            for (Workers worker : this.workers) {                           // for loop for all worker in zoo
                Output.Set("[" + (i++) + "] " + worker.GetName());          // Output
            }
        } else {
            Output.Set("There isn't any workers.");                         // If list of workers is empty
        }
    }

    public void CleanZoo() {
        this.zoo.DecreaseZooDirtiness();
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

        while(this.zoo.GetZooNumberOfVisitor()>0) {
            this.zoo.VisitorLetOut(zoo.GetZooNumberOfVisitor()-1);
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
        Output.Set("Select which animal statistics you would like to view: ");
        this.zoo.GetAnimalsList();
        int numberSelected = Input.GetInt() - 1;
        Animals animal = this.zoo.GetAnimal(numberSelected);
        if (!(animal == null)) {
            animal.AnimalStats();
        }
    }

    public void LetIn(Visitor visitor) {
        if(this.zooCashOffice.CheckVisitor(visitor))
            this.zoo.VisitorLetIn(visitor);
    }

    public int GetAttractiveness() {
        return 3;
    }

    private void ZooStats() {
        Output.Set("Zoo name: " + zoo.GetZooName() + "\n" +
                "Number of animals: " + zoo.GetZooNumberOfAnimal() + "\n" +
                "Amount of visitors: " + zoo.GetZooNumberOfVisitor() + "\n" +
                "Attractiveness level: " + zoo.GetZooAttractiveness() + "\n" +
                "Dirtiness level: " + zoo.GetZooDirtiness() + "\n" +
                "Money in the piggy bank: " + this.zooCashOffice.GetCash() + "$");
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
                case 1 -> {
                    Output.Set("\nAnimal management menu:");
                    Output.Set("[1] Buy an animal");
                    Output.Set("[2] Sell an animal");
                    Output.Set("[3] Feed an animal");
                    Output.Set("[4] Show statistics of an animal");
                    Output.Set("[5] Play with the animal");
                    Output.Set("[6] List all animals");
                    Output.Set("[0] Return back to main menu");
                    int animals = Input.GetInt();
                    switch (animals) {
                        case 1 -> BuyAnimal();
                        case 2 -> SellAnimal();
                        case 3 -> FeedAnimal();
                        case 4 -> ShowAnimalStats();
                        case 5 -> Output.Set("Play with");
                        case 6 -> this.zoo.GetAnimalsList();
                        case 0 -> Menu();
                        default -> Output.Set("Wrong number selected.");
                    }
                }
                case 2 -> {
                    Output.Set("\nWorker management menu:");
                    Output.Set("[1] Hire a worker");
                    Output.Set("[2] Fire a worker");
                    Output.Set("[3] List all workers");
                    Output.Set("[0] Return back to main menu");
                    int workers = Input.GetInt();
                    switch (workers) {
                        case 1 -> HireWorker();
                        case 2 -> FireWorker();
                        case 3 -> GetWorkersList();
                        case 0 -> Menu();
                        default -> Output.Set("Wrong number selected.");
                    }
                }
                case 3 -> {
                    Output.Set("\nZoo management menu:");
                    Output.Set("[1] Clean the zoo");
                    Output.Set("[2] Check the time");
                    Output.Set("[3] View zoo statistics");
                    Output.Set("[0] Return back to main menu");
                    int zoo = Input.GetInt();
                    switch (zoo) {
                        case 1 -> {CleanZoo(); Output.Set("Zoo has been cleaned.");}
                        case 2 -> this.timer.GetActualTime();
                        case 3 -> {Output.Set("Zoo statistics."); ZooStats();}
                        case 0 -> Menu();
                        default -> Output.Set("Wrong number selected.");
                    }
                }
                case 4 -> {
                    EndDay();
                    Output.Set("Day finished.");
                }
                default -> Output.Set("Wrong number selected.");
            }
        }
    }
}
