import java.util.ArrayList;

public class Location {
  private int locationId;
  private UuidManager uuidManager;
  private BilbyManager bilbyManager;
  private FoxManager foxManager;
  private CatManager catManager;

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

  public void runMonthlySimulation() {
    bilbyManager.bearNewAnimals(locationId);
    foxManager.bearNewAnimals(locationId);
    catManager.bearNewAnimals(locationId);

    foxManager.executeHunt(bilbyManager, locationId);
    catManager.executeHunt(bilbyManager, locationId);
  }

  public void relocateOutBilby() {
    // to implement
  }

  public void relocateInBilby() {
    // to implement
  }

  public int getLocationId() {
    return locationId;
  }
}