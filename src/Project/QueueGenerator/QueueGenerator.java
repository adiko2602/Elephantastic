package Project.QueueGenerator;

import Project.Output;
import Project.Timer.Timer;
import Project.Visitors.Visitor;
import Project.Zoo.ZooManagement;

import java.util.Random;

public class QueueGenerator implements Runnable {
    private final Timer timer;
    private final ZooManagement zooManagement;
    private boolean exit = false;

    private int actualHour;

    private final int[] hoursMultiplier = {0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 5, 7, 6, 4, 4, 3, 3, 2, 1, 0, 0, 0};
    private final int[] exitRatio = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3};

    public QueueGenerator(Timer timer, ZooManagement zooManagement) {
        this.timer = timer;
        this.zooManagement = zooManagement;
        this.actualHour = this.timer.GetActualHour();
        Output.Set("" + actualHour);
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                Thread.sleep(10);
            } catch (Exception ignored) {}
            CheckParameters();
        }
    }

    public void stop() {
        this.exit = true;
    }

    private void CheckParameters() {
        if (this.actualHour != this.timer.GetActualHour()) {
            this.actualHour = this.timer.GetActualHour();
            GenerateVisitorsToLeave();
            GenerateVisitorsToEnter();
        }
    }


    private void GenerateVisitorsToEnter() {
        Random rand = new Random();
        int numberOfVisitors = rand.nextInt(10) + 10;
        numberOfVisitors *= this.hoursMultiplier[this.actualHour];
        numberOfVisitors += (int) (numberOfVisitors * (this.zooManagement.GetAttractiveness() / 2.0));
        for (int i = 0; i < numberOfVisitors; i++) {
            this.zooManagement.LetIn(new Visitor());
        }
    }

    private void GenerateVisitorsToLeave() {
        Random rand = new Random();
        int numberOfVisitors = this.zooManagement.GetNumberOfVisitors();
        int ratio = 0;
        int numberOfVisitorsToLeave = 0;
        if (exitRatio[this.actualHour] == 1)
            ratio = rand.nextInt(25)+1;
        else if (exitRatio[this.actualHour] == 2)
            ratio = rand.nextInt(25)+25;
        else if (exitRatio[this.actualHour] == 3)
            ratio = rand.nextInt(25)+50;
        else
            ratio = 0;
        numberOfVisitorsToLeave = (int) ((ratio/100.0) * numberOfVisitors);
        try {
            for (int i = numberOfVisitorsToLeave - 1; i >= 0; i--) {
                this.zooManagement.LetOut(i);
            }
        }catch(Exception ignored) {}
    }
}


