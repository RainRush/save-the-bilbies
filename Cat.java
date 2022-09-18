public class Cat extends Animal {
  private int health;
  private Random randomUtils;
  
  public Cat() {
    super();
    health = 3;
    randomUtils = new Random();
  }

  public Cat(String _uniqueId, int _locationId, boolean _bornDuringSimulation) {
    super(_uniqueId, _locationId, _bornDuringSimulation);
    health = 3;
    randomUtils = new Random();
  }

  public boolean giveBirthAttempt() {
    // pull this logic out
    int randomNumber = randomUtils.getRandom(100);
    return randomNumber <= 20;
  }

  public boolean huntBilbyAttempt(boolean haveBilbiesLeft) {
    if (!haveBilbiesLeft) {
      return false;
    }
    int randomNumber = randomUtils.getRandom(100);
    return randomNumber <= 60;
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