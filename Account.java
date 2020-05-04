public class Account {
    private String number;
    private double balance;

    public static final String EMPTY_STRING = "";

    public Account() {
        this.number = EMPTY_STRING;
        this.balance = 0;
    }

    public Account(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return number + " " + balance;
    }
}
