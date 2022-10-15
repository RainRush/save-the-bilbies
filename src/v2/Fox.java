public class Fox extends Animal {
  private int health;
  private Random randomUtils;
  private static int GIVE_BIRTH_RATE = 10;
  
  public Fox() {
    super();
    health = 3;
    randomUtils = new Random();
  }

  public Fox(String _uniqueId, boolean _bornDuringSimulation) {
    super(_uniqueId, _bornDuringSimulation, GIVE_BIRTH_RATE);
    health = 3;
    randomUtils = new Random();
  }

  public boolean huntBilbyAttempt() {
    int HUNT_SUCCESS_RATE = 40;
    return randomUtils.hasSucceedByPercentage(HUNT_SUCCESS_RATE);
  }

  public boolean interventionAttempt() {
    int INTERVENTION_SUCCESS_RATE = 50;
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