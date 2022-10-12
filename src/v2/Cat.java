public class Cat extends Animal {
  private int health;
  private Random randomUtils;
  
  public Cat() {
    super();
    health = 3;
    randomUtils = new Random();
  }

  public Cat(String _uniqueId, boolean _bornDuringSimulation) {
    super(_uniqueId, _bornDuringSimulation);
    health = 3;
    randomUtils = new Random();
  }

  public boolean giveBirthAttempt() {
    int GIVE_BIRTH_RATE = 20;
    return randomUtils.hasSucceedByPercentage(GIVE_BIRTH_RATE);
  }

  public boolean huntBilbyAttempt() {
    int HUNT_SUCCESS_RATE = 60;
    return randomUtils.hasSucceedByPercentage(HUNT_SUCCESS_RATE);
  }

  public boolean interventionAttempt() {
    int INTERVENTION_SUCCESS_RATE = 40;
    return randomUtils.hasSucceedByPercentage(INTERVENTION_SUCCESS_RATE);
  }

  public void dieFromIntervention() {
    setDeath();
  }

  private void dieFromNoHealthLeft() {
    setDeath();
  }

  public void punishFailHunt() {
    health--;
    if (health == 0) {
      dieFromNoHealthLeft();
    }
  }
}