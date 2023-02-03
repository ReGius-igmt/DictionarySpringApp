package ru.regiuss.practice.dictionary.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.regiuss.practice.dictionary.Dictionary;
import ru.regiuss.practice.dictionary.server.repository.DictionaryRepository;
import ru.regiuss.practice.dictionary.server.repository.FileRepository;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;
    private final FileRepository fileRepository;

    public String search(int id, String key) {
        return dictionaryRepository.getByID(id-1).search(key);
    }

    public Map<String, String> getValues(int id) {
        return dictionaryRepository.getByID(id-1).getValues();
    }

    public void add(int id, String key, String value) {
        dictionaryRepository.getByID(id-1).add(key, value);
    }

    public synchronized Map<String, String> read(int dictionaryId, int fileId) {
        Dictionary dictionary = dictionaryRepository.getByID(dictionaryId-1);
        File file = fileRepository.getFileByID(fileId-1);
        try {
            dictionary.read(file);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("read dictionary error");
        }
        return dictionary.getValues();
    }

    public boolean remove(int id, String key) {
        return dictionaryRepository.getByID(id-1).remove(key);
    }

    public synchronized String[] getFiles() {
        return fileRepository.getFiles();
    }

    public String[] getAll() {
        return Arrays.stream(dictionaryRepository.getAll()).map(d -> d.getClass().getSimpleName()).toArray(String[]::new);
    }
}