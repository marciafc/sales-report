package br.com.marcia.salesreport.layout;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerLayout extends RegisterLayout {

    private String cnpj;

    private String name;

    private String businessArea;

}
