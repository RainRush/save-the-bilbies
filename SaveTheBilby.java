import java.util.ArrayList;

public class SaveTheBilby {
  private FileRepository fileRepository;
  private UuidManager uuidManager;
  private ArrayList<Location> locations;
  private String areaName;
  private int monthsLeft;

  public SaveTheBilby() {
    uuidManager = new UuidManager();
    fileRepository = new FileRepository();
    locations = new ArrayList<Location>();
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
      // this is not ideal, too many params
      locations.add(new Location(uuidManager, i, bilbiesCount, foxesCount, catsCount));
    }
  }

  public void simulate() {
    while(monthsLeft > 0) {
      for(Location location : locations) {
        location.runMonthlySimulation();
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

    for(Location location : saveTheBilby.locations) {
      System.out.print(location.getAliveBilbyCount());
      System.out.print(", ");
      System.out.print(location.getDeadBilbyCount());
      System.out.print(", ");
      System.out.print(location.getAliveFoxCount());
      System.out.print(", ");
      System.out.print(location.getDeadFoxCount());
      System.out.print(", ");
      System.out.print(location.getAliveCatCount());
      System.out.print(", ");
      System.out.println(location.getDeadCatCount());
    }
  }
}