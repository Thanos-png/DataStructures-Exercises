import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dynamic_Median {

    public static void main(String[] args) {
        String fileName = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            PQ<City> minHeap = new PQ<>();
            ReversedPQ<City> maxHeap = new ReversedPQ<>(new CityComparator());

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // Process the line and create a City object
                String[] parts = line.split("\\s+");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int population = Integer.parseInt(parts[2]);
                int influenzaCases = Integer.parseInt(parts[3]);
                City city = new City(id, name, population, influenzaCases);

                // Update heaps
                if (maxHeap.isEmpty() || city.compareTo(maxHeap.min()) < 0) {
                    maxHeap.insert(city);
                } else {
                    minHeap.insert(city);
                }

                // Balance the heaps
                while (maxHeap.size() > minHeap.size() + 1) {
                    minHeap.insert(maxHeap.getMin());
                }
                while (minHeap.size() > maxHeap.size()) {
                    maxHeap.insert(minHeap.getMin());
                }

                // Calculate and print median after every 5 lines
                if (lineNumber % 5 == 0) {
                    double median = calculateMedian(minHeap, maxHeap);
                    System.out.println(median);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double calculateMedian(PQ<City> minHeap, ReversedPQ<City> maxHeap) {
        double densityMin = calculateDensity(minHeap.min());
        double densityMax = calculateDensity(maxHeap.min());

        if (minHeap.size() == maxHeap.size()) {
            if (minHeap.min().compareTo(maxHeap.min()) < 0) {
                return densityMin;
            } else {
                return densityMax;
            }
        } else {
            return densityMax;
        }
    }

    private static double calculateDensity(City city) {
        return Math.round((double) city.getInfluenzaCases() / city.getPopulation() * 50000 * 100) / 100.0;
    }
}
