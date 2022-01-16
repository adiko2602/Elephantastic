package Project.Timer;

import Project.Output;

public class Timer implements Runnable {
    private long runSeconds = 1435;
    private long seconds = 1435;
    private int day = 1;
    private boolean endDay = false;

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while(true) {
            CountSeconds();
            CheckEndDay();
        }
    }

    private void CountSeconds() {
        try {
            Thread.sleep(1000);
        } catch (Exception exception) {
            Output.Set(exception.getMessage());
        }
        this.seconds++;
        this.runSeconds++;
    }

    private void CheckEndDay() {
        if(this.seconds == 1440) {
            this.seconds = 0;
            this.endDay = true;
            this.day++;
        }
    }

    public long CheckRunSeconds() {
        return this.runSeconds;
    }

    public void SetEndDay() {
        this.endDay = false;
    }

    public boolean GetEndDay() {
        return this.endDay;
    }

    public void GetActualTime() {
        int hours = GetActualHour();
        int minutes = GetActualMinute();
        String timeString = String.format("%02d:%02d", hours, minutes);

        Output.Set("Day: " + this.day + " \nTime: " + timeString);
    }

    public int GetActualHour() {
        return (int)(this.seconds%3600)/60;
    }

    public int GetActualMinute() {
        return (int)this.seconds%60;
    }

    public int GetActualDay() {
        return this.day;
    }
}
