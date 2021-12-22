package Project.Timer;

import Project.Output;

public class Timer implements Runnable {
    private int time = 770;
    private int day = 1;
    private boolean endDay = false;

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while(true) {
            if (CheckTime()) {
                AddMinute();
            } else {
                ResetTime();
            }
        }
    }

    private void AddMinute() {
        this.time++;
        try {
            Thread.sleep(1000);
        } catch (Exception exception) {
            Output.Set(exception.getMessage());
        }
    }

    private boolean CheckTime() {
        return this.time < 1440;
    }

    private void ResetTime() {
        this.time = 0;
        this.day++;
        this.endDay = true;
    }

    public void SetEndDay(boolean state) {
        this.endDay = state;
    }

    public boolean GetEndDay() {
        return this.endDay;
    }

    public void GetActualTime() {
        int hours = (this.time % 3600) / 60;
        int minutes = this.time % 60;
        String timeString = String.format("%02d:%02d", hours, minutes);

        Output.Set("Day: " + this.day + " \nTime: " + timeString);
    }

    public int GetActualHour() {
        return (this.time%3600)/60;
    }

    public int GetActualMinute() {
        return this.time%60;
    }

    /*public int GetActualSecond() {
        return ;
    }
*/
    public int GetActualDay() {
        return this.day;
    }
}
