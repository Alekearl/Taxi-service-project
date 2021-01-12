package mate.academy.dao.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import mate.academy.dao.ManufacturerDao;
import mate.academy.lib.Dao;
import mate.academy.model.Manufacturer;
import mate.academy.storage.Storage;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return Storage.addManufacturer(manufacturer);
    }

    @Override
    public Optional<Manufacturer> getById(Long manufactureId) {
        for (Manufacturer num : Storage.manufacturers) {
            if (num.getId().equals(manufactureId)) {
                return Optional.of(num);
            }
        }
        return Optional.empty();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        for (int i = 0; i < Storage.manufacturers.size(); i++) {
            if (Storage.manufacturers.get(i).getId().equals(manufacturer.getId())) {
                Storage.manufacturers.set(i, manufacturer);
                break;
            }
        }
        return manufacturer;
    }

    @Override
    public boolean deleteById(Long manufactureId) {
        return Storage.manufacturers.removeIf(m -> Objects.equals(m.getId(), manufactureId));
    }

    @Override
    public boolean delete(Manufacturer manufacturer) {
        return deleteById(manufacturer.getId());
    }

    @Override
    public List<Manufacturer> getAllManufactures() {
        return Storage.manufacturers;
    }
}
