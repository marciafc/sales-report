package br.com.marcia.salesreport.repository;

import br.com.marcia.salesreport.entity.RegisterEntity;
import br.com.marcia.salesreport.repository.projection.RegisterTypeCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, Long> {

    @Query("SELECT r.registerType as registerType, count(r.id) as count " +
            "FROM br.com.marcia.salesreport.entity.RegisterEntity r " +
            "GROUP BY r.registerType ")
    List<RegisterTypeCountProjection> countByRegisterType();

    @Query("SELECT max(s.saleId) " +
            "FROM br.com.marcia.salesreport.entity.SaleEntity s " +
            "WHERE s.total = " +
                " (SELECT max(s2.total) FROM br.com.marcia.salesreport.entity.SaleEntity s2) ")
    Long findIdMostExpensiveSale();

    @Query(value = "SELECT s.salesman_name " +
            "FROM ( " +
               "SELECT salesman_name, sum(total) as total " +
                "FROM sale " +
                "GROUP BY salesman_name " +
                "ORDER BY total asc " +
            "LIMIT 1) as s ", nativeQuery = true)
    String findWorstSalerman();

}
