package br.com.marcia.salesreport.reader;

import br.com.marcia.salesreport.layout.RegisterLayout;
import br.com.marcia.salesreport.mapper.DefaultCompositeLineMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SalesFileReader extends FlatFileItemReader<RegisterLayout> {

    public SalesFileReader() {
        setName("salesFileReader");
        setResource(new FileSystemResource("./data/in/data.dat"));
        setLineMapper(new DefaultCompositeLineMapper());
        setSkippedLinesCallback(log::info);
    }
}

