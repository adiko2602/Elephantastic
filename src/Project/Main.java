package Project;

import Project.Visitors.Visitors;
import Project.Zoo.Zoo;
import Project.Zoo.ZooManagment;


public class Main {

    public static void main(String[] args) {
        Timer timer = new Timer();
        ZooManagment zooManagment = new ZooManagment(timer);

        Thread timerThread = new Thread(timer);
        Thread zooManagmentThread = new Thread(zooManagment);
        timerThread.start();
        zooManagmentThread.start();

        System.out.println(timerThread.isAlive());
        System.out.println(zooManagmentThread.isAlive());

        //noinspection InfiniteLoopStatement
        while (true) {
            if(timer.GetEndDay()) {
                timer.SetEndDay(false);
                zooManagment.EndDay();
            }
            try {
                Thread.sleep(1000);
            } catch (Exception exception) {
                Output.Set(exception.getMessage());
            }
        }
    }
}
