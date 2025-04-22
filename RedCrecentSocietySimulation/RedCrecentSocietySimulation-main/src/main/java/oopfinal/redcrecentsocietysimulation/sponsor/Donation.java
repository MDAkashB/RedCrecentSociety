

package oopfinal.redcrecentsocietysimulation.sponsor;

public class Donation {
    private String donorName;
    private double amount;
    private String paymentMethod;

    public Donation(String donorName, double amount, String paymentMethod) {
        this.donorName = donorName;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public String getDonorName() {
        return donorName;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
