package Project.QueueGenerator;

import Project.Output;
import Project.Timer.Timer;
import Project.Visitors.Visitor;
import Project.Zoo.ZooManagement;

import java.util.Random;

public class QueueGenerator implements Runnable {
    private final Timer timer;
    private final ZooManagement zooManagement;

    private int actualDay;
    private int actualHour;

    private int actualAttractiveness;

    private final int[] hoursMultiplier = {0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 5, 7, 6, 4, 4, 3, 3, 2, 1, 0, 0, 0};

    public QueueGenerator(Timer timer, ZooManagement zooManagement) {
        this.timer = timer;
        this.zooManagement = zooManagement;
        this.actualDay = this.timer.GetActualDay();
        this.actualHour = this.timer.GetActualHour();
        this.actualAttractiveness = this.zooManagement.GetAttractiveness();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {}
            CheckParameters();
            Output.Set("Run for queue");
        }
    }

    private void CheckParameters() {
        Output.Set("Check parameters");
        Output.Set("Actual Hour: " + this.actualHour);
        Output.Set("Actual Hour: " + this.timer.GetActualHour());
        if (this.actualDay != this.timer.GetActualDay()) {
            this.actualDay = this.timer.GetActualDay();
            this.actualAttractiveness = this.zooManagement.GetAttractiveness();
        }
        if (this.actualHour != this.timer.GetActualHour()) {
            this.actualHour = this.timer.GetActualHour();
            Output.Set("Generate visitors");
            GenerateVisitors();
        }
    }


    private void GenerateVisitors() {
        Random rand = new Random();
        int numberOfVisitors = rand.nextInt(10) + 10;
        numberOfVisitors *= this.hoursMultiplier[this.actualHour];
        numberOfVisitors += (int) (numberOfVisitors * (this.actualAttractiveness / 2.0));
        Output.Set("" + numberOfVisitors);
        for (int i = 0; i < numberOfVisitors; i++) {
            Output.Set("Visitor " + i);
            this.zooManagement.LetIn(new Visitor());
        }
    }
}

    /*@Override
    public void run() {
        while(true) {
            CheckDay();
            Output.Set("" + this.actualHour + "/" + this.timer.GetActualHour());
            if(!(this.actualHour == this.timer.GetActualHour())) {
                this.actualHour = this.timer.GetActualHour();
                Generate();
            }
            if(!this.isVisitor) {
                GenerateIsVisitor();
            }
            try {
                Thread.sleep(1);
            } catch (Exception ignored) { }
        }
    }

    private void CheckDay() {
        if(this.actualDay != this.timer.GetActualDay()) {
            this.actualDay = this.timer.GetActualDay();
            int visitorNumberStep = 10;
            if(this.actualAttractiveness < this.zooManagement.GetAttractiveness()) {
                this.actualAttractiveness = this.zooManagement.GetAttractiveness();
                this.visitorNumber += visitorNumberStep;
            }
            if(this.actualAttractiveness > this.zooManagement.GetAttractiveness()) {
                this.actualAttractiveness = this.zooManagement.GetAttractiveness();
                if(this.visitorNumber >= this.minVisitorNumber) {
                    this.visitorNumber -= visitorNumberStep;
                }
            }
        }
    }

    private void GenerateIsVisitor() {
        if(!this.visitorsQueue.isEmpty()) {
            if(this.visitorsQueue.size() > 60) {
                try {
                    Thread.sleep(200);
                } catch (Exception ignored) { }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (Exception ignored) { }
            }
            this.isVisitor = true;
        }
    }

    private void Generate() {
        Output.Set("Generate");
        int r = this.visitorNumber*this.hoursMultiplier[this.actualHour]*this.actualAttractiveness;
        for(int i = 0; i<r; i++) {
            visitorsQueue.add(new Visitor());
        }
        Output.Set("" + this.visitorsQueue.size());
    }

    public Visitor GetVisitor() {
        Visitor visitor = this.visitorsQueue.get(this.visitorsQueue.size()-1);
        this.visitorsQueue.remove(this.visitorsQueue.size()-1);
        this.isVisitor = false;
        return visitor;
    }

    public boolean CheckIfIsVisitor() {
        return this.isVisitor;
    }

     */


