package Project.Workers;

import java.util.Random;

public class Workers {
    private long workEndTime;
    private final String name;

    public Workers() {
        Random rand = new Random();
        String[] names = {"Katerina", "Manraj", "Ayomide", "Glen", "Star", "Kirby", "Nadia", "Rogan", "Charley", "Brogan", "Darnell", "Jody", "Jocelyn", "Rory", "Neriah", "Lenny", "Kendal", "August", "Rikki", "Reagan"};
        this.name = names[rand.nextInt(names.length)];
    }

    public void SetWorkEndTime(long time) {
        this.workEndTime = time;
    }

    public long GetWorkEndTime() {
        return this.workEndTime;
    }

    public String GetName() {
        return this.name;
    }

    public int GetValue() {
        int workerValue = 500;
        return workerValue;
    }
}
