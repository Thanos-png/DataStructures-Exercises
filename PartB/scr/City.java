
public class City implements CityInterface, Comparable<City> {
    private int ID;
    private String name;
    private int population;
    private int influenzaCases;

    // Constructors
    public City() {
        // Default constructor
    }

    public City(int ID, String name, int population, int influenzaCases) {
        setID(ID);
        setName(name);
        setPopulation(population);
        setInfluenzaCases(influenzaCases);
    }

    // Getter methods
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getInfluenzaCases() {
        return influenzaCases;
    }

    // Setter methods
    public void setID(int ID) {
        if (ID >= 1 && ID <= 999) {
            this.ID = ID;
        } else {
            throw new IllegalArgumentException("ID must be between 1 and 999");
        }
    }

    public void setName(String name) {
        if (name.length() <= 50) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name length cannot exceed 50 characters");
        }
    }

    public void setPopulation(int population) {
        if (population >= 0 && population <= 10_000_000) {
            this.population = population;
        } else {
            throw new IllegalArgumentException("Population must be a positive number not exceeding 10,000,000");
        }
    }

    public void setInfluenzaCases(int influenzaCases) {
        if (influenzaCases >= 0 && influenzaCases <= population) {
            this.influenzaCases = influenzaCases;
        } else {
            throw new IllegalArgumentException("Influenza cases must be a non-negative number not exceeding the population");
        }
    }

    public int compareTo(City other) {
        // Compare based on cases per 50,000 inhabitants, alphabetical order, and ID
        double densityThis = calculateDensity(this);
        double densityOther = calculateDensity(other);

        if (densityThis < densityOther) {
            return -1;
        } else if (densityThis > densityOther) {
            return 1;
        } else {
            int nameComparison = this.getName().compareTo(other.getName());
            if (nameComparison != 0) {
                return nameComparison;
            } else {
                return Integer.compare(this.getID(), other.getID());
            }
        }
    }

    private static double calculateDensity(City city) {
        return Math.round((double) city.getInfluenzaCases() / city.getPopulation() * 50000 * 100) / 100.0;
    }
}
