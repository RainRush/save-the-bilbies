import java.util.ArrayList;

public class SaveTheBilby {
  private FileRepository fileRepository;
  private UuidManager uuidManager;
  private ArrayList<Location> locations;
  private BilbyManager bilbyManager;
  private FoxManager foxManager;
  private CatManager catManager;
  private String areaName;
  private int monthsLeft;

  public SaveTheBilby() {
    uuidManager = new UuidManager();
    fileRepository = new FileRepository();
    locations = new ArrayList<Location>();
    bilbyManager = new BilbyManager(uuidManager);
    foxManager = new FoxManager(uuidManager);
    catManager = new CatManager(uuidManager);
    areaName = "";
    monthsLeft = 12;
  }

  public void setupEnvironment() {
    String populationStartFilePath = "populationStart.txt";
    ArrayList<int[]> populationsOfLocations = fileRepository.readPopulationFromPath(populationStartFilePath);

    for (int i = 0; i < populationsOfLocations.size(); i++) {
      int bilbiesCount = populationsOfLocations.get(i)[0];
      int foxesCount = populationsOfLocations.get(i)[1];
      int catsCount = populationsOfLocations.get(i)[2];

      Location location = new Location(uuidManager, i);
      location.setBilbyManager(bilbyManager);
      location.setFoxManager(foxManager);
      location.setCatManager(catManager);
      locations.add(location);
      bilbyManager.createNewAnimals(i, bilbiesCount, false);
      foxManager.createNewAnimals(i, foxesCount, false);
      catManager.createNewAnimals(i, catsCount, false);
    }
  }

  public void simulate() {
    while(monthsLeft > 0) {
      System.out.print("Month Left: ");
      System.out.println(monthsLeft);

      for(Location location : locations) {
        location.runMonthlySimulation();

        int locationId = location.getLocationId();

        System.out.print("Location: ");
        System.out.print(locationId);
        System.out.print(" - ");
        System.out.print(bilbyManager.getAliveCount(locationId));
        System.out.print(", ");
        System.out.print(bilbyManager.getDeadCount(locationId));
        System.out.print(", ");
        System.out.print(foxManager.getAliveCount(locationId));
        System.out.print(", ");
        System.out.print(foxManager.getDeadCount(locationId));
        System.out.print(", ");
        System.out.print(catManager.getAliveCount(locationId));
        System.out.print(", ");
        System.out.println(catManager.getDeadCount(locationId));
      }

      // list current result for each location
      // ask if interventions are needed
      monthsLeft--;
    }
  }

  public static void main(String[] args) {
    SaveTheBilby saveTheBilby = new SaveTheBilby();

    // display greeting message
    // ask area name

    saveTheBilby.setupEnvironment();
    saveTheBilby.simulate();    
  }
}