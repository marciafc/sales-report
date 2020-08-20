package br.com.marcia.salesreport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "sale_item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long code;

    private Integer quantity;

    private Float price;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "sale_id")
    private SaleEntity sale;

}
