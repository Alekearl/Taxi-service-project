package mate.academy.service;

import java.util.List;
import mate.academy.dao.DriverDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Driver;

@Service
public class DriverServiceImpl implements DriverService {

    @Inject
    DriverDao driverDao;

    @Override
    public Driver create(Driver driver) {
        return driverDao.create(driver);
    }

    @Override
    public Driver get(Long id) {
        return driverDao.get(id).get();
    }

    @Override
    public List<Driver> getAll() {
        return driverDao.getAll();
    }

    @Override
    public Driver update(Driver driver) {
        return driverDao.update(driver);
    }

    @Override
    public boolean delete(Long id) {
        return driverDao.delete(id);
    }
}
