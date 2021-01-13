package mate.academy.storage;

import java.util.ArrayList;
import java.util.List;
import mate.academy.model.Car;
import mate.academy.model.Driver;
import mate.academy.model.Manufacturer;

public class Storage {
    public static final List<Manufacturer> manufacturers = new ArrayList<>();
    public static final List<Driver> drivers = new ArrayList<>();
    public static final List<Car> cars = new ArrayList<>();
    private static long manufactureId = 0L;
    private static long driverId = 0L;
    private static long carId = 0L;

    public static Manufacturer addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufactureId);
        manufacturers.add(manufacturer);
        return manufacturer;
    }

    public static Driver addDriver(Driver driver) {
        driver.setId(++driverId);
        drivers.add(driver);
        return driver;
    }

    public static Car addCar(Car car) {
        car.setId(++carId);
        cars.add(car);
        return car;
    }
}
