package com.example.demo.controller;

import com.example.demo.model.FieldModel;
import com.example.demo.service.FieldService;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping
    public @ResponseBody ResponseEntity<JSONArray> getPaths() throws Exception {
        try{
            return ResponseEntity.status(200).body(fieldService.listAllPaths());
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @GetMapping("/{field}")
    public @ResponseBody ResponseEntity<JSONArray> getAllFieldByPath(@PathVariable("field") String pathField) throws Exception {
        try{
            return ResponseEntity.status(200).body(fieldService.getInfoByPath(pathField));
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}
