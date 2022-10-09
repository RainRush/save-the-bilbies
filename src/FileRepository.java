import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
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

  private void upsertFile(String filePath) {
    try {
      File fileObj = new File(filePath);
      if (fileObj.createNewFile()) {
        System.out.println("File created: " + fileObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void writeResultToPath(String filePath, ArrayList<int[]> results) {
    this.upsertFile(filePath);

    try {
      FileWriter myWriter = new FileWriter(filePath);
      for (int[] resultRow : results) {
        String rowMessage = "";
        for (int i = 0; i < resultRow.length; i++) {
          rowMessage += Integer.toString(resultRow[i]);
          if (i < resultRow.length - 1) {
            rowMessage += ",";
          }
        }
        myWriter.write(rowMessage + "\n");
      }
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}