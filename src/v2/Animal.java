public class Animal {
  protected String uniqueId;
  protected boolean status;
  protected boolean bornDuringSimulation;

  protected Animal() {
    uniqueId = "";
    status = true;
    bornDuringSimulation = false;
  }

  protected Animal(String _uniqueId, boolean _bornDuringSimulation) {
    uniqueId = _uniqueId;
    status = true;
    bornDuringSimulation = _bornDuringSimulation;
  }

  public String getUniqueId() {
    return uniqueId;
  }

  public void setUniqueId(String _uniqueId) {
    uniqueId = _uniqueId;
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

  // public boolean checkAliveInLocation(int locationId) {
  //   boolean isAlive = this.checkAlive();
  //   boolean isInLocation = this.getLocationId() == locationId;
  //   return isAlive && isInLocation;
  // }

  // public boolean checkNotAliveInLocation(int locationId) {
  //   boolean isNotAlive = !this.checkAlive();
  //   boolean isInLocation = this.getLocationId() == locationId;
  //   return isNotAlive && isInLocation;
  // }

  // public void relocateTo(int _locationId) {
  //   locationId = _locationId;
  // }

  protected void setDeath() {
    status = false;
  }
}