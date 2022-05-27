package com.example.demo.controller;


import com.example.demo.service.TabletTopService;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tabletop")
public class TableTopController {

    @Autowired
    TabletTopService tabletTopService;


    @GetMapping("/{document}/{path}")
    public @ResponseBody
    ResponseEntity<JSONArray> getPathsFields(@PathVariable("document") String document, @PathVariable("path") String path) throws Exception{
        try{
            return ResponseEntity.status(200).body(tabletTopService.getColumnByDocumentAndPath(document,path));
        }catch (Exception e){
            throw new Exception(e);
        }
    }

}
