package Project;

import Project.QueueGenerator.QueueGenerator;
import Project.Timer.Timer;
import Project.Visitors.Visitor;
import Project.Zoo.ZooManagment;


public class Main {
/*test*/
    public static void main(String[] args) {
        Timer timer = new Timer();
        ZooManagment zooManagment = new ZooManagment(timer);
        QueueGenerator queueGenerator = new QueueGenerator(timer, zooManagment);

        Thread timerThread = new Thread(timer);
        Thread zooManagmentThread = new Thread(zooManagment);
        Thread queueGeneratorThread = new Thread(queueGenerator);

        timerThread.start();
        zooManagmentThread.start();
        queueGeneratorThread.start();

        //noinspection InfiniteLoopStatement
        while (true) {
            if(timer.GetEndDay()) {
                timer.SetEndDay(false);
                zooManagment.EndDay();
            } else {
                try {
                    Thread.sleep(1);
                } catch (Exception exception) {
                    Output.Set(exception.getMessage());
                }
            }

            if(queueGenerator.CheckIfIsVisitor()) {
                zooManagment.LetIn(queueGenerator.GetVisitor());
            }
        }
    }
}
