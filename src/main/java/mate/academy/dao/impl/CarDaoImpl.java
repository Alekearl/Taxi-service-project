package mate.academy.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import mate.academy.dao.CarDao;
import mate.academy.lib.Dao;
import mate.academy.model.Car;
import mate.academy.model.Driver;
import mate.academy.storage.Storage;

@Dao
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        return Storage.addCar(car);
    }

    @Override
    public Optional<Car> get(Long id) {
        for (Car car : Storage.cars) {
            if (car.getId().equals(id)) {
                return Optional.of(car);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Car> getAll() {
        return Storage.cars;
    }

    @Override
    public Car update(Car car) {
        for (int i = 0; i < Storage.cars.size(); i++) {
            if (Storage.cars.get(i).getId().equals(car.getId())) {
                Storage.cars.set(i, car);
                break;
            }
        }
        return car;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.cars.removeIf(c -> Objects.equals(c.getId(), id));
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        List<Car> carList = new ArrayList<>();
        for (Car car : Storage.cars) {
            for (Driver driver: car.getDrivers()) {
                if (driver.getId().equals(driverId)) {
                    carList.add(car);
                }
            }
        }
        return carList;
    }
}
