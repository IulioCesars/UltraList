package com.iuliocesars.ultralist.Util;

import android.database.Cursor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IulioCesars on 29/04/2018.
 */

public class SuperCursor
{
    private Cursor c;

    public SuperCursor(Cursor c)
    { this.c = c; }

    public int getInt(String columnName)
    {
        int column = c.getColumnIndex(columnName);
        if(!c.isNull(column))
            return c.getInt(column);
        else
            return 0;
    }

    public String getString(String columnName)
    {
        int column = c.getColumnIndex(columnName);
        if(!c.isNull(column))
            return c.getString(column);
        else
            return "";
    }

    public BigDecimal getBigDecimal(String columnName)
    {
        int column = c.getColumnIndex(columnName);
        if(!c.isNull(column))
        {
            String valor = c.getString(column);
            return new BigDecimal(valor);
        }
        else
            return new BigDecimal(0);
    }

    public Timestamp getTimeStamp(String columnName){
        int column = c.getColumnIndex(columnName);
        if(!c.isNull(column))
        {
            try {
                String valor = c.getString(column);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(valor);
                return new Timestamp(parsedDate.getTime());
            }
            catch (Exception ex)
            {
                return null;
            }
        }
        else
            return null;
    }
}
