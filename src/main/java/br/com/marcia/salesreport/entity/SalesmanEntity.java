package br.com.marcia.salesreport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;

@Entity(name = "salesman")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesmanEntity extends RegisterEntity {

    private String cpf;

    private String name;

    private Float salary;

}
