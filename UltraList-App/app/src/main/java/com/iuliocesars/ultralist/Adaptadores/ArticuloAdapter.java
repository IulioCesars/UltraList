package com.iuliocesars.ultralist.Adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iuliocesars.ultralist.Activity.ArticuloActivity;
import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.Extras;
import com.iuliocesars.ultralist.Util.RequestCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IulioCesars on 11/03/2018.
 */

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder>
{
    List<Articulo> lstArticulos;

    public ArticuloAdapter(List<Articulo> lstArticulos) {
        this.lstArticulos = lstArticulos;
    }

    public static class ArticuloViewHolder extends RecyclerView.ViewHolder
    {
        Articulo articulo;
        TextView tvNombre, tvCategoria, tvCantidad, tvPrecioUnitario, tvTotal;
        ImageView ivFoto;

        public ArticuloViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCategoria = itemView.findViewById(R.id.tvCategoria);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvPrecioUnitario = itemView.findViewById(R.id.tvPrecioUnitario);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            ivFoto = itemView.findViewById(R.id.ivFoto);
        }

        public void Bind(Articulo a)
        {
            articulo = a;
            tvNombre.setText(articulo.getNombre());

            tvCategoria.setText(
                    itemView.getResources().getString(R.string.txtCantidad) + " " +
                    articulo.getCategoria()
            );
            tvCantidad.setText(
                    itemView.getResources().getString(R.string.txtCantidad) + " " +
                    Integer.toString(articulo.getCantidad())
            );
            tvPrecioUnitario.setText(
                    itemView.getResources().getString(R.string.txtPrecioUnitario) + " " +
                    articulo.getPrecio().toString()
            );
            tvTotal.setText(
                    itemView.getResources().getString(R.string.txtPrecioTotal) + " " +
                    (articulo.getPrecio().multiply(new BigDecimal(articulo.getCantidad()))).toString()
            );
            
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity parentActivity = (Activity) view.getContext();
                    //Toast.makeText(parentActivity, "ASDASDASD", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(parentActivity, ArticuloActivity.class);
                    i.putExtra(Extras.Articulo, articulo);
                    parentActivity.startActivityForResult(i, RequestCode.ArticuloActivity);
                }
            });
        }
    }

    @Override
    public ArticuloViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptador_elemento_articulo, parent, false);

        ArticuloViewHolder avh = new ArticuloViewHolder(itemView);
        return avh;
    }

    @Override
    public void onBindViewHolder(ArticuloViewHolder holder, int position) {
        holder.Bind(lstArticulos.get(position));
    }

    @Override
    public int getItemCount() {
        return lstArticulos.size();
    }
}
