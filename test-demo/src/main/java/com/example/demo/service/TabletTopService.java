package com.example.demo.service;



import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TabletTopService {

    @Autowired
    @Qualifier("dataSourceTable")
    DataSource tableTop;

    @Autowired
    FieldService fieldService;

    public JSONArray getColumnByDocumentAndPath(String document, String path) throws Exception {

        JSONArray fields = fieldService.getInfoByPath(path);
        String [] fieldsPaths = fields.toJSONString().replaceAll("\\W+", ",").split(",");
        StringBuilder sb = new StringBuilder();
        String sep = "";
        for(String s : fieldsPaths){
            if(s != "" && !s.equals("paths") && s != " "){
                sb.append(sep);
                sb.append(s);
                sep = ",";
            }
        }
        String test = sb.toString();
        Connection con = tableTop.getConnection();
        String query = String.format("SELECT %s FROM tabletop WHERE document='%s'",test.replaceAll(",name,", "name,"),document);
        ResultSet resultSet = con.prepareStatement(query).executeQuery();
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        JSONArray jsonArray = new JSONArray();

        while (resultSet.next()){
            int numColumns = rsMetaData.getColumnCount();
            JSONObject jsonObject = new JSONObject();
            for(int i=1;i<=numColumns;i++){
                String column_name = rsMetaData.getColumnName(i);
                jsonObject.put(column_name, resultSet.getObject(column_name));
            }
            jsonArray.add(jsonObject);
        }
        con.close();
        return jsonArray;
    }
}
