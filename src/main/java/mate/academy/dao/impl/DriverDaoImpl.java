package mate.academy.dao.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import mate.academy.dao.DriverDao;
import mate.academy.model.Driver;
import mate.academy.storage.Storage;

public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        return Storage.addDriver(driver);
    }

    @Override
    public Optional<Driver> get(Long id) {
        for (Driver driver : Storage.drivers) {
            if (driver.getId().equals(id)) {
                return Optional.of(driver);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Driver> getAll() {
        return Storage.drivers;
    }

    @Override
    public Driver update(Driver driver) {
        for (int i = 0; i < Storage.drivers.size(); i++) {
            if (Storage.drivers.get(i).getId().equals(driver.getId())) {
                Storage.drivers.set(i, driver);
                break;
            }
        }
        return driver;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.drivers.removeIf(d -> Objects.equals(d.getId(), id));
    }
}
