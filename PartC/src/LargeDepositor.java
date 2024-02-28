// ATHANASIOS PANAGIOTIDIS
// p3220143

class LargeDepositor {

    private int AFM;
    private String firstName;
    private String lastName;
    private double savings;
    private double taxedIncome;

    // Default LargeDepositor constructor
    LargeDepositor() {
    }

    LargeDepositor(int AFM, String firstName, String lastName, double savings, double taxedIncome) {
        this.AFM = AFM;
        this.firstName = firstName.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.savings = savings;
        this.taxedIncome = taxedIncome;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toLowerCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toLowerCase();
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public double getTaxedIncome() {
        return taxedIncome;
    }

    public void setTaxedIncome(double taxedIncome) {
        this.taxedIncome = taxedIncome;
    }

    public int key() {return AFM;}

    public String toString() {
        return firstName + " " + lastName + " Savings: " + savings + " Taxed income: " + taxedIncome;
    }
}
