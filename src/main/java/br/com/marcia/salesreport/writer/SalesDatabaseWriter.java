package br.com.marcia.salesreport.writer;

import br.com.marcia.salesreport.entity.RegisterEntity;
import br.com.marcia.salesreport.entity.SaleEntity;
import br.com.marcia.salesreport.eumeration.RegisterTypeEnum;
import br.com.marcia.salesreport.repository.RegisterRepository;
import br.com.marcia.salesreport.repository.SaleItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SalesDatabaseWriter implements ItemWriter<RegisterEntity> {

    private RegisterRepository registerRepository;

    private SaleItemRepository saleItemRepository;

    public SalesDatabaseWriter(RegisterRepository registerRepository,
                               SaleItemRepository saleItemRepository) {

        this.registerRepository = registerRepository;
        this.saleItemRepository = saleItemRepository;
    }

    @Override
    @Transactional
    public void write(List<? extends RegisterEntity> registerEntities) {

        log.info("Salvando a lista de registros em lotes de " + registerEntities.size());

        // Vendas
        saveSales(registerEntities);

        // Demais registros
        saveRegisters(registerEntities);

    }

    private void saveRegisters(List<? extends RegisterEntity> registerEntities) {

        List<? extends RegisterEntity> others = registerEntities.stream()
                .filter(register -> register.getRegisterType() != RegisterTypeEnum.SALE)
                .collect(Collectors.toList());

        registerRepository.saveAll(others);

    }

    private void saveSales(List<? extends RegisterEntity> registerEntities) {

        List<? extends RegisterEntity> sales = registerEntities.stream()
                .filter(register -> register.getRegisterType() == RegisterTypeEnum.SALE)
                .collect(Collectors.toList());

        registerRepository.saveAll(sales);

        sales.stream().forEach(sale -> {
            SaleEntity saleEntity = (SaleEntity) sale;
            saleEntity.getItems().stream().forEach(item -> {
                item.setSale(saleEntity);
                saleItemRepository.save(item);
            });
        });
    }


}
