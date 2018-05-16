package com.iuliocesars.ultralist.NET;

/**
 * Created by IulioCesars on 14/05/2018.
 */

public interface INetAction<T> {
    public void Execute(T entidad);
}
