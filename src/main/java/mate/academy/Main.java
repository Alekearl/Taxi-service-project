package mate.academy;

import java.util.ArrayList;
import java.util.List;
import mate.academy.lib.Injector;
import mate.academy.model.Car;
import mate.academy.model.Driver;
import mate.academy.model.Manufacturer;
import mate.academy.service.CarService;
import mate.academy.service.DriverService;
import mate.academy.service.ManufacturerService;

public class Main {
    private static final Injector injector
            = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
        Driver taxiDriverOne = new Driver("Borys", "0202B");
        Driver taxiDriverTwo = new Driver("Hrysha", "0101A");
        driverService.create(taxiDriverOne);
        driverService.create(taxiDriverTwo);
        System.out.println(driverService.getAll());
        Driver taxiDriverThree = new Driver("Volodymyr", "0303C");
        driverService.create(taxiDriverThree);
        System.out.println(taxiDriverThree);
        taxiDriverThree.setLicenseNumber("0404D");
        driverService.update(taxiDriverThree);
        System.out.println(taxiDriverThree);
        System.out.println(driverService.get(1L));
        driverService.delete(2L);
        System.out.println(driverService.getAll());

        ManufacturerService manufacturerService
                = (ManufacturerService) injector.getInstance(ManufacturerService.class);
        Manufacturer volksWagenSedan = new Manufacturer("Volkswagen", "Germany");
        Manufacturer volksWagen = new Manufacturer("Volkswagen", "Germany");
        Manufacturer seat = new Manufacturer("Seat", "Spain");
        Manufacturer skoda = new Manufacturer("Skoda", "Czech Republic");
        manufacturerService.create(volksWagenSedan);
        manufacturerService.create(volksWagen);
        manufacturerService.create(seat);
        manufacturerService.create(skoda);
        System.out.println(manufacturerService.getAllManufactures());
        Manufacturer landRover = new Manufacturer("Discovery", "Germany");
        manufacturerService.create(landRover);
        landRover.setCountry("England");
        System.out.println(manufacturerService.getAllManufactures());
        manufacturerService.update(landRover);
        System.out.println(manufacturerService.getAllManufactures());
        manufacturerService.deleteById(3L);
        System.out.println(manufacturerService.getAllManufactures());
        System.out.println(manufacturerService.getById(4L));

        CarService carService = (CarService) injector.getInstance(CarService.class);
        Car carOne = new Car("Passat B8", volksWagenSedan);
        Car carTwo = new Car("Touareg 7P", volksWagen);
        Car carThree = new Car("Leon", seat);
        Car carFour = new Car("Superb", skoda);
        carService.create(carOne);
        carService.create(carTwo);
        carService.create(carThree);
        carService.create(carFour);
        System.out.println(carService.getAll());
        List<Driver> driverList = new ArrayList<>();
        driverList.add(taxiDriverOne);
        driverList.add(taxiDriverTwo);
        driverList.add(taxiDriverThree);
        carOne.setDrivers(driverList);
        carTwo.setDrivers(driverList);
        carThree.setDrivers(driverList);
        carFour.setDrivers(driverList);

        carService.addDriverToCar(taxiDriverOne, carOne);
        carService.addDriverToCar(taxiDriverTwo, carOne);
        carService.addDriverToCar(taxiDriverOne, carTwo);
        carService.addDriverToCar(taxiDriverThree, carThree);
        carService.addDriverToCar(taxiDriverOne, carFour);
        System.out.println(carService.getAllByDriver(1L));
        System.out.println(carService.get(1L));
    }
}
