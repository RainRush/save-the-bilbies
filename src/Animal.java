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

  public void setUniqueId(String _uniqueId) {
    uniqueId = _uniqueId;
  }

  public int getLocationId() {
    return locationId;
  }

  public void setLocationId(int _locationId) {
    locationId = _locationId;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean _status) {
    status = _status;
  }

  public boolean getBornDuringSimulation() {
    return bornDuringSimulation;
  }

  public void setBornDuringSimulation(boolean _bornDuringSimulation) {
    bornDuringSimulation = _bornDuringSimulation;
  }

  public boolean checkNewBirth() {
    return bornDuringSimulation;
  }

  public boolean checkAlive() {
    return status;
  }

  public boolean checkAliveInLocation(int locationId) {
    boolean isAlive = this.checkAlive();
    boolean isInLocation = this.getLocationId() == locationId;
    return isAlive && isInLocation;
  }

  public boolean checkNotAliveInLocation(int locationId) {
    boolean isNotAlive = !this.checkAlive();
    boolean isInLocation = this.getLocationId() == locationId;
    return isNotAlive && isInLocation;
  }

  public void relocateTo(int _locationId) {
    locationId = _locationId;
  }

  protected void setDeath() {
    status = false;
  }
}