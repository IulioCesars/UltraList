package com.iuliocesars.ultralist.Adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iuliocesars.ultralist.Activity.ArticuloScrollingActivity;
import com.iuliocesars.ultralist.Base.BaseActivity;
import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.Extras;
import com.iuliocesars.ultralist.Util.RequestCode;

import java.io.FileInputStream;
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
        View view;

        public ArticuloViewHolder(View itemView) {
            super(itemView);
            view =itemView;
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCategoria = itemView.findViewById(R.id.tvCategoria);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvPrecioUnitario = itemView.findViewById(R.id.tvPrecioUnitario);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            ViewGroup.LayoutParams lp = itemView.findViewById(R.id.pnlMeGusta).getLayoutParams();
            lp.height = 0;
            itemView.findViewById(R.id.pnlMeGusta).setLayoutParams(lp);
        }

        public void Bind(Articulo a)
        {
            articulo = a;
            tvNombre.setText(articulo.getNombre());

            tvCategoria.setText(
                    itemView.getResources().getString(R.string.txtCategoria) + " " +
                    ObtenerCategoria(articulo.getCategoria())
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

            if(articulo.getImage_path() != null && !articulo.getImage_path().isEmpty())
            {
                try
                {
                    FileInputStream fis =  view.getContext().openFileInput(articulo.getImage_path());
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    ivFoto.setImageBitmap(bitmap);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity parentActivity = (Activity) view.getContext();
                    //Toast.makeText(parentActivity, "ASDASDASD", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(parentActivity, ArticuloScrollingActivity.class);
                    i.putExtra(Extras.Articulo, articulo);
                    parentActivity.startActivityForResult(i, RequestCode.ArticuloActivity);
                }
            });

        }

        protected String ObtenerCategoria(String cat)
        {
            Integer index = 0;
            switch (cat)
            {
                case "Any": { index = 0; break;}
                case "Home": { index = 1; break;}
                case "Personal": { index = 2; break;}
                case "Food": { index = 3; break;}
                case "Technology": { index = 4; break;}

                case "Ninguna": { index = 0; break;}
                case "Hogar": { index = 1; break;}
                case "Comida": { index = 3; break;}
                case "Tecnologia": { index = 4; break;}
            }

            String[] arr = itemView.getContext().getResources().getStringArray(R.array.arrCategorias);
            return arr[index];
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
