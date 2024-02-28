import java.util.Comparator;

public class CityComparator implements Comparator<City> {

    @Override
    public int compare(City city1, City city2) {
        double densityCity1 = calculateDensity(city1);
        double densityCity2 = calculateDensity(city2);

        if (densityCity1 < densityCity2) {
            return 1;
        } else if (densityCity1 > densityCity2) {
            return -1;
        } else {
            int nameComparison = city2.getName().compareTo(city1.getName());
            if (nameComparison != 0) {
                return nameComparison;
            } else {
                return Integer.compare(city2.getID(), city1.getID());
            }
        }
    }

    private static double calculateDensity(City city) {
        return Math.round((double) city.getInfluenzaCases() / city.getPopulation() * 50000 * 100) / 100.0;
    }
}
