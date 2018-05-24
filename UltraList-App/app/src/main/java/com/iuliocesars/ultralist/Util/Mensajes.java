package com.iuliocesars.ultralist.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.iuliocesars.ultralist.R;

/**
 * Created by IulioCesars on 15/05/2018.
 */

public class Mensajes
{
    public static void Confirmar(Context ctx, int titulo, DialogInterface.OnClickListener dialogInterface)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(ctx.getResources().getString(titulo))
                //.setIcon(android.R.drawable.ic_delete)
                .setPositiveButton(ctx.getResources().getString(R.string.txtSi), dialogInterface)
                .setNegativeButton(ctx.getResources().getString(R.string.txtNo), null)
                .show();
    }
}
