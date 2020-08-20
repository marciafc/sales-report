package br.com.marcia.salesreport;

import br.com.marcia.salesreport.service.WatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.marcia.salesreport")
public class SalesReportApp implements CommandLineRunner {

    @Autowired
    private WatcherService watcher;

    public static void main(String[] args) {
        SpringApplication.run(SalesReportApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        watcher.watch();
    }

}
