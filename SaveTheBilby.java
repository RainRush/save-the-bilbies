import java.util.ArrayList;
import java.util.Scanner;

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

  private void relocateEnquiry() {
    // int relocateFrom;
    // int relocateTo;
    // int amountToRelocate = 0;
    // ask from which location (show list)
    // to which location (show list)
    // how many

    // are there rules of relocation?
    // while(!selectedOption.equalsIgnoreCase("B") && !availableLocationIds.contains(selectedOption)) {

    // }

    System.out.println("Moved # bilbies from location # to location #");
  }

  private void displayInterventionActions(ArrayList<String> availableLocationIds) {
    System.out.println("Intervention Actions: ");
    for (String availableLocationId : availableLocationIds) {
      for (Location location : locations) {
        int locationId = location.getLocationId();
        String stringifiedLocationId = Integer.toString(locationId);
        if (stringifiedLocationId.equals(availableLocationId)) {
          System.out.println(availableLocationId + " - " + foxManager.getAliveCount(locationId) + " foxes and " + catManager.getAliveCount(locationId));
        }
      }
    }
    System.out.println("B - To Go Back to User Actions.");
  }

  private void executeInterventionOnLocation(int selectedLocationId) {
    for (Location location : locations) {
      if (location.getLocationId() == selectedLocationId) {
        location.intervene();
      }
    }
  }

  private ArrayList<String> getAvailableLocations() {
    ArrayList<String> availableLocations = new ArrayList<String>();
    for (Location location : locations) {
      if (location.checkInterventionAvailibility()) {
        availableLocations.add(Integer.toString(location.getLocationId()));
      }
    }
    return availableLocations;
  }

  private void interveneEnquiry() {
    String selectedOption = "";
    ArrayList<String> availableLocationIds = this.getAvailableLocations();
    Scanner scanner = new Scanner(System.in);

    while(!selectedOption.equalsIgnoreCase("B") && !availableLocationIds.contains(selectedOption)) {
      this.displayInterventionActions(availableLocationIds);
      selectedOption = scanner.nextLine();

      if (selectedOption.equalsIgnoreCase("B")) {
        System.out.println("Back to User Options.");
      } else if (availableLocationIds.contains(selectedOption)) {
        int selectedLocationId = Integer.parseInt(selectedOption);
        System.out.println("Intervened in location " + selectedLocationId);
        this.executeInterventionOnLocation(selectedLocationId);
      } else {
        System.out.println("Invalid option");
      }
    }
  }

  private void displayMonthlyStateOfAllLocations() {
    for(Location location : locations) {
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
  }

  private void displayMonthLeft(int monthsLeft) {
    System.out.println("Month Left: " + monthsLeft);
  }

  private void displayUserActions() {
    System.out.println("User Actions: ");
    System.out.println("1 - Move bilby location.");
    System.out.println("2 - Intervene.");
    System.out.println("3 - Continue monthly simulation.");
  }

  private void monthlyUserEnquiry() {
    String selectedOption = "";
    Scanner scanner = new Scanner(System.in);
    
    while(!selectedOption.equalsIgnoreCase("3")) {
      this.displayMonthLeft(monthsLeft);
      this.displayMonthlyStateOfAllLocations();
      this.displayUserActions();

      selectedOption = scanner.nextLine();

      if (selectedOption.equalsIgnoreCase("1")) {
        this.relocateEnquiry();
      } else if (selectedOption.equalsIgnoreCase("2")) {
        this.interveneEnquiry();
      } else if (selectedOption.equalsIgnoreCase("3")) {
        System.out.println("Monthly simulation...");
      } else {
        System.out.println("Invalid input.");
      }
    }
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
      monthsLeft--;
      for(Location location : locations) {
        location.runMonthlySimulation();
      }

      this.monthlyUserEnquiry();
    }
  }

  public void finishSimulationAndPersist() {
    System.out.println("Finished simulation.");
    System.out.println("The result is: ");
    String populationEndFilePath = "populationEnd.txt";
    System.out.println("Result written to file at path: " + populationEndFilePath);
    // to set up
    int[][] results = new int[0][0];
    fileRepository.writeResultToPath(populationEndFilePath, results);
  }

  public static void main(String[] args) {
    SaveTheBilby saveTheBilby = new SaveTheBilby();

    System.out.println("Welcome to Save The Bilby simulation.");
    // ask area name

    saveTheBilby.setupEnvironment();
    saveTheBilby.simulate();
    saveTheBilby.finishSimulationAndPersist();
  }
}