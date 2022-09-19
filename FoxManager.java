import java.util.ArrayList;

public class FoxManager extends AnimalManager<Fox> {
  private UuidManager uuidManager;

  public FoxManager() {
    uuidManager = new UuidManager();
  }

  public FoxManager(UuidManager _uuidManager) {
    uuidManager = _uuidManager;
  }

  private void createNewAnimal(int locationId, boolean isBornInSimulation) {
    String uniqueId = uuidManager.generateUniqueIdForFox();
    animals.add(new Fox(uniqueId, locationId, isBornInSimulation));
  }

  public void createNewAnimals(int locationId, int amount, boolean isBornInSimulation) {
    for (int i = 0; i < amount; i++) {
      createNewAnimal(locationId, isBornInSimulation); 
    }
  }

  public void bearNewAnimals(int locationId) {
    int newBornCount = 0;
    for (Fox animal : animals) {
      boolean isAlive = animal.checkAlive();
      boolean isInLocation = animal.getLocationId() == locationId;
      boolean isGivingBirth = animal.giveBirthAttempt();
      if (isAlive && isInLocation && isGivingBirth) {
        newBornCount++;
      }
    }

    createNewAnimals(locationId, newBornCount, true);
  }

  public void executeHunt(BilbyManager bilbyManager, int locationId) {
    for (Fox animal : animals) {
      boolean isInLocation = animal.getLocationId() == locationId;
      boolean isAlive = animal.checkAlive();
      boolean haveBilbiesLeft = bilbyManager.checkBilbiesLeftInLocation(locationId);
      if (isAlive && isInLocation && haveBilbiesLeft) {
        if (animal.huntBilbyAttempt()) {
          bilbyManager.chooseOneToDieFromHunt(locationId);
        } else {
          animal.punishFailHunt();
        }
      }
    }
  }
}