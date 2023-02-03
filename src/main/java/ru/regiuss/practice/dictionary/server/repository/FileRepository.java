package ru.regiuss.practice.dictionary.server.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class FileRepository {
    @Value("${dictionariesPath:dictionaries}")
    private String dictionariesPath;

    public String[] getFiles() {
        File f = new File(dictionariesPath);
        if(f.exists()) return f.list();
        else throw new RuntimeException("source file not exist");
    }

    public File getFileByID(int id) {
        if(id < 0) throw new IllegalArgumentException("id cant been < 0");
        File f = new File(dictionariesPath);
        if(!f.exists()) throw new RuntimeException("source file not exist");
        String[] files = f.list();
        if(files == null || id > files.length - 1)
            throw new IllegalArgumentException(String.format("file not found by id [%s]", id));
        if(f.exists()) return new File(f, files[id]);
        else return null;
    }
}
