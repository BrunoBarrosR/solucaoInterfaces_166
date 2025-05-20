package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

import java.time.Duration;

public class RentalService {

    private static final Double PRICE_PER_HOUR = 10.00;
    private static final Double PRICE_PER_DAY = 130.00;

    private final TaxService taxService;

    public RentalService(TaxService taxService) {
        this.taxService = taxService;
    }

    public void processInvoice(CarRental carRental) {

        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes / 60.0;

        double basicPayment;
        if (hours <= 12.0) {
            basicPayment = PRICE_PER_HOUR * Math.ceil(hours);
        } else {
            basicPayment = PRICE_PER_DAY * Math.ceil(hours/24.0);
        }

        double tax = taxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
