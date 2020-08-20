package br.com.marcia.salesreport.processor;

import br.com.marcia.salesreport.entity.*;
import br.com.marcia.salesreport.layout.CustomerLayout;
import br.com.marcia.salesreport.layout.RegisterLayout;
import br.com.marcia.salesreport.layout.SaleLayout;
import br.com.marcia.salesreport.layout.SalesmanLayout;
import br.com.marcia.salesreport.eumeration.RegisterTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SalesFileProcessor implements ItemProcessor<RegisterLayout, RegisterEntity> {

    @Override
    public RegisterEntity process(RegisterLayout registerLayout) {

        if(registerLayout.getRegisterType() == RegisterTypeEnum.SALESMAN) {
            return createSalesman(registerLayout);
        }

        if(registerLayout.getRegisterType() == RegisterTypeEnum.CUSTOMER) {
            return createCustomer(registerLayout);
        }

        if(registerLayout.getRegisterType() == RegisterTypeEnum.SALE) {
            return createSale(registerLayout);
        }

        return null;
    }

    private RegisterEntity createSale(RegisterLayout registerLayout) {

        List<SaleItemEntity> itemEntityList = new ArrayList<>();
        final Double[] total = {0d};

        // Venda
        SaleLayout saleLayout = (SaleLayout) registerLayout;
        SaleEntity saleEntity = SaleEntity.builder()
                .saleId(Integer.parseInt(saleLayout.getSaleId()))
                .salesmanName(saleLayout.getSalesmanName())
                .build();
        saleEntity.setRegisterType(RegisterTypeEnum.SALE);

        // Items da venda
        saleLayout.getItems().stream().forEach(i -> {

            Integer quantity = Integer.parseInt(i.getQuantity());
            Float price = Float.parseFloat(i.getPrice());
            total[0] += quantity * price;

            itemEntityList.add(SaleItemEntity.builder()
                    .code(Long.parseLong(i.getCode()))
                    .quantity(quantity)
                    .price(price)
                    .build());
        });

        saleEntity.setItems(itemEntityList);
        saleEntity.setTotal(total[0]);

        return saleEntity;
    }

    private RegisterEntity createSalesman(RegisterLayout registerLayout) {
        SalesmanLayout salesmanLayout = (SalesmanLayout) registerLayout;
        SalesmanEntity salesmanEntity = SalesmanEntity.builder()
                .cpf(salesmanLayout.getCpf())
                .name(salesmanLayout.getName())
                .salary(Float.parseFloat(salesmanLayout.getSalary()))
                .build();
        salesmanEntity.setRegisterType(RegisterTypeEnum.SALESMAN);

        return salesmanEntity;
    }

    private CustomerEntity createCustomer(RegisterLayout registerLayout) {
        CustomerLayout customerLayout = (CustomerLayout) registerLayout;
        CustomerEntity customerEntity = CustomerEntity.builder()
                .cnpj(customerLayout.getCnpj())
                .name(customerLayout.getName())
                .businessArea(customerLayout.getBusinessArea())
                .build();
        customerEntity.setRegisterType(RegisterTypeEnum.CUSTOMER);

        return customerEntity;
    }
}

