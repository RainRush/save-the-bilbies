import java.util.ArrayList;

public class AnimalManager<T extends Animal> {
  protected ArrayList<T> animals;

  protected AnimalManager() {
    animals = new ArrayList<T>();
  }

  public int getAliveCount(int locationId) {
    int aliveCount = 0;
    for (T animal : animals) {
      boolean isInLocation = animal.getLocationId() == locationId;
      boolean isAlive = animal.checkAlive();
      if (isInLocation && isAlive) {
        aliveCount++;
      }
    }
    return aliveCount;
  }

  public int getDeadCount(int locationId) {
    int deadCount = 0;
    for (T animal : animals) {
      boolean isInLocation = animal.getLocationId() == locationId;
      boolean isNotAlive = !animal.checkAlive();
      if (isInLocation && isNotAlive) {
        deadCount++;
      }
    }
    return deadCount;
  }
}