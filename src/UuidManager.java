import java.util.HashSet;

public class UuidManager {
  private HashSet<String> uuidsSet;

  public UuidManager() {
    uuidsSet = new HashSet<String>();
  }

  public UuidManager(HashSet<String> initialUuidsSet) {
    uuidsSet = initialUuidsSet;
  }

  private char generateRandomAlphanumeric() {
    String listOfAlphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    int randomIndexOfAlphanumericList = (int)(Math.random() * listOfAlphanumeric.length());
    return listOfAlphanumeric.charAt(randomIndexOfAlphanumericList);
  }

  private String generateUniqueIdByPrefix(String prefix) {
    String generatedUniqueId = "";
    do {
      generatedUniqueId = prefix;
      for (int i = 0; i < 3; i++) {
        generatedUniqueId += this.generateRandomAlphanumeric();
      }
    } while(uuidsSet.contains(generatedUniqueId));

    uuidsSet.add(generatedUniqueId);
    return generatedUniqueId;
  }

  public String generateUniqueIdForBilby() {
    return this.generateUniqueIdByPrefix("B");
  }

  public String generateUniqueIdForCat() {
    return this.generateUniqueIdByPrefix("C");
  }

  public String generateUniqueIdForFox() {
    return this.generateUniqueIdByPrefix("F");
  }

  public void resetManager() {
    uuidsSet.clear();
  }
}