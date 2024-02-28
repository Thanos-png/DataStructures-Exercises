import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.nio.file.Paths;

public class Influenza_k {
    public static void main(String[] args) {

        // The parameter k
        String kStr = args[0];
        int k = Integer.parseInt(kStr);

        // The file name
        String fileName = args[1];
        // String fileName = getFilePath(args[1]);

        try {
            // Read cities from the file
            PQ<City> citiesQueue = readCitiesFromFile(fileName);

            // Check if k is valid
            if (k > citiesQueue.size()) {
                System.out.println("Error: k is greater than the total number of cities.");
                System.exit(1);
            }

            // Print the k cities with the lowest cases per 50,000 inhabitants
            System.out.println("The top k(" + k + ") cities are:");
            for (int i = 0; i < k; i++) {
                System.out.println(citiesQueue.min().getName());
                citiesQueue.getMin();
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    private static PQ<City> readCitiesFromFile(String fileName) throws IOException {
        PQ<City> citiesQueue = new PQ<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int population = Integer.parseInt(parts[2]);
                int influenzaCases = Integer.parseInt(parts[3]);

                City city = new City(id, name, population, influenzaCases);
                citiesQueue.insert(city);
            }
        }

        return citiesQueue;
    }

/*
    private static String getFilePath(String fileName) {
        // Get the parent directory of the current working directory
        String parentDirectory = Paths.get("").toAbsolutePath().getParent().toString();
        // Construct the full file path
        return Paths.get(parentDirectory, fileName).toString();
    }
*/
}
