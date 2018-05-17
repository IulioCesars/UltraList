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

import com.iuliocesars.ultralist.Activity.ArticuloScrollingActivity;
import com.iuliocesars.ultralist.Activity.MapsActivity;
import com.iuliocesars.ultralist.Modelos.Articulo;
import com.iuliocesars.ultralist.Modelos.Oferta;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.ConexionWS;
import com.iuliocesars.ultralist.Util.Extras;
import com.iuliocesars.ultralist.Util.RequestCode;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IulioCesars on 15/05/2018.
 */

public class OfertaAdapter extends RecyclerView.Adapter<OfertaAdapter.OfertaViewHolder>
{
    List<Oferta> lstOfertas;

    public OfertaAdapter(List<Oferta> lstOfertas) {
        this.lstOfertas = lstOfertas;
    }

    public static class OfertaViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvNombre, tvCategoria, tvCantidad, tvPrecioUnitario, tvTotal;
        ImageView ivFoto;
        View view;

        public OfertaViewHolder(View itemView) {
            super(itemView);
            view =itemView;
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCategoria = itemView.findViewById(R.id.tvCategoria);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvPrecioUnitario = itemView.findViewById(R.id.tvPrecioUnitario);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            ivFoto = itemView.findViewById(R.id.ivFoto);

            tvCantidad.setVisibility(View.INVISIBLE);
            tvPrecioUnitario.setVisibility(View.INVISIBLE);
        }

        public void Bind(final Oferta o)
        {
            tvNombre.setText(o.nombre);

            tvCategoria.setText(
                    itemView.getResources().getString(R.string.txtCategoria) + " " +
                            o.categoria
            );
            tvTotal.setText(
                    itemView.getResources().getString(R.string.txtPrecioTotal) + " " + o.precio
            );

            if(o.image_path != null && !o.image_path.isEmpty())
            {
                try
                {
                    String URL = ConexionWS.URL_IMAGENES + o.image_path;
                    Picasso.get().load(URL).into(ivFoto);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity parentActivity = (Activity) view.getContext();

                    Intent i = new Intent(parentActivity, MapsActivity.class);
                    i.putExtra(Extras.Oferta, o);
                    parentActivity.startActivityForResult(i, RequestCode.MapsActivity);
                }
            });
        }


    }

    @Override
    public OfertaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptador_elemento_articulo, parent, false);

        OfertaViewHolder ovh = new OfertaViewHolder(itemView);
        return ovh;
    }

    @Override
    public void onBindViewHolder(OfertaViewHolder holder, int position) {
        holder.Bind(lstOfertas.get(position));
    }

    @Override
    public int getItemCount() {
        return lstOfertas.size();
    }
}
