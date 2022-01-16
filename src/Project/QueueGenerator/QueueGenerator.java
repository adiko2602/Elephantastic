package Project.QueueGenerator;

import Project.Output;
import Project.Timer.Timer;
import Project.Visitors.Visitor;
import Project.Zoo.ZooManagement;

import java.util.ArrayList;

public class QueueGenerator implements Runnable {
    private final Timer timer;
    private final ZooManagement zooManagement;
    private int visitorNumber;
    private final int minVisitorNumber = 10;
    private boolean isVisitor = false;

    private int actualDay;
    private int actualHour;

    private int actualAttractiveness;

    private final int[] hoursMultiplier = { 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 5, 7, 6, 4, 4, 3, 3, 2, 1, 1, 0, 0};

    private final ArrayList<Visitor> visitorsQueue = new ArrayList<>();

    public QueueGenerator(Timer timer, ZooManagement zooManagment) {
        this.timer = timer;
        this.zooManagement = zooManagment;
        this.actualDay = this.timer.GetActualDay();
        this.actualHour = this.timer.GetActualHour();
        this.actualAttractiveness = this.zooManagement.GetAttractiveness();
        this.visitorNumber = this.minVisitorNumber;
        Generate();
    }

    @Override
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

}
