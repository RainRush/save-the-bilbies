public class IdManager {
  private int bilbiesCounter;
  private int foxesCounter;
  private int catsCounter;

  public IdManager() {
    this.bilbiesCounter = 0;
    this.foxesCounter = 0;
    this.catsCounter = 0;
  }

  public String getAndBumpNextBilbyId() {
    this.bilbiesCounter++;
    return "B" + String.format("%03d", this.bilbiesCounter);
  }

  public String getAndBumpNextFoxId() {
    this.foxesCounter++;
    return "F" + String.format("%03d", this.foxesCounter);
  }

  public String getAndBumpNextCatId() {
    this.catsCounter++;
    return "C" + String.format("%03d", this.catsCounter);
  }
}