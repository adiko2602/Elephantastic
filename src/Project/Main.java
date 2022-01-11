package Project;

import Project.QueueGenerator.QueueGenerator;
import Project.Timer.Timer;
import Project.Visitors.Visitor;
import Project.Zoo.ZooManagement;


public class Main { //hah

    public static void main(String[] args) {
        Timer timer = new Timer();
        ZooManagement zooManagement = new ZooManagement(timer);
        QueueGenerator queueGenerator = new QueueGenerator(timer, zooManagement);

        Thread timerThread = new Thread(timer);
        Thread zooManagementThread = new Thread(zooManagement);
        Thread queueGeneratorThread = new Thread(queueGenerator);

        timerThread.start();
        zooManagementThread.start();
        queueGeneratorThread.start();

        //noinspection InfiniteLoopStatement
        while (true) {
            if(timer.GetEndDay()) {
                timer.SetEndDay(false);
                zooManagement.EndDay();
            } else {
                try {
                    Thread.sleep(1);
                } catch (Exception exception) {
                    Output.Set(exception.getMessage());
                }
            }

            if(queueGenerator.CheckIfIsVisitor()) {
                zooManagement.LetIn(queueGenerator.GetVisitor());
            }
        }
    }
}
