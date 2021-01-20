package mate.academy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.dao.CarDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Car;
import mate.academy.model.Driver;
import mate.academy.model.Manufacturer;
import mate.academy.util.ConnectionUtil;

@Dao
public class CarDaoImplJdbc implements CarDao {
    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars (model, manufacturer_id) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, car.getModel());
            statement.setLong(2, car.getManufacturer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getLong(1));
            }
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't create car "
                    + car, e);
        }
    }

    @Override
    public Optional<Car> get(Long id) {
        String query = "SELECT c.id, c.model, c.manufacturer_id, "
                + "m.name, m.country FROM cars c "
                + "JOIN manufacturers m ON c.manufacturer_id = "
                + "m.id WHERE c.id = ? AND c.deleted = FALSE";
        Car car = null;
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(parseDataFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't get car by ID "
                    + id, e);
        }
        if (car != null) {
            car.setDrivers(getCarDrivers(car.getId()));
        }
        return Optional.ofNullable(car);
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT c.id, c.model, c.manufacturer_id, m.name, "
                + "m.country FROM cars c "
                + "JOIN manufacturers m ON c.manufacturer_id = m.id "
                + "WHERE c.deleted = FALSE";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cars.add(parseDataFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't get all cars "
                    + cars, e);
        }
        for (Car car : cars) {
            car.setDrivers(getCarDrivers(car.getId()));
        }
        return cars;
    }

    @Override
    public Car update(Car car) {
        String query = "UPDATE cars SET model = ?, "
                + "manufacturer_id = ? WHERE id = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setString(1, car.getModel());
            statement.setLong(2, car.getManufacturer().getId());
            statement.setLong(3, car.getId());
            statement.executeUpdate();
            statement.close();
            removeDrivers(car, connection);
            insertDrivers(car, connection);
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't update car " + car, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE cars SET deleted = TRUE "
                + "WHERE id = ?;";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't delete car by ID "
                    + id, e);
        }
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String selectQuery = "SELECT c.id, c.model, c.manufacturer_id, "
                + "m.name, m.country "
                + "FROM cars c "
                + "JOIN cars_drivers cd ON c.id = cd.car_id "
                + "JOIN manufacturers m ON c.manufacturer_id = m.id "
                + "JOIN drivers d ON d.id = cd.driver_id "
                + "WHERE cd.driver_id = ? "
                + "AND c.deleted = FALSE AND d.deleted = FALSE";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(selectQuery)) {
            statement.setLong(1, driverId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cars.add(parseDataFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't get car drivers by ID "
                    + driverId, e);
        }
        for (Car car : cars) {
            car.setDrivers(getCarDrivers(car.getId()));
        }
        return cars;
    }

    private Car parseDataFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        String model = resultSet.getString("model");
        Long manufacturerId = resultSet.getObject("manufacturer_id", Long.class);
        String name = resultSet.getString("name");
        String country = resultSet.getString("country");
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturer.setId(manufacturerId);
        Car car = new Car(model, manufacturer);
        car.setId(id);
        return car;
    }

    private List<Driver> getCarDrivers(Long id) {
        String query = "SELECT d.name, d.license_number, "
                + "cd.driver_id FROM cars_drivers cd "
                + "JOIN drivers d ON cd.driver_id = d.id "
                + "WHERE cd.car_id = ? AND d.deleted = FALSE";
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String licenceNumber = resultSet.getString("license_number");
                Long driverId = resultSet.getObject("driver_id", Long.class);
                Driver driver = new Driver(name, licenceNumber);
                driver.setId(driverId);
                drivers.add(driver);
            }
            return drivers;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't get drivers from car "
                    + id, e);
        }
    }

    private void insertDrivers(Car car, Connection connection) {
        String query = "INSERT INTO cars_drivers (car_id, driver_id) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (Driver driver : car.getDrivers()) {
                statement.setLong(1, car.getId());
                statement.setLong(2, driver.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't insert driver "
                    + car, e);
        }
    }

    private void removeDrivers(Car car, Connection connection) {
        String query = "DELETE FROM cars_drivers WHERE car_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, car.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't delete driver "
                    + car, e);
        }
    }
}
