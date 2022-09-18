import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class FileRepository {
  public ArrayList<int[]> readPopulationFromPath(String filePath) {
    ArrayList<int[]> populationsOfLocations = new ArrayList<int[]>();

    try {
      File fileObj = new File(filePath);
      Scanner scanner = new Scanner(fileObj);
      while (scanner.hasNextLine()) {
        String lineData = scanner.nextLine();
        String[] populations = lineData.split(",");
        int[] locationPopulations = {
          Integer.parseInt(populations[0]), 
          Integer.parseInt(populations[1]), 
          Integer.parseInt(populations[2])
        };
        populationsOfLocations.add(locationPopulations);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    return populationsOfLocations;
  }

  public void writeResultToPath(String filePath, int[][] results) {
    // to be implemented
  }
}