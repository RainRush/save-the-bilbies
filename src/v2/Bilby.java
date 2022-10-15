public class Bilby extends Animal {
  private Random randomUtils;
  private static int GIVE_BIRTH_RATE = 15;

  public Bilby() {
    super();
    randomUtils = new Random();
  }

  public Bilby(String _id, boolean _bornDuringSimulation) {
    super(_id, _bornDuringSimulation, GIVE_BIRTH_RATE);
    randomUtils = new Random();
  }

  public void dieFromHunt() {
    setDeath();
  }

  public void dieFromLocationOverloaded() {
    setDeath();
  }
}