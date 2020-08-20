package br.com.marcia.salesreport.mapper;

import br.com.marcia.salesreport.layout.RegisterLayout;
import br.com.marcia.salesreport.layout.SalesmanLayout;
import br.com.marcia.salesreport.eumeration.RegisterTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

@Slf4j
public class SalesmanFieldSetMapper implements FieldSetMapper<RegisterLayout> {

    @Override
    public RegisterLayout mapFieldSet(FieldSet fieldSet) {

        SalesmanLayout salesmanLayout = SalesmanLayout.builder()
                .cpf(fieldSet.readString(1))
                .name(fieldSet.readString(2))
                .salary(fieldSet.readString(3))
                .build();
        salesmanLayout.setRegisterType(RegisterTypeEnum.SALESMAN);

        return salesmanLayout;
    }
}
