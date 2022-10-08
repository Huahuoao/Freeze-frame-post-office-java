package com.huahuo.app.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface BaiduService {
    public String similarAdd(String url);
    public ArrayList<HashMap<String,String>> similarSearch(String url);
}
