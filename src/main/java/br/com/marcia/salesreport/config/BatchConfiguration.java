package br.com.marcia.salesreport.config;

import br.com.marcia.salesreport.entity.RegisterEntity;
import br.com.marcia.salesreport.layout.RegisterLayout;
import br.com.marcia.salesreport.processor.SalesFileProcessor;
import br.com.marcia.salesreport.reader.SalesDatabaseReader;
import br.com.marcia.salesreport.reader.SalesFileReader;
import br.com.marcia.salesreport.repository.RegisterRepository;
import br.com.marcia.salesreport.repository.SaleItemRepository;
import br.com.marcia.salesreport.repository.projection.RegisterTypeCountProjection;
import br.com.marcia.salesreport.service.FileService;
import br.com.marcia.salesreport.writer.MostExpensiveSaleIdFileWriter;
import br.com.marcia.salesreport.writer.SalesDatabaseWriter;
import br.com.marcia.salesreport.writer.RegisterCountFileWriter;
import br.com.marcia.salesreport.writer.WorstSalesmanFileWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfiguration {

    private static final int CHUNK_SIZE_MIN = 1;

    private static final int CHUNK_SIZE_MAX = 10;

    @Value("${file.data.path.in}")
    private String fileInputPath;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SalesFileReader salesFileReader;

    @Autowired
    private SalesFileProcessor salesFileProcessor;

    @Autowired
    private SalesDatabaseWriter salesDatabaseWriter;

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private RegisterCountFileWriter registerCountFileWriter;

    @Autowired
    private MostExpensiveSaleIdFileWriter mostExpensiveSaleIdFileWriter;

    @Autowired
    private WorstSalesmanFileWriter worstSalesmanFileWriter;

    @Autowired
    private FileService fileService;

    @Bean
    public Job salesSynchronizationJob(@Autowired JobBuilderFactory jobBuilderFactory) {

        fileService.clean();

        return jobBuilderFactory.get("salesSynchronizationJob")
                .start(salesSynchronizationStep1())
                .next(getCountClientAndSalermanStep2())
                .next(findIdMostExpensiveSaleStep3())
                .next(findWorstSalesmanStep4())
                .build();
    }

    @Bean
    public Step salesSynchronizationStep1() {

        log.debug("Começando o step 1");

        return stepBuilderFactory.get("salesSynchronizationStep1")
                .<RegisterLayout, RegisterEntity> chunk(CHUNK_SIZE_MAX)
                .reader(salesFileReader)
                .processor(salesFileProcessor)
                .writer(salesDatabaseWriter)
                .build();
    }

    @Bean
    public Step getCountClientAndSalermanStep2() {

        log.debug("Começando o step 2");

        return stepBuilderFactory
                .get("getCountClientAndSalermanStep2")
                .<Long, List<RegisterTypeCountProjection>> chunk(CHUNK_SIZE_MIN)
                .reader(new SalesDatabaseReader(
                        registerRepository,
                        "countByRegisterType",
                        null))
                .writer(registerCountFileWriter)
                .build();
    }

    @Bean
    public Step findIdMostExpensiveSaleStep3() {

        log.debug("Começando o step 3");

        return stepBuilderFactory
                .get("findIdMostExpensiveSaleStep3")
                .<Long, Long> chunk(CHUNK_SIZE_MIN)
                .reader(new SalesDatabaseReader(
                        registerRepository,
                        "findIdMostExpensiveSale",
                        null))
                .writer(mostExpensiveSaleIdFileWriter)
                .build();
    }

    @Bean
    public Step findWorstSalesmanStep4() {

        log.debug("Começando o step 4");

        return stepBuilderFactory
                .get("findWorstSalesmanStep4")
                .<Long, String> chunk(CHUNK_SIZE_MIN)
                .reader(new SalesDatabaseReader(
                        registerRepository,
                        "findWorstSalerman",
                        null))
                .writer(worstSalesmanFileWriter)
                .build();
    }

}