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
      boolean isGivingBirth = animal.giveBirthAttempt();
      if (animal.checkAliveInLocation(locationId) && isGivingBirth) {
        newBornCount++;
      }
    }

    createNewAnimals(locationId, newBornCount, true);
  }

  public boolean checkBilbiesLeftInLocation(int locationId) {
    for (Bilby animal : animals) {
      if (animal.checkAliveInLocation(locationId)) {
        return true;
      }
    }
    return false;
  }

  public void chooseOneToDieFromHunt(int locationId) {
    for (Bilby animal : animals) {
      if (animal.checkAliveInLocation(locationId)) {
        animal.dieFromHunt();
        break;
      }
    }
  }

  public void relocate(int relocateFromLocationId, int relocateToLocationId, int amountToRelocate) {
    while (amountToRelocate > 0) {
      for (Bilby animal : animals) {
        if (animal.checkAliveInLocation(relocateFromLocationId)) {
          animal.relocateTo(relocateToLocationId);
          amountToRelocate--;
          break;
        }
      }
    }
  }

  public void killOverloadedBilbiesInLocation(int locationId) {
    int LOCATION_LIMIT = 20;
    int bilbiesInLocationCount = this.getAliveCount(locationId);
    if (bilbiesInLocationCount > LOCATION_LIMIT) {
      int bilbiesExcess = bilbiesInLocationCount - LOCATION_LIMIT;
      while (bilbiesExcess > 0) {
        for (Bilby animal : animals) {
          if (animal.checkAliveInLocation(locationId)) {
            animal.dieFromLocationOverloaded();
            bilbiesExcess--;
            break;
          }
        }
      }
    }
  }
}