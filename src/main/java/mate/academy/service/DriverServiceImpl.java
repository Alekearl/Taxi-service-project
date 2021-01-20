package mate.academy.service;

import java.util.List;
import mate.academy.dao.DriverDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Driver;

@Service
public class DriverServiceImpl implements DriverService {

    @Inject
    private DriverDao genericDao;

    @Override
    public Driver create(Driver driver) {
        return (Driver) genericDao.create(driver);
    }

    @Override
    public Driver get(Long id) {
        return (Driver) genericDao.get(id).get();
    }

    @Override
    public List<Driver> getAll() {
        return genericDao.getAll();
    }

    @Override
    public Driver update(Driver driver) {
        return (Driver) genericDao.update(driver);
    }

    @Override
    public boolean delete(Long id) {
        return genericDao.delete(id);
    }
}
