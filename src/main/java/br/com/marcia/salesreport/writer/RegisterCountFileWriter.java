package br.com.marcia.salesreport.writer;

import br.com.marcia.salesreport.eumeration.RegisterTypeEnum;
import br.com.marcia.salesreport.repository.projection.RegisterTypeCountProjection;
import br.com.marcia.salesreport.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RegisterCountFileWriter implements ItemWriter<List<RegisterTypeCountProjection>> {

    @Autowired
    private FileService fileService;

    @Override
    public void write(List<? extends List<RegisterTypeCountProjection>> result) {

        result.get(0).stream().forEach(reg -> {

            if(reg.getRegisterType() == RegisterTypeEnum.CUSTOMER) {
                fileService.write("Quantidade de clientes no arquivo de entrada: " + reg.getCount());

            } else if(reg.getRegisterType() == RegisterTypeEnum.SALESMAN) {
                fileService.write("Quantidade de vendedores no arquivo de entrada: " + reg.getCount());

            }
        });

    }

}

