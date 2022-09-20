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

  // relocate related, move out?
  private ArrayList<String> getRelocateFromAvailableLocations() {
    ArrayList<String> relocateAvailableLocations = new ArrayList<String>();
    int LOCATION_LIMIT = 20;
    for (Location location : locations) {
      int bilbiesInLocationCount = bilbyManager.getAliveCount(location.getLocationId());
      if (bilbiesInLocationCount > LOCATION_LIMIT) {
        relocateAvailableLocations.add(Integer.toString(location.getLocationId()));
      }
    }
    return relocateAvailableLocations;
  }

  private ArrayList<String> getRelocateToAvailableLocations() {
    ArrayList<String> relocateAvailableLocations = new ArrayList<String>();
    for (Location location : locations) {
      relocateAvailableLocations.add(Integer.toString(location.getLocationId()));
    }
    return relocateAvailableLocations;
  }

  private void displayRelocateFromActions(ArrayList<String> availableLocationIds) {
    int LOCATION_LIMIT = 20;
    for (String availableLocationId : availableLocationIds) {
      for (Location location : locations) {
        int locationId = location.getLocationId();
        String stringifiedLocationId = Integer.toString(locationId);
        if (stringifiedLocationId.equals(availableLocationId)) {
          System.out.println(availableLocationId + " - " + (bilbyManager.getAliveCount(locationId) - LOCATION_LIMIT) + " bilbies exceeded location limit, choose to move");
        }
      }
    }
    System.out.println("B - To Go Back to User Actions.");
  }

  private void displayRelocateToActions(ArrayList<String> availableLocationIds) {
    for (String availableLocationId : availableLocationIds) {
      for (Location location : locations) {
        int locationId = location.getLocationId();
        String stringifiedLocationId = Integer.toString(locationId);
        if (stringifiedLocationId.equals(availableLocationId)) {
          System.out.println(availableLocationId + " - " + bilbyManager.getAliveCount(locationId) + " bilbies in location");
        }
      }
    }
    System.out.println("B - To Go Back to User Actions.");
  }

  private void relocateEnquiry() {
    String stringifiedRelocateFrom = "";
    String stringifiedRelocateTo = "";
    int relocateFromLocationId = -1;
    int relocateToLocationId = -1;
    int amountToRelocate = 0;
    boolean goBackFlag = false;
    ArrayList<String> availableRelocateFromLocationIds = this.getRelocateFromAvailableLocations();
    ArrayList<String> availableRelocateToLocationIds = this.getRelocateToAvailableLocations();
    Scanner scanner = new Scanner(System.in);

    while(!goBackFlag && !availableRelocateFromLocationIds.contains(stringifiedRelocateFrom)) {
      System.out.println("Choose location to relocate from");
      this.displayRelocateFromActions(availableRelocateFromLocationIds);
      stringifiedRelocateFrom = scanner.nextLine();
      if (stringifiedRelocateFrom.equalsIgnoreCase("B")) {
        goBackFlag = true;
      } else if (availableRelocateFromLocationIds.contains(stringifiedRelocateFrom)) {
        relocateFromLocationId = Integer.parseInt(stringifiedRelocateFrom);
      } else {
        System.out.println("Invalid option");
      }
    }

    while(!goBackFlag && !availableRelocateToLocationIds.contains(stringifiedRelocateTo)) {
      System.out.println("Choose location to relocate to");
      this.displayRelocateToActions(availableRelocateToLocationIds);
      stringifiedRelocateTo = scanner.nextLine();
      if (stringifiedRelocateTo.equalsIgnoreCase("B")) {
        goBackFlag = true;
      } else if (availableRelocateToLocationIds.contains(stringifiedRelocateTo)) {
        relocateToLocationId = Integer.parseInt(stringifiedRelocateTo);
      } else {
        System.out.println("Invalid option");
      }
    }

    if (goBackFlag) {
      System.out.println("Back to User Options.");
    } else {
      int LOCATION_LIMIT = 20;
      amountToRelocate = bilbyManager.getAliveCount(relocateFromLocationId) - LOCATION_LIMIT;
      bilbyManager.relocate(relocateFromLocationId, relocateToLocationId, amountToRelocate);
      System.out.println("Moved " + amountToRelocate + " bilbies from location " + relocateFromLocationId + " to location " + relocateToLocationId);
    }
  }
  // relocate related, move out?

  // intervention related, move out?
  private void displayInterventionActions(ArrayList<String> availableLocationIds) {
    System.out.println("Intervention Actions: ");
    for (String availableLocationId : availableLocationIds) {
      for (Location location : locations) {
        int locationId = location.getLocationId();
        String stringifiedLocationId = Integer.toString(locationId);
        if (stringifiedLocationId.equals(availableLocationId)) {
          System.out.println(availableLocationId + " - " + foxManager.getAliveCount(locationId) + " foxes and " + catManager.getAliveCount(locationId) + " cats");
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

  private ArrayList<String> getInterveneAvailableLocations() {
    ArrayList<String> interveneAvailableLocations = new ArrayList<String>();
    for (Location location : locations) {
      if (location.checkInterventionAvailibility()) {
        interveneAvailableLocations.add(Integer.toString(location.getLocationId()));
      }
    }
    return interveneAvailableLocations;
  }

  private void interveneEnquiry() {
    String selectedOption = "";
    ArrayList<String> availableLocationIds = this.getInterveneAvailableLocations();
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
    // show the results
    // show some statistics
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