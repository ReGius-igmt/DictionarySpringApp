package ru.regiuss.practice.dictionary.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.regiuss.practice.dictionary.server.dto.ResponseDto;
import ru.regiuss.practice.dictionary.server.service.DictionaryService;
import ru.regiuss.practice.dictionary.server.util.Utils;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final DictionaryService dictionaryService;

    @GetMapping("files")
    public ResponseDto<Map<Integer, String> > getFiles() {
        String[] files = dictionaryService.getFiles();
        Map<Integer, String> data = Utils.arrayToMap(files);
        return new ResponseDto<>(data);
    }

    @GetMapping("dictionaries")
    public ResponseDto<Map<Integer, String>> getDictionaries() {
        String[] dictionaries = dictionaryService.getAll();
        Map<Integer, String> data = Utils.arrayToMap(dictionaries);
        return new ResponseDto<>(data);
    }

    @GetMapping("dictionaries/{dictionaryId}/read")
    public ResponseDto<Map<String, String>> read(@PathVariable int dictionaryId, @RequestParam int fileId) {
        Map<String, String> data = dictionaryService.read(dictionaryId, fileId);
        return new ResponseDto<>(data);
    }

    @GetMapping("dictionaries/{dictionaryId}")
    public ResponseDto<Map<String, String>> getValues(@PathVariable int dictionaryId) {
        Map<String, String> data = dictionaryService.getValues(dictionaryId);
        return new ResponseDto<>(data);
    }

    @GetMapping("dictionaries/{dictionaryId}/{key}")
    public ResponseDto<String> search(@PathVariable int dictionaryId, @PathVariable String key) {
        String value = dictionaryService.search(dictionaryId, key);
        return new ResponseDto<>(value);
    }

    @DeleteMapping("dictionaries/{dictionaryId}")
    public ResponseDto<String> remove(@PathVariable int dictionaryId, @RequestParam String key) {
        boolean value = dictionaryService.remove(dictionaryId, key);
        return new ResponseDto<>("Запись удалена", value);
    }

    @PostMapping("dictionaries/{dictionaryId}")
    public ResponseDto<String> add(@PathVariable int dictionaryId, @RequestParam String key, @RequestParam String value) {
        dictionaryService.add(dictionaryId, key, value);
        return new ResponseDto<>("Запись добавлена");
    }
}
