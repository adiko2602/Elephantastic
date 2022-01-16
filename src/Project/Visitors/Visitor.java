package Project.Visitors;

import java.util.Random;

public class Visitor {
    private int age;
    private Random rand;

    public Visitor() {
        rand = new Random();
        this.age = rand.nextInt(100)+1;
    }

    public Integer GetAge() {
        return this.age;
    }
}
