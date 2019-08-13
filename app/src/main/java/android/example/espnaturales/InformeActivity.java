package android.example.espnaturales;

import android.content.Intent;
import android.example.espnaturales.Datos.EspNatDbAccess;
import android.example.espnaturales.Datos.InformesEspacios;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class InformeActivity extends AppCompatActivity {

    static List<InformesEspacios> listaInformes;
    RecyclerView recyclerView;
    private DrawerLayout mDrawerLayout;
    private InfoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        EspNatDbAccess dbAccess = EspNatDbAccess.getInstance(getApplicationContext());
        listaInformes = dbAccess.getInformes();

        recyclerView = findViewById(R.id.infor_recycler);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // specify an adapter with the list to show
        mAdapter = new InfoAdapter(listaInformes);
        recyclerView.setAdapter(mAdapter);


        // Obtener la lista de espacios


    }
}

class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    private List<InformesEspacios> informesEspaciosList;

    public InfoAdapter(List<InformesEspacios> informesEspaciosList) {
        this.informesEspaciosList = informesEspaciosList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_infor, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = informesEspaciosList.get(position).getTituloInforme();
        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return informesEspaciosList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.list_title);
        }
    }
}
