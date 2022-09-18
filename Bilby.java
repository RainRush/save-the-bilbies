public class Bilby extends Animal {
  private Random randomUtils;

  public Bilby() {
    super();
    randomUtils = new Random();
  }

  public Bilby(String _uniqueId, int _locationId, boolean _bornDuringSimulation) {
    super(_uniqueId, _locationId, _bornDuringSimulation);
    randomUtils = new Random();
  }

  public boolean giveBirthAttempt() {
    int randomNumber = randomUtils.getRandom(100);
    return randomNumber <= 15;
  }

  public void dieFromHunt() {
    setDeath();
  }

  public void dieFromExcess() {
    setDeath();
  }
}