package com.example.adrian.findme.models;

import com.parse.ParseObject;
import com.parse.ParseClassName;

/**
 * Created by Adrian on 06-Sep-15.
 */
@ParseClassName("PlaceCategory")
public class PlaceCategory extends ParseObject {
    public String getName() {
        return getString("name");
    }
}