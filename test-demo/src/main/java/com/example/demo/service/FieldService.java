package com.example.demo.service;


import com.example.demo.model.FieldModel;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

@Service
public class FieldService {


    @Autowired
    @Qualifier("dataSource")
    DataSource field;


    public JSONArray listAllPaths() throws SQLException {
        Connection con = field.getConnection();
        ResultSet resultSet = con.prepareStatement("SELECT * FROM field").executeQuery();
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


    public JSONArray getInfoByPath(String pathField) throws Exception{
        Connection con = field.getConnection();
        String query = String.format("SELECT paths FROM field WHERE name_field='%s'", pathField);
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
