package Project;

import Project.QueueGenerator.QueueGenerator;
import Project.Timer.Timer;
import Project.Visitors.Visitor;
import Project.Zoo.ZooManagement;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        Timer timer = new Timer();

        ZooManagement zooManagement = new ZooManagement(timer);
        QueueGenerator queueGenerator = new QueueGenerator(timer, zooManagement);

        Thread timerThread = new Thread(timer);
        Thread zooManagementThread = new Thread(zooManagement);

        timerThread.start();
        zooManagementThread.start();

        //noinspection InfiniteLoopStatement
        while (true) {
            zooManagement.CheckWorkers();
            if(timer.GetEndDay()) {
                timer.SetEndDay();
                zooManagement.EndDay();
            }
/*            if(timer.CheckRunSeconds() % 60 == 0) {
                ArrayList<Visitor> visitors = queueGenerator.Generate();
                for (Visitor vistor: visitors) {
                    zooManagement.LetIn(vistor);
                }
            }*/
        }
    }
}
