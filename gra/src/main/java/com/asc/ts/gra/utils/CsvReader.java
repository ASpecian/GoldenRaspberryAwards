package com.asc.ts.gra.utils;

import com.asc.ts.gra.model.entity.AbstractEntity;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 * 
 * @param <E> Entity class
 */

public final class CsvReader<E extends AbstractEntity> {
    public static <E> List<E> load(String path, String fileName, Class<E> clazz) throws Exception {
        String _fileName = fileName.endsWith(".csv") ? fileName : fileName.concat(".csv");
        String fullPath = path.concat((!path.endsWith("/") && !_fileName.startsWith("/")) ? "/" : "").concat(_fileName);
        
        File file = new File(fullPath);

        if (!file.exists()) {
            throw new Exception(String.format("File [%s] not found!", fullPath));
        } else {
            System.out.println("> Read file from " + file.getAbsolutePath());
        }
        
        Reader reader = new BufferedReader(new FileReader(file));
        
        CsvToBean<E> csvReader = new CsvToBeanBuilder(reader)
                .withType(clazz)
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
        
        List<E> results = csvReader.parse();
        
        System.out.println("  > Total registers: " + results.size());
        
        return results;
    }
}
