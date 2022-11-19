package com.jgrajber.repository.offer;

import com.jgrajber.model.InsuranceOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InsuranceOfferRepositoryJDBCImpl implements InsuranceOfferRepository {

    private final DataSource dataSource;

    @Autowired
    public InsuranceOfferRepositoryJDBCImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<InsuranceOffer> getOffersByVehicleId(long vehicleId) {

        List<InsuranceOffer> offers = new ArrayList<>();

        String query = """
                SELECT io.id, io.insurer, io.price, io.insert_time
                FROM insurance_offer AS io
                WHERE io.vehicle_id = ?
                """;

        try (var connection = dataSource.getConnection();
             var ps = connection.prepareStatement(query)) {

            ps.setLong(1, vehicleId);

            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    var offer = new InsuranceOffer(
                            rs.getLong("id"),
                            rs.getString("insurer"),
                            rs.getBigDecimal("price"),
                            rs.getTimestamp("insert_time")
                    );
                    offers.add(offer);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return offers;
    }
}
