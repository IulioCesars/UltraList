package com.iuliocesars.ultralist.NET;

/**
 * Created by IulioCesars on 20/05/2018.
 */

public interface INetResponse<T>
{
    public void OnSuccess(T entidad);
    public void OnError(String msg);

}