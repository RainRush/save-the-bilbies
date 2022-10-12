public class Bilby extends Animal {
  private Random randomUtils;

  public Bilby() {
    super();
    randomUtils = new Random();
  }

  public Bilby(String _id, boolean _bornDuringSimulation) {
    super(_id, _bornDuringSimulation);
    randomUtils = new Random();
  }

  public boolean giveBirthAttempt() {
    int GIVE_BIRTH_RATE = 15;
    return randomUtils.hasSucceedByPercentage(GIVE_BIRTH_RATE);
  }

  // this should be on another level
  // public void relocateTo(int relocateToLocationId) {
  //   setLocationId(relocateToLocationId);
  // }

  public void dieFromHunt() {
    setDeath();
  }

  public void dieFromLocationOverloaded() {
    setDeath();
  }
}