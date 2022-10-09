public class Random {
  private int getRandom(int max) {
    return (int)(Math.random() * max + 1);
  }

  public boolean hasSucceedByPercentage(int successPercentage) {
    int MAX_PERCENTAGE = 100;
    int randomNumber = this.getRandom(MAX_PERCENTAGE);
    return randomNumber <= successPercentage;
  }
}