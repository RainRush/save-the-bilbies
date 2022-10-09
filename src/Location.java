import java.util.ArrayList;

public class Location {
  private int locationId;
  private UuidManager uuidManager;
  private BilbyManager bilbyManager;
  private FoxManager foxManager;
  private CatManager catManager;
  private boolean haveIntervenedLocation = false;

  public Location() {
    locationId = 0;
    uuidManager = new UuidManager();
  }

  public Location(UuidManager _uuidManager, int _locationId) {
    locationId = _locationId;
    uuidManager = _uuidManager;
  }

  public void setBilbyManager(BilbyManager _bilbyManager) {
    bilbyManager = _bilbyManager;
  }

  public void setFoxManager(FoxManager _foxManager) {
    foxManager = _foxManager;
  }

  public void setCatManager(CatManager _catManager) {
    catManager = _catManager;
  }

  public boolean checkInterventionAvailibility() {
    return !haveIntervenedLocation;
  }

  public void runMonthlySimulation() {
    bilbyManager.killOverloadedBilbiesInLocation(locationId);

    bilbyManager.bearNewAnimals(locationId);
    foxManager.bearNewAnimals(locationId);
    catManager.bearNewAnimals(locationId);

    foxManager.executeHunt(bilbyManager, locationId);
    catManager.executeHunt(bilbyManager, locationId);
  }

  public void intervene() {
    int foxesIntervenedCount = foxManager.interveneLocation(locationId);
    int catsIntervenedCount = catManager.interveneLocation(locationId);
    System.out.println(foxesIntervenedCount + " foxes and " + catsIntervenedCount + " cats had been killed");
    haveIntervenedLocation = true;
  }

  public int getLocationId() {
    return locationId;
  }
}