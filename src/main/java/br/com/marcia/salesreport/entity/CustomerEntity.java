package br.com.marcia.salesreport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity extends RegisterEntity {

    private String cnpj;

    private String name;

    @Column(name = "business_area")
    private String businessArea;

}
