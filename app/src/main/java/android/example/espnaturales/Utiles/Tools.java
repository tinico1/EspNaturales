package android.example.espnaturales.Utiles;

import android.content.Context;
import android.example.espnaturales.GlobalApplication;
import android.example.espnaturales.ListContentFragment;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Tools {
    static Tools tools;

    Tools () {
    }

    static public Tools getTools() {
        if (tools == null)
            tools = new Tools();
        return tools;
    }

    public RecyclerView creaRecycler(LayoutInflater inflater, ViewGroup container, FragmentActivity activity, int id) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(id, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);

        ListContentFragment.ContentAdapter adapter = new ListContentFragment.ContentAdapter(recyclerView.getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return recyclerView;
    }


    public void setImage(String uri, ImageView imageView) {
        Context context = GlobalApplication.getAppContext();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable imagen = ContextCompat.getDrawable(context, imageResource);
        imageView.setImageDrawable(imagen);
    }
}
