package br.com.marcia.salesreport.repository;

import br.com.marcia.salesreport.entity.*;
import br.com.marcia.salesreport.eumeration.RegisterTypeEnum;
import br.com.marcia.salesreport.layout.CustomerLayout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RegisterRepositoryTest {

    @Autowired
    private RegisterRepository transactionRepository;

    @Test
    public void testSaveAllRegisters() {

        List<RegisterEntity> registerToSave = new ArrayList<>();

        CustomerEntity customerEntity1 = CustomerEntity.builder()
                .cnpj("1234567890")
                .name("João")
                .businessArea("Rural")
                .build();
        customerEntity1.setRegisterType(RegisterTypeEnum.CUSTOMER);
        registerToSave.add(customerEntity1);

        CustomerEntity customerEntity2 = CustomerEntity.builder()
                .cnpj("234567890")
                .name("Maria")
                .businessArea("Rural")
                .build();
        customerEntity2.setRegisterType(RegisterTypeEnum.CUSTOMER);
        registerToSave.add(customerEntity2);

        SaleEntity saleEntity1 = SaleEntity.builder()
                .saleId(10)
                .salesmanName("Mariano")
                .build();
        saleEntity1.setRegisterType(RegisterTypeEnum.SALE);
        List<SaleItemEntity> itemEntityList1 = new ArrayList<>();
        SaleItemEntity item1 = SaleItemEntity.builder()
                .code(1L)
                .quantity(100)
                .price(10f)
                .build();
        itemEntityList1.add(item1);
        saleEntity1.setItems(itemEntityList1);
        saleEntity1.setTotal(1000d);
        registerToSave.add(saleEntity1);

        SalesmanEntity salesmanEntity = SalesmanEntity.builder()
                .cpf("9999999999999")
                .name("Mariano")
                .salary(1000f)
                .build();
        salesmanEntity.setRegisterType(RegisterTypeEnum.SALESMAN);
        registerToSave.add(salesmanEntity);

        List<RegisterEntity> registerEntities = transactionRepository.saveAll(registerToSave);

        assertThat(registerEntities).isEqualTo(registerToSave);
    }


}
