package ru.dilarionov.springtest.controller;

import ru.dilarionov.springtest.history.domain.History;
import ru.dilarionov.springtest.history.repos.HistoryRepo;
import ru.dilarionov.springtest.user.domain.Message;
import ru.dilarionov.springtest.user.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private HistoryRepo historyRepo;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/index")
    public String index(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "index";
    }

    @PostMapping("index")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepo.save(message);

        History history = new History("Нажата кнопка Добавить", new Date().toString());
        historyRepo.save(history);

        Iterable<History> histories = historyRepo.findAll();
        model.put("history", histories);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "index";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String tag, Map<String, Object> model){
        Iterable<Message> messages;
        History history = new History("Нажата кнопка Фильтр по тегу", new Date().toString());
        historyRepo.save(history);

        if (tag != null && !tag.isEmpty()) {
            messages = messageRepo.findByTag(tag);
            history = new History("фильтрация по тегу " + tag, new Date().toString());
            historyRepo.save(history);
        } else {
            history = new History("фильтрация по пустому тегу", new Date().toString());
            messages = messageRepo.findAll();
            historyRepo.save(history);
        }
        Iterable<History> histories = historyRepo.findAll();
        model.put("history", histories);

        model.put("messages", messages);
        return "index";
    }

    @PostMapping("delete")
    public String delete(@RequestParam String id, Map<String, Object> model){
        Iterable<Message> messages;

        History history = new History("Нажата кнопка Удалить", new Date().toString());
        historyRepo.save(history);

        if (id != null && !id.isEmpty()) {
            Long idNum = Long.parseLong(id);
            Message message = messageRepo.findById(idNum).orElse(null);
            history = new History("Попытка удалить сообщение с id " + id,new Date().toString());
            historyRepo.save(history);
            if (message != null) {
                history = new History("Сообщение найдено, удаление", new Date().toString());
                historyRepo.save(history);
                messageRepo.delete(message);
            } else {
                history = new History("Сообщение не найдено", new Date().toString());
                historyRepo.save(history);
            }
            messages = messageRepo.findAll();
        } else {
            history = new History("Пустой id для удаления", new Date().toString());
            historyRepo.save(history);
            messages = messageRepo.findAll();
        }

        Iterable<History> histories = historyRepo.findAll();
        model.put("history", histories);

        model.put("messages", messages);
        return "index";
    }


}