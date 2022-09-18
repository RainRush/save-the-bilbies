public class Animal {
  protected String uniqueId;
  protected int locationId;
  protected boolean status;
  protected boolean bornDuringSimulation;

  protected Animal() {
    uniqueId = "";
    locationId = 0;
    status = true;
    bornDuringSimulation = false;
  }

  protected Animal(String _uniqueId, int _locationId, boolean _bornDuringSimulation) {
    uniqueId = _uniqueId;
    locationId = _locationId;
    status = true;
    bornDuringSimulation = _bornDuringSimulation;
  }

  public String getUniqueId() {
    return uniqueId;
  }

  public int getLocationId() {
    return locationId;
  }

  public boolean getStatus() {
    return status;
  }

  public boolean getBornDuringSimulation() {
    return bornDuringSimulation;
  }

  public boolean checkNewBirth() {
    return bornDuringSimulation;
  }

  public boolean checkAlive() {
    return status;
  }

  public void relocateTo(int _locationId) {
    locationId = _locationId;
  }

  protected void setDeath() {
    status = false;
  }
}