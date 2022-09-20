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
    int GIVE_BIRTH_RATE = 15;
    return randomUtils.hasSucceedByPercentage(GIVE_BIRTH_RATE);
  }

  public void relocateTo(int relocateToLocationId) {
    setLocationId(relocateToLocationId);
  }

  public void dieFromHunt() {
    setDeath();
  }

  public void dieFromLocationOverloaded() {
    setDeath();
  }
}