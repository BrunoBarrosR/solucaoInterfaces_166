package model.services;

public class UnitedStatesTaxService implements TaxService{

    @Override
    public double tax(double amount) {
        if (amount <= 100.0) {
            return amount * 0.6;
        } else {
            return amount * 0.3;
        }
    }
}
