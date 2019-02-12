package com.docker.apiserver;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TodoController {

    private static List<Todo> todos = readTodos();

    private static List<Todo> readTodos() {
        JSONParser jsonParser = new JSONParser();
        JSONObject parsedJson = null;
        try {
            parsedJson = (JSONObject) jsonParser.parse(new FileReader("db.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        List<HashMap<String, String>> todos = (List<HashMap<String, String>>) parsedJson.get("elements");
        ArrayList<Todo> arrayList = new ArrayList();
        for (HashMap<String, String> todo : todos) {
            arrayList.add(new Todo(todo.get("title")));
        }
        return arrayList;
    }

    @GetMapping("/todos")
    public List<Todo> getTodos() throws ParseException, IOException {
        return todos;
    }

    @PostMapping("/todos")
    public void addTodo(@RequestBody Map<String,String> todo){
        todos.add(new Todo(todo.get("title")));
        saveTodo();
        return;
    }

    private void saveTodo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("elements",todos);
        try {
            FileWriter fileWriter = new FileWriter("db.json");
            jsonObject.writeJSONString(fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
