package Project.Animals;

import Project.Output;

public abstract class Animals {
    protected String animalName;
    protected int wakeUp;
    protected int goToSleep;
    protected int cleanLevel = 5;
    protected int funLevel = 5;
    protected boolean hungry = true;
    protected int maxFunLevel = 10;
    protected int maxCleanLevel = 10;
    protected int buyValue;
    protected int sellValue;
    protected int withoutFood;

    public String GetAnimalName() {
        return this.animalName;
    }

    public void SetAnimalHungry(boolean state) {
        this.hungry = state;
    }

    public void AnimalStats() {
        Output.Set("Name: " + GetAnimalName() + "\n" +
                    "Fun: " + GetAnimalFun() + "\n" +
                    "Clean: " + GetAnimalCleanLevel() + "\n" +
                    "Hungry: " + GetAnimalHungry() + "\n" +
                    "Wakeup: " + GetAnimalWakeUp() + "\n" +
                    "Go to sleep: " + GetAnimalGoToSleep());
    }

    public int GetAnimalWakeUp() {
        return this.wakeUp;
    }

    public int GetAnimalGoToSleep() {
        return this.goToSleep;
    }

    public int GetAnimalCleanLevel() {
        return this.cleanLevel;
    }

    public int GetAnimalFun() {
        return this.funLevel;
    }

    public boolean GetAnimalHungry() {
        return this.hungry;
    }

    public int GetAnimalBuyValue() { return this.buyValue; }

    public int GetAnimalSellValue() { return this.sellValue; }

    public int GetAnimalWithoutFood() { return this.withoutFood; }

    public void IncreaseAnimalFun() {
        if(this.funLevel < this.maxFunLevel) {
            this.funLevel++;
        }
    }

    public void DecreaseAnimalFun() {
        if(this.funLevel > 0) {
            this.funLevel--;
        }
    }

    public void IncreaseAnimalClean() {
        if(this.cleanLevel < this.maxCleanLevel) {
            this.cleanLevel++;
        }
    }

    public void DecreaseAnimalClean() {
        if(this.cleanLevel > 0) {
            this.cleanLevel--;
        }
    }

    public void IncreaseAnimalWithoutFood() {
        this.withoutFood++;
    }

    public void ResetAnimalWithoutFood() {
        this.withoutFood = 0;
    }
}