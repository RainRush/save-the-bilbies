import java.util.ArrayList;

public class Location {
  private ArrayList<Bilby> bilbies;
  private ArrayList<Fox> foxes;
  private ArrayList<Cat> cats;
  private int locationId;
  private UuidManager uuidManager;

  public Location() {
    bilbies = new ArrayList<Bilby>();
    foxes = new ArrayList<Fox>();
    cats = new ArrayList<Cat>();
    locationId = 0;
    uuidManager = new UuidManager();
  }

  public Location(UuidManager _uuidManager, int _locationId, int bilbiesCount, int foxesCount, int catsCount) {
    locationId = _locationId;
    uuidManager = _uuidManager;
    bilbies = new ArrayList<Bilby>();
    foxes = new ArrayList<Fox>();
    cats = new ArrayList<Cat>();

    for (int i = 0; i < bilbiesCount; i++) {
      createNewBilby(false);
    }

    for (int i = 0; i < foxesCount; i++) {
      createNewFox(false);
    }

    for (int i = 0; i < catsCount; i++) {
      createNewCat(false);
    }
  }

  private void createNewBilby(boolean isBornInSimulation) {
    String uniqueId = uuidManager.generateUniqueIdForBilby();
    bilbies.add(new Bilby(uniqueId, locationId, isBornInSimulation));
  }

  private void createNewFox(boolean isBornInSimulation) {
    String uniqueId = uuidManager.generateUniqueIdForFox();
    foxes.add(new Fox(uniqueId, locationId, isBornInSimulation));
  }

  private void createNewCat(boolean isBornInSimulation) {
    String uniqueId = uuidManager.generateUniqueIdForCat();
    cats.add(new Cat(uniqueId, locationId, isBornInSimulation));
  }

  private void bearNewBilbies() {
    int newBornCount = 0;
    for (Bilby bilby : bilbies) {
      if (bilby.checkAlive() && bilby.giveBirthAttempt()) {
        newBornCount++;
      }
    }

    for (int i = 0; i < newBornCount; i++) {
      createNewBilby(true);
    }
  }

  private void bearNewFoxes() {
    int newBornCount = 0;
    for (Fox fox : foxes) {
      if (fox.checkAlive() && fox.giveBirthAttempt()) {
        newBornCount++;
      }
    }

    for (int i = 0; i < newBornCount; i++) {
      createNewFox(true);
    }
  }

  private void bearNewCats() {
    int newBornCount = 0;
    for (Cat cat : cats) {
      if (cat.checkAlive() && cat.giveBirthAttempt()) {
        newBornCount++;
      }
    }

    for (int i = 0; i < newBornCount; i++) {
      createNewCat(true);
    }
  }

  private boolean checkBilbiesLeftInLocation() {
    for (Bilby bilby : bilbies) {
      if (bilby.status) {
        return true;
      }
    }
    return false;
  }

  private void executeCatsHunt() {
    for (Cat cat : cats) {
      boolean haveBilbiesLeft = checkBilbiesLeftInLocation();
      if (cat.checkAlive()) {
        if (cat.huntBilbyAttempt(haveBilbiesLeft)) {
          for (Bilby bilby : bilbies) {
            if (bilby.checkAlive()) {
              bilby.dieFromHunt();
              break;
            }
          }
        } else {
          cat.punishFailHunt();
        }
      }
    }
  }

  private void executeFoxesHunt() {
    for (Fox fox : foxes) {
      boolean haveBilbiesLeft = checkBilbiesLeftInLocation();
      if (fox.checkAlive()) {
        if (fox.huntBilbyAttempt(haveBilbiesLeft)) {
          for (Bilby bilby : bilbies) {
            if (bilby.checkAlive()) {
              bilby.dieFromHunt();
              break;
            }
          }
        } else {
          fox.punishFailHunt();
        }
      }
    }
  }

  public void runMonthlySimulation() {
    bearNewBilbies();
    bearNewFoxes();
    bearNewCats();

    executeFoxesHunt();
    executeCatsHunt();
  }

  public void relocateOutBilby() {
    // to implement
  }

  public void relocateInBilby() {
    // to implement
  }

  public int getBilbyPopulation() {
    return bilbies.size();
  }

  public int getAliveBilbyCount() {
    int aliveCount = 0;
    for (Bilby bilby : bilbies) {
      if (bilby.status) {
        aliveCount++;
      }
    }
    return aliveCount;
  }

  public int getDeadBilbyCount() {
    int deadCount = 0;
    for (Bilby bilby : bilbies) {
      if (!bilby.status) {
        deadCount++;
      }
    }
    return deadCount;
  }

  public int getFoxPopulation() {
    return foxes.size();
  }
  public int getAliveFoxCount() {
    int aliveCount = 0;
    for (Fox fox : foxes) {
      if (fox.status) {
        aliveCount++;
      }
    }
    return aliveCount;
  }

  public int getDeadFoxCount() {
    int deadCount = 0;
    for (Fox fox : foxes) {
      if (!fox.status) {
        deadCount++;
      }
    }
    return deadCount;
  }

  public int getCatPopulation() {
    return cats.size();
  }
  public int getAliveCatCount() {
    int aliveCount = 0;
    for (Cat cat : cats) {
      if (cat.status) {
        aliveCount++;
      }
    }
    return aliveCount;
  }

  public int getDeadCatCount() {
    int deadCount = 0;
    for (Cat cat : cats) {
      if (!cat.status) {
        deadCount++;
      }
    }
    return deadCount;
  }
}