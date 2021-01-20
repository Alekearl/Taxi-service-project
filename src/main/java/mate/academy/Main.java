package mate.academy;

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
        ManufacturerService manufacturerService
                = (ManufacturerService) injector.getInstance(ManufacturerService.class);
        Manufacturer manufacturer = new Manufacturer("Volvo", "China");
        manufacturerService.create(manufacturer);
        System.out.println(manufacturer);
        System.out.println(manufacturerService.getById(1L));
        manufacturer.setName("FORD");
        manufacturer.setCountry("USA");
        manufacturerService.update(manufacturer);
        System.out.println(manufacturerService.getById(1L));
        manufacturerService.delete(manufacturer);
        manufacturerService.deleteById(3L);
        System.out.println(manufacturerService.getAllManufactures());

        DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
        Driver driver = new Driver("Leonidas", "354541");
        driverService.create(driver);
        System.out.println(driver);
        driver.setLicenseNumber("3578");
        driverService.update(driver);

        CarService carService = (CarService) injector.getInstance(CarService.class);
        Car car = new Car("Volvo", manufacturer);
        carService.create(car);
        System.out.println(car);
        car.setModel("XC60");
        carService.update(car);
        System.out.println(car);
        System.out.println(carService.getAll());
        carService.delete(2L);
        System.out.println(carService.getAll());
        carService.addDriverToCar(driver, car);
        System.out.println(carService.getAll());
    }
}
