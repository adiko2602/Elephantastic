package Project;

import Project.Visitors.Visitors;
import Project.Zoo.Zoo;

public class Main {

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        zoo.VisitorLetIn(new Visitors());
    }
}
