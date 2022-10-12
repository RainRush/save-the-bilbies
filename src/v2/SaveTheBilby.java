import java.util.ArrayList;
import java.util.Scanner;

public class SaveTheBilby {
  private FileRepository fileRepository;
  private IdManager idManager;
  private ArrayList<Location> locations;
  private String areaName;
  private int monthsLeft;

  public SaveTheBilby() {
    idManager = new IdManager();
    fileRepository = new FileRepository();
    locations = new ArrayList<Location>();
    areaName = "";
    monthsLeft = 12;
  }

  private void displayInterventionActions(ArrayList<String> availableLocationIds) {
    System.out.println("Intervention Actions: ");
    for (String availableLocationId : availableLocationIds) {
      for (Location location : locations) {
        int locationId = location.getLocationId();
        String stringifiedLocationId = Integer.toString(locationId);
        if (stringifiedLocationId.equals(availableLocationId)) {
          System.out.println(availableLocationId + " - " + location.getAnimalCountByAliveDeath("FOX", "ALIVE") + " foxes and " + location.getAnimalCountByAliveDeath("CAT", "ALIVE") + " cats");
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
        System.out.print(location.getAnimalCountByAliveDeath("BILBY", "ALIVE"));
        System.out.print(", ");
        System.out.print(location.getAnimalCountByAliveDeath("BILBY", "DEAD"));
        System.out.print(", ");
        System.out.print(location.getAnimalCountByAliveDeath("FOX", "ALIVE"));
        System.out.print(", ");
        System.out.print(location.getAnimalCountByAliveDeath("FOX", "DEAD"));
        System.out.print(", ");
        System.out.print(location.getAnimalCountByAliveDeath("CAT", "ALIVE"));
        System.out.print(", ");
        System.out.print(location.getAnimalCountByAliveDeath("CAT", "DEAD"));
        System.out.println();
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
        // this.relocateEnquiry();
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

      Location location = new Location(idManager, i);
      locations.add(location);
      location.createNewAnimals(bilbiesCount, "BILBY", false);
      location.createNewAnimals(foxesCount, "FOX", false);
      location.createNewAnimals(catsCount, "CAT", false);
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
    String populationEndFilePath = "populationFinish.txt";
    System.out.println("Result written to file at path: " + populationEndFilePath);

    ArrayList<int[]> results = new ArrayList<int[]>();
    for (Location location : locations) {
      int bilbiesAlive = location.getAnimalCountByAliveDeath("BILBY", "ALIVE");
      int bilbiesDead = location.getAnimalCountByAliveDeath("BILBY", "DEAD");
      int foxesAlive = location.getAnimalCountByAliveDeath("FOX", "ALIVE");
      int foxesDead = location.getAnimalCountByAliveDeath("FOX", "DEAD");
      int catsAlive = location.getAnimalCountByAliveDeath("CAT", "ALIVE");
      int catsDead = location.getAnimalCountByAliveDeath("CAT", "DEAD");
      int[] locationResult = {bilbiesAlive, bilbiesDead, foxesAlive, foxesDead, catsAlive, catsDead};
      results.add(locationResult);
    }
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