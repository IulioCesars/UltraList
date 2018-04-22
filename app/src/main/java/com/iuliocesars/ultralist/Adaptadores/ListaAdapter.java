package com.iuliocesars.ultralist.Adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.iuliocesars.ultralist.Activity.ListaActivity;
import com.iuliocesars.ultralist.Modelos.Lista;
import com.iuliocesars.ultralist.R;
import com.iuliocesars.ultralist.Util.Extras;
import com.iuliocesars.ultralist.Util.RequestCode;

import java.util.List;

/**
 * Created by IulioCesars on 10/03/2018.
 */

public class ListaAdapter
    extends RecyclerView.Adapter<ListaAdapter.ListaViewHolder>
{
    private List<Lista> lstLista;
    private View.OnClickListener listener;

    public ListaAdapter(List<Lista> lstLista) {
        this.lstLista = lstLista;
    }

    public static class ListaViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvNombre, tvDescripcion;
        Lista lista;
        public ListaViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }

        public void Bind(Lista l)
        {
            lista = l;
            tvNombre.setText(l.getNombre());
            tvDescripcion.setText(l.getDescripcion());

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity parentActivity = (Activity) view.getContext();
                    //Toast.makeText(parentActivity, "SDFSDF", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(parentActivity, ListaActivity.class);
                    i.putExtra(Extras.Lista, lista);
                    parentActivity.startActivityForResult(i, RequestCode.ListaActivity);
                }
            });
        }

    }

    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptador_elemento_lista, parent, false);

        //itemView.setOnClickListener(this);

        ListaViewHolder lvh = new ListaViewHolder(itemView);
        return lvh;
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        Lista l = lstLista.get(position);
        holder.Bind(l);
    }

    @Override
    public int getItemCount() {
        return lstLista.size();
    }


}
