package Project.QueueGenerator;

import Project.Output;
import Project.Timer.Timer;
import Project.Visitors.Visitor;
import Project.Zoo.ZooManagement;

import java.util.ArrayList;
import java.util.HashMap;

public class QueueGenerator implements Runnable {
    private Timer timer;
    private ZooManagement zooManagment;
    private int visitorNumber;
    private int minVisitorNumber = 10;
    private int visitorNumberStep = 10;
    private boolean isVisitor = false;

    private int actualDay;
    private int actualHour;

    private int actualAtractive;

    private int[] hoursMultiplier = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 5, 6, 6, 6, 5, 4, 4, 3, 1, 1, 1, 1 };

    private ArrayList<Visitor> visitorsQueue = new ArrayList<>();

    public QueueGenerator(Timer timer, ZooManagement zooManagment) {
        this.timer = timer;
        this.zooManagment = zooManagment;
        this.actualDay = this.timer.GetActualDay();
        this.actualHour = this.timer.GetActualHour();
        this.actualAtractive = this.zooManagment.GetAttractiveness();
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
            if(this.actualAtractive < this.zooManagment.GetAttractiveness()) {
                this.actualAtractive = this.zooManagment.GetAttractiveness();
                this.visitorNumber += this.visitorNumberStep;
            }
            if(this.actualAtractive > this.zooManagment.GetAttractiveness()) {
                this.actualAtractive = this.zooManagment.GetAttractiveness();
                if(this.visitorNumber >= this.minVisitorNumber) {
                    this.visitorNumber -= this.visitorNumberStep;
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
                this.isVisitor = true;
            } else {
                try {
                    Thread.sleep(1000);
                } catch (Exception ignored) { }
                this.isVisitor = true;
            }
        }
    }

    private void Generate() {
        Output.Set("Generate");
        int r = this.visitorNumber*this.hoursMultiplier[this.actualHour]*this.actualAtractive;
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
