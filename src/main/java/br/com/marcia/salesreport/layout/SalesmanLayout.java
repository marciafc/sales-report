package br.com.marcia.salesreport.layout;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesmanLayout extends RegisterLayout {

    private String cpf;

    private String name;

    private String salary;
}
