package ru.regiuss.practice.dictionary.server.repository;

import org.springframework.stereotype.Repository;
import ru.regiuss.practice.dictionary.Dictionary;
import ru.regiuss.practice.dictionary.DigitDictionary;
import ru.regiuss.practice.dictionary.LatinDictionary;

@Repository
public class DictionaryRepository {

    private final Dictionary[] dictionaries;

    public DictionaryRepository() {
        this.dictionaries = new Dictionary[] {
                new DigitDictionary(),
                new LatinDictionary(),
        };
    }

    public synchronized Dictionary getByID(int id) {
        if(id < 1) throw new IllegalArgumentException("id cant been < 1");
        if(id > dictionaries.length)
            throw new IllegalArgumentException(String.format("dictionary not found by id [%s]", id));
        return dictionaries[id-1];
    }

    public synchronized Dictionary[] getAll() {
        return dictionaries;
    }
}
