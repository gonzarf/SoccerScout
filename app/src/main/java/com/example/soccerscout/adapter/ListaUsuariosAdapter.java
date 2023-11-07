package com.example.soccerscout.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccerscout.R;
import com.example.soccerscout.usuarios.Usuario;

import java.util.ArrayList;

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.UsuarioViewHolder> {

    ArrayList<Usuario> listaUsuarios;

    public ListaUsuariosAdapter(ArrayList<Usuario> listaUsuarios){
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_usuario, null, false);
        return  new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        holder.viewNombre.setText(listaUsuarios.get(position).getNombre());
        holder.viewCorreo.setText(listaUsuarios.get(position).getCorreo());

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewCorreo;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
        }
    }
}
