package com.iuliocesars.ultralist.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iuliocesars.ultralist.Modelos.Lista;
import com.iuliocesars.ultralist.R;

import java.util.List;

/**
 * Created by IulioCesars on 10/03/2018.
 */

public class ListaAdapter
    extends RecyclerView.Adapter<ListaAdapter.ListaViewHolder>
    implements View.OnClickListener
{
    private List<Lista> lstLista;
    private View.OnClickListener listener;

    public ListaAdapter(List<Lista> lstLista) {
        this.lstLista = lstLista;
    }

    public static class ListaViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvNombre, tvDescripcion;

        public ListaViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }

        public void Bind(Lista l)
        {
            tvNombre.setText(l.getNombre());
            tvDescripcion.setText(l.getDescripcion());
        }
    }

    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptador_elemento_lista, parent, false);

        itemView.setOnClickListener(this);

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

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}
