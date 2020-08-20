package br.com.marcia.salesreport.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@Slf4j
public class FileService {

    @Value("${file.data.path.out}")
    private String fileOutputPath;

    @Value("${file.data.name.out}")
    private String fileOutputName;

    @SneakyThrows
    public void write(String text) {

        String fileName = fileOutputPath + fileOutputName;

        log.debug("Abrindo arquivo para escrita " + fileName);

        try(FileOutputStream file = new FileOutputStream(fileName, true)) {
            file.write((text + "\n").getBytes());

        } catch(IOException e) {
            log.debug("Ocorreu erro de I/O no file " + fileName);
        }

    }

    @SneakyThrows
    public void clean() {

        log.debug("Excluindo os arquivos .dat da pasta " + fileOutputPath);

        try {
            Files.walk(Paths.get(fileOutputPath))
                    .filter(p -> p.getFileName().toString().endsWith(".dat"))
                    .forEach(f -> f.toFile().delete());

        } catch (IOException e) {
            log.debug("Ocorreu erro de I/O na exclusão dos aquivos .dat ");
        }
    }

}
