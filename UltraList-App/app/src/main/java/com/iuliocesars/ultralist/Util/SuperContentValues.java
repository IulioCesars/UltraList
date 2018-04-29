package com.iuliocesars.ultralist.Util;

import android.content.ContentValues;
import android.support.design.widget.FloatingActionButton;

import com.iuliocesars.ultralist.DAO.InterfaceDAO;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IulioCesars on 29/04/2018.
 */

public class SuperContentValues {
    private ContentValues cv;

    public SuperContentValues() {
        cv = new ContentValues();
    }

    public void put(String key, Byte value)
    { cv.put(key, value); }

    public void put(String key, Long value)
    { cv.put(key, value); }

    public void put(String key, Float value)
    { cv.put(key, value); }

    public void put(String key, Short value)
    { cv.put(key, value); }

    public void put(String key, byte[] value)
    { cv.put(key, value); }

    public void put(String key, Double value)
    { cv.put(key, value); }

    public void put(String key, String value)
    { cv.put(key, value); }

    public void put(String key, Boolean value)
    { cv.put(key, value); }

    public void put(String key, Integer value)
    { cv.put(key, value); }

    public void put(String key,  BigDecimal value)
    {
        if(value != null)
            cv.put(key, value.doubleValue());
        else
            cv.putNull(key);
    }

    public void put(String key,  Timestamp value)
    {
        if(value != null)
            cv.put(key, value.toString());
        else
            cv.putNull(key);
    }




    public ContentValues getContentValues()
    { return cv; }
}
