package Project;

import Project.QueueGenerator.QueueGenerator;
import Project.Timer.Timer;
import Project.Zoo.ZooManagement;

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
                timer.GetActualTime();
                try {
                    Thread.sleep(10);
                } catch (Exception ignored) {}
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
