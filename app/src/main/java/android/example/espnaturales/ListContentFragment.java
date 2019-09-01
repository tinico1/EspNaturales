/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.example.espnaturales;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.example.espnaturales.Datos.EspacioNatural;
import android.example.espnaturales.Utiles.Tools;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Provides UI for the view with List.
 */
public class ListContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentActivity activity = getActivity();

        RecyclerView recyclerView = Tools.getTools().creaRecycler( inflater,  container,  activity,  R.layout.recycler_view);
        return recyclerView;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;
        public TextView description;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list, parent, false));
            avator = itemView.findViewById(R.id.list_avatar);
            name = itemView.findViewById(R.id.list_title);
            description = itemView.findViewById(R.id.list_desc);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    EspacioNatural espacioNatural= ListaEspacios.listaEspacios.get(getAdapterPosition());
                    int id = espacioNatural.getId();
                    intent.putExtra(DetailActivity.EXTRA_ID,
                            id);

                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }


    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.


        public ContentAdapter(Context context) {
            Resources resources = context.getResources();

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            String uri = ListaEspacios.listaEspacios.get(position).getNomIcono();

            Tools.getTools().setImage(uri, holder.avator);
            holder.name.setText(ListaEspacios.listaEspacios.get(position).getNombre());
            holder.description.setText(ListaEspacios.listaEspacios.get(position).getDescCorta());
        }


        @Override
        public int getItemCount() {
            return ListaEspacios.listaEspacios.size();
        }
    }

}
