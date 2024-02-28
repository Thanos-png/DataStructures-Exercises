
public interface CityInterface {
    /**
	 * return the id of a city
	 * @return int-type item
	 */
    int getID();

    /**
	 * return the name of a city
	 * @return string-type item
	 */
    String getName();

    /**
	 * return the population of a city
	 * @return int-type item
	 */
    int getPopulation();

    /**
	 * return the total number of flue cases in a city
	 * @return int-type item
	 */
    int getInfluenzaCases();

    /**
	 * sets an int-type item as the id of a city
	 */
    void setID(int ID);

    /**
	 * sets an string-type item as the name of a city
	 */
    void setName(String name);

    /**
	 * sets an int-type item as the population of a city
	 */
    void setPopulation(int population);

    /**
	 * sets an int-type item as the total number of flue cases in a city
	 */
    void setInfluenzaCases(int influenzaCases);
}
