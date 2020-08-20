package br.com.marcia.salesreport.mapper;

import br.com.marcia.salesreport.layout.CustomerLayout;
import br.com.marcia.salesreport.layout.RegisterLayout;
import br.com.marcia.salesreport.eumeration.RegisterTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

@Slf4j
public class CustomerFieldSetMapper implements FieldSetMapper<RegisterLayout> {

    @Override
    public RegisterLayout mapFieldSet(FieldSet fieldSet) {

        RegisterLayout customerLayout = CustomerLayout.builder()
                .cnpj(fieldSet.readString(1))
                .name(fieldSet.readString(2))
                .businessArea(fieldSet.readString(3))
                .build();
        customerLayout.setRegisterType(RegisterTypeEnum.CUSTOMER);

        return customerLayout;
    }

}