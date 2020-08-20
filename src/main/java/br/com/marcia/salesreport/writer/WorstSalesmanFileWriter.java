package br.com.marcia.salesreport.writer;

import br.com.marcia.salesreport.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WorstSalesmanFileWriter implements ItemWriter<String> {

    @Autowired
    private FileService fileService;

    @Override
    public void write(List<? extends String> result) {

        fileService.write("O pior vendedor: " + result.get(0));

    }


}
