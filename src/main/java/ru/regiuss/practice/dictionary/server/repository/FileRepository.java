package ru.regiuss.practice.dictionary.server.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class FileRepository {
    @Value("${dictionariesPath:dictionaries}")
    private String dictionariesPath;

    public synchronized String[] getFiles() {
        File f = new File(dictionariesPath);
        if(f.exists()) return f.list();
        else throw new RuntimeException("source file not exist");
    }

    public synchronized File getFileByID(int id) {
        if(id < 1) throw new IllegalArgumentException("id cant been < 1");
        File f = new File(dictionariesPath);
        if(!f.exists()) throw new RuntimeException("source file not exist");
        String[] files = f.list();
        if(files == null || id > files.length)
            throw new IllegalArgumentException(String.format("file not found by id [%s]", id));
        if(f.exists()) return new File(f, files[id-1]);
        else return null;
    }
}
