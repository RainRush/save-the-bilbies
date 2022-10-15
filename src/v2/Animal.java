public class Animal {
  protected String uniqueId;
  protected boolean status;
  protected int giveBirthRate;
  protected boolean bornDuringSimulation;
  private Random randomUtils;

  protected Animal() {
    uniqueId = "";
    status = true;
    giveBirthRate = 0;
    bornDuringSimulation = false;
    randomUtils = new Random();

  }

  protected Animal(String _uniqueId, boolean _bornDuringSimulation, int _giveBirthRate) {
    uniqueId = _uniqueId;
    status = true;
    giveBirthRate = _giveBirthRate;
    bornDuringSimulation = _bornDuringSimulation;
    randomUtils = new Random();
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

  public boolean giveBirthAttempt() {
    return randomUtils.hasSucceedByPercentage(giveBirthRate);
  }

  public boolean checkAlive() {
    return status;
  }

  protected void setDeath() {
    status = false;
  }
}