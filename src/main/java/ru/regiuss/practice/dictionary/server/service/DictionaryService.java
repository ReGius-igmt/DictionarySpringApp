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
        final Dictionary dictionary = dictionaryRepository.getByID(id);
        synchronized (dictionary) {
            return dictionary.search(key);
        }
    }

    public Map<String, String> getValues(int id) {
        final Dictionary dictionary = dictionaryRepository.getByID(id);
        synchronized (dictionary) {
            return dictionary.getValues();
        }
    }

    public void add(int id, String key, String value) {
        final Dictionary dictionary = dictionaryRepository.getByID(id);
        synchronized (dictionary) {
            dictionary.add(key, value);
        }
    }

    public Map<String, String> read(int dictionaryId, int fileId) {
        Dictionary dictionary = dictionaryRepository.getByID(dictionaryId);
        File file = fileRepository.getFileByID(fileId);
        synchronized (dictionary) {
            try {
                dictionary.read(file);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("read dictionary error");
            }
            return dictionary.getValues();
        }
    }

    public boolean remove(int id, String key) {
        final Dictionary dictionary = dictionaryRepository.getByID(id);
        synchronized (dictionary) {
            return dictionary.remove(key);
        }
    }

    public String[] getFiles() {
        return fileRepository.getFiles();
    }

    public String[] getAll() {
        return Arrays.stream(dictionaryRepository.getAll()).map(d -> d.getClass().getSimpleName()).toArray(String[]::new);
    }
}