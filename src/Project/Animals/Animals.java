package Project.Animals;

public abstract class Animals {
    protected String animalName;
    protected int wakeUp;
    protected int goToSleep;
    protected int buyLevel;
    protected int atractiveLevel;
    protected int funLevel;
    protected boolean hungry;
    protected int maxFunLevel;
    protected int maxArtactiveLevel;

    public String GetAnimalName() {
        return this.animalName;
    }

    public int GetAnimalWakeUp() {
        return this.wakeUp;
    }

    public int GetAnimalGoToSleep() {
        return this.goToSleep;
    }

    public int GetAnimalBuyLevel() {
        return this.buyLevel;
    }

    public int GetAnimalAtractive() {
        return this.atractiveLevel;
    }

    public int GetAnimalFun() {
        return this.funLevel;
    }

    public boolean GetAnimalHungry() {
        return this.hungry;
    }

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

    public void IncreaseAnimalAtractive() {
        if(this.atractiveLevel < this.maxArtactiveLevel) {
            this.atractiveLevel++;
        }
    }

    public void DecreaseAnimalAtractive() {
        if(this.atractiveLevel > 0) {
            this.atractiveLevel--;
        }
    }
}
