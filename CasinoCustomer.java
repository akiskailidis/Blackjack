public class CasinoCustomer {
    private String name;
    private double money;

    // Constructor initializes name and starting money
    public CasinoCustomer(String name, double money) {
        this.name = name;
        this.money = money;
    }

    // Subtracts a lost bet from the customer's money
    public void payBet(double amount) {
        money -= amount;
    }

    // Adds a won bet to the customer's money
    public void collectBet(double amount) {
        money += amount;
    }
	
	public double getMoney() {
		return money;
	}


    // Checks if the customer can afford a given bet
    public boolean canCover(double amount) {
        return money >= amount;
    }

    // Checks if the customer is broke (money < 1)
    public boolean isBroke() {
        return money < 1.0;
    }

    // Returns the customer's name
    @Override
    public String toString() {
        return name;
    }

    // Prints the name and current money
    public void printState() {
        System.out.println(name + " has " + money + " euros");
    }
}
