import java.util.ArrayList;

public class Location {
  private int locationId;
  private ArrayList<Bilby> bilbies;
  private ArrayList<Cat> cats;
  private ArrayList<Fox> foxes;
  private IdManager idManager;
  private boolean haveIntervenedLocation = false;

  public Location() {
    locationId = 0;
    bilbies = new ArrayList<Bilby>();
    cats = new ArrayList<Cat>();
    foxes = new ArrayList<Fox>();
    idManager = new IdManager();
  }

  public Location(IdManager _idManager, int _locationId) {
    locationId = _locationId;
    bilbies = new ArrayList<Bilby>();
    cats = new ArrayList<Cat>();
    foxes = new ArrayList<Fox>();
    idManager = _idManager;
  }

  public int getLocationId() {
    return locationId;
  }

  public boolean checkInterventionAvailibility() {
    return !haveIntervenedLocation;
  }

  private void killOverloadedBilbiesInLocation() {
    int LOCATION_LIMIT = 20;
    int bilbiesInLocationCount = getAnimalCountByAliveDeath("BILBY", "ALIVE");
    if (bilbiesInLocationCount > LOCATION_LIMIT) {
      int bilbiesExcess = bilbiesInLocationCount - LOCATION_LIMIT;
      while (bilbiesExcess > 0) {
        for (Bilby bilby : bilbies) {
          if (bilby.getStatus() == true) {
            bilby.dieFromLocationOverloaded();
            bilbiesExcess--;
            break;
          }
        }
      }
    }
  }

  private void createNewAnimal(String animalType, boolean isBornInSimulation) {
    if (animalType == "BILBY") {
      String id = idManager.getAndBumpNextBilbyId();
      bilbies.add(new Bilby(id, isBornInSimulation));
    }
    if (animalType == "CAT") {
      String id = idManager.getAndBumpNextCatId();
      cats.add(new Cat(id, isBornInSimulation));
    }
    if (animalType == "FOX") {
      String id = idManager.getAndBumpNextFoxId();
      foxes.add(new Fox(id, isBornInSimulation));
    }
  }

  public void createNewAnimals(int amount, String animalType, boolean isBornInSimulation) {
    for (int i = 0; i < amount; i++) {
      createNewAnimal(animalType, isBornInSimulation);
    }
  }

  private void bearNewAnimals() {
    int newBornBilbyCount = 0;
    int newBornCatCount = 0;
    int newBornFoxCount = 0;
    
    for (Bilby bilby : bilbies) {
      boolean isGivingBirth = bilby.giveBirthAttempt();
      if (bilby.checkAlive() && isGivingBirth) {
        newBornBilbyCount++;
      }
    }
    for (Cat cat : cats) {
      boolean isGivingBirth = cat.giveBirthAttempt();
      if (cat.checkAlive() && isGivingBirth) {
        newBornCatCount++;
      }
    }
    for (Fox fox : foxes) {
      boolean isGivingBirth = fox.giveBirthAttempt();
      if (fox.checkAlive() && isGivingBirth) {
        newBornFoxCount++;
      }
    }
    createNewAnimals(newBornBilbyCount, "BILBY", false);
    createNewAnimals(newBornCatCount, "CAT", false);
    createNewAnimals(newBornFoxCount, "FOX", false);
  }

  private void chooseOneBilbyToDieFromHunt() {
    for (Bilby bilby : bilbies) {
      if (bilby.checkAlive()) {
        bilby.dieFromHunt();
        break;
      }
    }
  }

  private void executeHunt() {
    for (Cat cat : cats) {
      int bilbiesAliveCount = getAnimalCountByAliveDeath("BILBY", "ALIVE");
      boolean haveBilbiesLeft = bilbiesAliveCount > 0;
      if (cat.checkAlive() && haveBilbiesLeft) {
        if (cat.huntBilbyAttempt()) {
          chooseOneBilbyToDieFromHunt();
        } else {
          cat.punishFailHunt();
        }
      }
    }
    for (Fox fox : foxes) {
      int bilbiesAliveCount = getAnimalCountByAliveDeath("BILBY", "ALIVE");
      boolean haveBilbiesLeft = bilbiesAliveCount > 0;
      if (fox.checkAlive() && haveBilbiesLeft) {
        if (fox.huntBilbyAttempt()) {
          chooseOneBilbyToDieFromHunt();
        } else {
          fox.punishFailHunt();
        }
      }
    }
  } 

  public void runMonthlySimulation() {
    killOverloadedBilbiesInLocation();
    bearNewAnimals();
    executeHunt();
  }

  public int getAnimalCountByAliveDeath(String animalType, String aliveDeathType) {
    boolean status = aliveDeathType == "ALIVE";
    int count = 0;
    if (animalType == "BILBY") {
      for (Bilby bilby : bilbies) {
        if (bilby.getStatus() == status) {
          count++;
        }
      }
    }
    if (animalType == "CAT") {
      for (Cat cat : cats) {
        if (cat.getStatus() == status) {
          count++;
        }
      }
    }
    if (animalType == "FOX") {
      for (Fox fox : foxes) {
        if (fox.getStatus() == status) {
          count++;
        }
      }
    }
    return count;
  }

  public void intervene() {
    int foxesIntervenedCount = interveneByAnimalType("FOX");
    int catsIntervenedCount = interveneByAnimalType("CAT");
    System.out.println(foxesIntervenedCount + " foxes and " + catsIntervenedCount + " cats had been killed");
    haveIntervenedLocation = true;
  }

  private int interveneByAnimalType(String animalType) {
    int animalIntervenedCount = 0;
    if (animalType == "CAT") {
      for (Cat cat : cats) {
        if (cat.checkAlive() && cat.interventionAttempt()) {
          cat.dieFromIntervention();
          animalIntervenedCount++;
        }
      }
    }
    if (animalType == "FOX") {
      for (Fox fox : foxes) {
        if (fox.checkAlive() && fox.interventionAttempt()) {
          fox.dieFromIntervention();
          animalIntervenedCount++;
        }
      }
    }
    return animalIntervenedCount;
  }
}