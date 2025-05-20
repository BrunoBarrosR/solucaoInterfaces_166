package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.CanadaTaxService;
import model.services.RentalService;
import model.services.UnitedStatesTaxService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel");
        System.out.println("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Devolução (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
        CarRental crCan = new CarRental(start, finish, new Vehicle(carModel));
        CarRental crUSA = new CarRental(start, finish, new Vehicle(carModel));

        RentalService rentalService = new RentalService(new BrazilTaxService());

        RentalService rentalServiceCan = new RentalService(new CanadaTaxService());

        RentalService rentalServiceUSA = new RentalService(new UnitedStatesTaxService());

        rentalService.processInvoice(cr);
        rentalServiceCan.processInvoice(crCan);
        rentalServiceUSA.processInvoice(crUSA);


        System.out.println(cr.getInvoice());

        System.out.println("Canada \n" + crCan.getInvoice());

        System.out.println("USA \n" + crUSA.getInvoice());



//        System.out.println("FATURA: ");
//        System.out.println("Pagamento básico: " +  String.format("%.2f", cr.getInvoice().getBasicPayment()));
//        System.out.println("Imposto: " + String.format("%.2f",  cr.getInvoice().getTax()));
//        System.out.println("Pagamento total: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
//
//
//        System.out.println("FATURA CANADA: ");
//        System.out.println("Pagamento básico: " +  String.format("%.2f", crCan.getInvoice().getBasicPayment()));
//        System.out.println("Imposto: " + String.format("%.2f",  crCan.getInvoice().getTax()));
//        System.out.println("Pagamento total: " + String.format("%.2f", crCan.getInvoice().getTotalPayment()));
//
//
//        System.out.println("FATURA USA:");
//        System.out.println("Pagamento básico: " +  String.format("%.2f", crUSA.getInvoice().getBasicPayment()));
//        System.out.println("Imposto: " + String.format("%.2f",  crUSA.getInvoice().getTax()));
//        System.out.println("Pagamento total: " + String.format("%.2f", crUSA.getInvoice().getTotalPayment()));




        sc.close();

    }
}
