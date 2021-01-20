package mate.academy.service;

import java.util.List;
import mate.academy.dao.ManufacturerDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Manufacturer;

@Service
public class ManufacturerServiceImpl implements GenericService<Manufacturer, Long> {

    @Inject
    private ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return (Manufacturer) manufacturerDao.create(manufacturer);
    }

    @Override
    public Manufacturer get(Long manufactureId) {
        return (Manufacturer) manufacturerDao.get(manufactureId).get();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return (Manufacturer) manufacturerDao.update(manufacturer);
    }

    @Override
    public boolean delete(Long manufactureId) {
        return manufacturerDao.delete(manufactureId);
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerDao.getAll();
    }
}
