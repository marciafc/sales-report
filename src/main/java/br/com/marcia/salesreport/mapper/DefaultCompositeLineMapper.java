package br.com.marcia.salesreport.mapper;

import br.com.marcia.salesreport.layout.RegisterLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DefaultCompositeLineMapper extends PatternMatchingCompositeLineMapper<RegisterLayout> {

    private final String DELIMITER = "ç";

    public DefaultCompositeLineMapper() {

        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put("00*", new DelimitedLineTokenizer(DELIMITER));
        this.setTokenizers(tokenizers);

        Map<String, FieldSetMapper<RegisterLayout>> fieldSetMappers = new HashMap<>();
        fieldSetMappers.put("001*", new SalesmanFieldSetMapper());
        fieldSetMappers.put("002*", new CustomerFieldSetMapper());
        fieldSetMappers.put("003*", new SaleFieldSetMapper());

        this.setFieldSetMappers(fieldSetMappers);
    }
}