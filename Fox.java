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
    int randomNumber = randomUtils.getRandom(100);
    return randomNumber <= 10;
  }

  public boolean huntBilbyAttempt(boolean haveBilbiesLeft) {
    if (!haveBilbiesLeft) {
      return false;
    }

    int randomNumber = randomUtils.getRandom(100);
    return randomNumber <= 40;
  }

  public boolean interventionAttempt() {
    // to do
    return true;
  }

  public void dieFromIntervention() {
    setDeath();
  }

  public void punishFailHunt() {
    health--;
    if (health == 0) {
      setDeath();
    }
  }
}