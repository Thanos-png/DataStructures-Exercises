import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DynamicInfluenza_k_withPQ {
    public static void main(String[] args) {

        // The parameter k
        String kStr = args[0];
        int k = Integer.parseInt(kStr);

        // The file name
        String fileName = args[1];

        try {
            // Read cities from the file
            PQ<City> citiesQueue = readCitiesFromFile(fileName, k);

            // Check if k is valid
            if (k > citiesQueue.size()) {
                System.out.println("Error: k is greater than the total number of cities.");
                System.exit(1);
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    private static void printTopKCities(PQ<City> citiesQueue, int k) {
        // Create a copy of the priority queue
        PQ<City> copyOfCitiesQueue = citiesQueue.copy();

        // Print the k cities with the lowest cases per 50,000 inhabitants
        System.out.println("The top k(" + k + ") cities are:");
        for (int i = 0; i < k; i++) {
            System.out.println(copyOfCitiesQueue.min().getName());
            copyOfCitiesQueue.getMin();
        }
    }

    private static PQ<City> readCitiesFromFile(String fileName, int k) throws IOException {
        PQ<City> citiesQueue = new PQ<>();
        ReversedPQ<City> reversedCitiesQueue = new ReversedPQ<>(new CityComparator());

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int removedCityID;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int population = Integer.parseInt(parts[2]);
                int influenzaCases = Integer.parseInt(parts[3]);
                City city = new City(id, name, population, influenzaCases);

                // If the priority queue size exceeds k, remove the city with the highest density
                if (citiesQueue.size() >= k) {
                    if (city.compareTo(reversedCitiesQueue.min()) < 0) {
                        removedCityID = reversedCitiesQueue.getMin().getID();
                        reversedCitiesQueue.insert(city);

                        citiesQueue.remove(removedCityID);
                        citiesQueue.insert(city);
                    }
                } else {
                    reversedCitiesQueue.insert(city);
                    citiesQueue.insert(city);
                }

                if (citiesQueue.size() == k) {
                    printTopKCities(citiesQueue, k);
                }
            }
        }

        return citiesQueue;
    }
}
