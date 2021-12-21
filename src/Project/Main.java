package Project;

import Project.Visitors.Visitors;
import Project.Zoo.Zoo;
import Project.Zoo.ZooManagment;

public class Main {

    public static void main(String[] args) {
        ZooManagment zooManagment = new ZooManagment();
/*        zooManagment.BuyAnimal();
        zooManagment.BuyAnimal();
        zooManagment.BuyAnimal();
        zooManagment.SellAnimal();*/
        //noinspection InfiniteLoopStatement
        while(true) {
            zooManagment.Menu();
        }
    }
}
