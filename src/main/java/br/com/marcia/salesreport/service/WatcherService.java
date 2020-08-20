package br.com.marcia.salesreport.service;

import br.com.marcia.salesreport.job.ExecutorJob;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.*;
import java.util.List;

@Service
@Slf4j
public class WatcherService {

    private final String FILE_EXTENSION = ".dat";

    @Value("${file.data.path.in}")
    private String fileInputPath;

    @Autowired
    private ExecutorJob executorJob;

    @SneakyThrows
    public void watch() {

        log.info("Iniciou WatcherService. Aguardando arquivos para processar...");

        Path path = Paths.get(fileInputPath);
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        WatchKey key;

        while ((key = watchService.take()) != null) {

            List<WatchEvent<?>> events = key.pollEvents();
            String fileName = events.get(0).context().toString();
            if(fileName.endsWith(FILE_EXTENSION)) {
                executorJob.execute();
                log.info("Finalizou execução. Aguardando novos arquivos para processar...");
                key.reset();

            }
        }

    }
}
