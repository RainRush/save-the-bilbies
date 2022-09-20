public class Fox extends Animal {
  private int health;
  private Random randomUtils;
  
  public Fox() {
    super();
    health = 3;
    randomUtils = new Random();
  }

  public Fox(String _uniqueId, int _locationId, boolean _bornDuringSimulation) {
    super(_uniqueId, _locationId, _bornDuringSimulation);
    health = 3;
    randomUtils = new Random();
  }

  public boolean giveBirthAttempt() {
    int GIVE_BIRTH_RATE = 10;
    return randomUtils.hasSucceedByPercentage(GIVE_BIRTH_RATE);
  }

  public boolean huntBilbyAttempt() {
    int HUNT_SUCCESS_RATE = 40;
    return randomUtils.hasSucceedByPercentage(HUNT_SUCCESS_RATE);
  }

  public boolean interventionAttempt() {
    int INTERVENTION_SUCCESS_RATE = 100;
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