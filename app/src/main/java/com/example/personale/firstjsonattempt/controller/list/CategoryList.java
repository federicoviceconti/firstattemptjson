package com.example.personale.firstjsonattempt.controller.list;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by personale on 02/03/2017.
 */

public class CategoryList {
    private Map<String, String> categotyList;

    public CategoryList(){
        categotyList = new HashMap<>();
    }

    public void add(String key, String value){
        categotyList.put(key, value);
    }

    public Map<String,String> get() {
        return categotyList;
    }
}
