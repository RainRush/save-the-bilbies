import java.util.ArrayList;

public class BilbyManager extends AnimalManager<Bilby> {
  private UuidManager uuidManager;

  public BilbyManager() {
    uuidManager = new UuidManager();
  }

  public BilbyManager(UuidManager _uuidManager) {
    uuidManager = _uuidManager;
  }

  private void createNewAnimal(int locationId, boolean isBornInSimulation) {
    String uniqueId = uuidManager.generateUniqueIdForBilby();
    animals.add(new Bilby(uniqueId, locationId, isBornInSimulation));
  }

  public void createNewAnimals(int locationId, int amount, boolean isBornInSimulation) {
    for (int i = 0; i < amount; i++) {
      createNewAnimal(locationId, isBornInSimulation); 
    }
  }

  public void bearNewAnimals(int locationId) {
    int newBornCount = 0;
    for (Bilby animal : animals) {
      boolean isAlive = animal.checkAlive();
      boolean isInLocation = animal.getLocationId() == locationId;
      boolean isGivingBirth = animal.giveBirthAttempt();
      if (isAlive && isInLocation && isGivingBirth) {
        newBornCount++;
      }
    }

    createNewAnimals(locationId, newBornCount, true);
  }

  public boolean checkBilbiesLeftInLocation(int locationId) {
    for (Bilby animal : animals) {
      boolean isInLocation = animal.getLocationId() == locationId;
      boolean isAlive = animal.checkAlive();
      if (isInLocation && isAlive) {
        return true;
      }
    }
    return false;
  }

  public void chooseOneToDieFromHunt(int locationId) {
    for (Bilby animal : animals) {
      boolean isInLocation = animal.getLocationId() == locationId;
      boolean isAlive = animal.checkAlive();
      if (isAlive && isInLocation) {
        animal.dieFromHunt();
        break;
      }
    }
  }
}