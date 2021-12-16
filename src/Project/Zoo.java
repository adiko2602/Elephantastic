package Project;

import Project.Animals.Animals;
import Project.Workers.Workers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Zoo {
    private int bankAccount;
    private ArrayList<Animals> animals = new ArrayList<>();
    private ArrayList<Animals> animalsToBuy = new ArrayList<>();
    private ArrayList<Workers> workers = new ArrayList<>();

    public void Run() {
        animalsToBuy.add(new Animals());
        //noinspection InfiniteLoopStatement
        while(true) {
            GetMethodName();
        }
    }

    private void GetMethodName() {
        try {
            String method = Input.GetString();
            this.FindMethod(method);
        } catch (Throwable exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void FindMethod(String method) throws Throwable {
        try {
            Method reflectedMethod = this.getClass().getDeclaredMethod(method);
            reflectedMethod.setAccessible(true);
            reflectedMethod.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException exception) {
            System.out.println("There's no method like " + method);
        } catch (InvocationTargetException exception) {
            throw new Exception(exception.getTargetException().getMessage());
        }
    }

    public void BuyAnimal() {
        for(Animals animal: animalsToBuy) {
            Output.Set(animal.getAnimalsName());
        }
    }

    public void FeedAnimal() {
    }

    public void SellAnimal() {
    }

    public void ListOfAnimals() {
        for (Animals animal:animals) {
            Output.Set(animal.getAnimalsName());
        }
    }

    public void HireWorker() {
    }

    public void FiredWorker() {
    }

    public void ListOfWorkers() {
    }

    public void CleanZoo() {
    }

    public void UpgradeZoo() {
    }
}
