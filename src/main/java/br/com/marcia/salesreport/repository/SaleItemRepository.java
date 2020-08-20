package br.com.marcia.salesreport.repository;

import br.com.marcia.salesreport.entity.SaleItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItemEntity, Long> {

}
