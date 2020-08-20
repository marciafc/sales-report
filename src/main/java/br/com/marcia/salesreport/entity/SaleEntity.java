package br.com.marcia.salesreport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "sale")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleEntity extends RegisterEntity {

    @Column(name = "sale_id")
    private Integer saleId;

    @Column(name = "salesman_name")
    private String salesmanName;

    private Double total;

    @OneToMany(mappedBy = "sale")
    private List<SaleItemEntity> items = new ArrayList<>();

}
