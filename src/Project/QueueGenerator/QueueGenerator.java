package Project.QueueGenerator;

import Project.Output;
import Project.Timer.Timer;
import Project.Visitors.Visitor;
import Project.Zoo.ZooManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class QueueGenerator implements Runnable {
    private Timer timer;
    private ZooManagement zooManagement;
    private int visitorNumber;
    private int minVisitorNumber = 10;
    private int visitorNumberStep = 10;
    private boolean isVisitor = false;

    private int actualDay;
    private int actualHour;

    private int actualAttractiveness;

    private int[] hoursMultiplier = { 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 5, 5, 6, 7, 6, 5, 4, 3, 3, 2, 1, 1, 0, 0};

    private ArrayList<Visitor> visitorsQueue = new ArrayList<>();

    public QueueGenerator(Timer timer, ZooManagement zooManagment) {
        this.timer = timer;
        this.zooManagement = zooManagment;
        this.actualDay = this.timer.GetActualDay();
        this.actualHour = this.timer.GetActualHour();
        this.actualAttractiveness = this.zooManagement.GetAttractiveness();
        this.visitorNumber = this.minVisitorNumber;
        //Generate();
    }

   /* @Override
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

    */

    public void run(){
        CheckDay();
        CheckHour();
    }

    private void CheckDay() {
        if(this.actualDay != this.timer.GetActualDay()) {
            this.actualDay = this.timer.GetActualDay();
            this.actualAttractiveness = this.zooManagement.GetAttractiveness();
        }
    }

    private void CheckHour() {
        if(this.actualHour != this.timer.GetActualHour()) {
                this.actualHour = this.timer.GetActualHour();
                GenerateVisitors();
            }
    }

    private void GenerateVisitors() {
        Random rand = new Random();
        int numberOfVisitors = rand.nextInt(10)+10;
        numberOfVisitors *= this.hoursMultiplier[this.actualHour];
        numberOfVisitors += (int)(numberOfVisitors*(this.actualAttractiveness/2.0));
        for(int i = 0; i<numberOfVisitors; i++) {
            zooManagement.LetIn(new Visitor());
        }
    }


    /*
    private void CheckDay() {
        if(this.actualDay != this.timer.GetActualDay()) {
            this.actualDay = this.timer.GetActualDay();
            if(this.actualAttractiveness < this.zooManagement.GetAttractiveness()) {
                this.actualAttractiveness = this.zooManagement.GetAttractiveness();
                this.visitorNumber += this.visitorNumberStep;
            }
            if(this.actualAttractiveness > this.zooManagement.GetAttractiveness()) {
                this.actualAttractiveness = this.zooManagement.GetAttractiveness();
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
}
