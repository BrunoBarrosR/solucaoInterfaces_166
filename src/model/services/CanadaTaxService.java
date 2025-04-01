package model.services;

public class CanadaTaxService implements TaxService{

    public double tax(double amount) {
        if (amount <= 100.00) {
            return amount * 0.5;
        } else {
            return amount * 0.2;
        }
    }
}
