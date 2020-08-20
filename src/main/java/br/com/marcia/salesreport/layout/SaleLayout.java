package br.com.marcia.salesreport.layout;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class SaleLayout extends RegisterLayout {

    private String saleId;

    private String salesmanName;

    private List<SaleItemLayout> items;

    @Data
    public static class SaleItemLayout {
        private String code;
        private String quantity;
        private String price;
    }
}

