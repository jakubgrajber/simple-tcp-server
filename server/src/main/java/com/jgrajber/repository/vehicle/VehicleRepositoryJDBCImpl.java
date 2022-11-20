package com.jgrajber.repository.vehicle;

import com.jgrajber.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleRepositoryJDBCImpl implements VehicleRepository {

    private final DataSource dataSource;

    @Autowired
    public VehicleRepositoryJDBCImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Vehicle> getVehiclesByUserId(long userId) {

        List<Vehicle> vehicles = new ArrayList<>();

        String query = """
                SELECT v.id, v.brand, v.model, v.insert_time
                FROM vehicle AS v
                WHERE v.user_id = ?
                """;

        try (var connection = dataSource.getConnection();
             var ps = connection.prepareStatement(query)) {

            ps.setLong(1, userId);

            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    var vehicle = new Vehicle(
                            rs.getLong("id"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getTimestamp("insert_time")
                    );
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
}
