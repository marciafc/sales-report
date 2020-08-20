package br.com.marcia.salesreport.mapper;

import br.com.marcia.salesreport.eumeration.RegisterTypeEnum;
import br.com.marcia.salesreport.layout.RegisterLayout;
import br.com.marcia.salesreport.layout.SaleLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SaleFieldSetMapper implements FieldSetMapper<RegisterLayout> {

    private final static String DELIMITER = "-";

    private final static String REGEX_ITEMS = "\\d*-\\d*-\\d*\\.?\\d{0,2}";

    @Override
    public RegisterLayout mapFieldSet(FieldSet fieldSet) {

        List<SaleLayout.SaleItemLayout> items = new ArrayList<>();

        Pattern pattern = Pattern.compile(REGEX_ITEMS);
        Matcher matcher = pattern.matcher(fieldSet.readString(2));

        while (matcher.find()) {

            String itemInfo = matcher.group();

            String[] info = itemInfo.split(DELIMITER);

            SaleLayout.SaleItemLayout saleItemLayout = new SaleLayout.SaleItemLayout();
            saleItemLayout.setCode(info[0]);
            saleItemLayout.setQuantity(info[1]);
            saleItemLayout.setPrice(info[2]);
            items.add(saleItemLayout);

        }

        SaleLayout salesmanLayout = SaleLayout.builder()
                .saleId(fieldSet.readString(1))
                .items(items)
                .salesmanName(fieldSet.readString(3))
                .build();
        salesmanLayout.setRegisterType(RegisterTypeEnum.SALE);

        return salesmanLayout;
    }

}