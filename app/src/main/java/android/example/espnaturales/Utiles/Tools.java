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

    static private float COLOR_TIPO1;
    static private float COLOR_TIPO2;
    static private float COLOR_TIPO3;
    static private float COLOR_TIPO4;
    static private float COLOR_TIPO5;
    static private float COLOR_TIPO6;

    Tools () {
        COLOR_TIPO1 = rgbToHsl(34, 63, 83);
        COLOR_TIPO2 = rgbToHsl(100, 83, 0);
        COLOR_TIPO3 = rgbToHsl(60, 40, 80);
        COLOR_TIPO4 = rgbToHsl(100, 3, 00);
        COLOR_TIPO5 = rgbToHsl(0, 50, 00);
        COLOR_TIPO6 = rgbToHsl(0, 100, 00);
    }

    static public Tools getTools() {
        if (tools == null)
            tools = new Tools();
        return tools;
    }


    /*

     */

    private  static float  rgbToHsl(int  r, int  g, int b){

        int max_int = Math.max(Math.max(r, g), b);
        int  min_int = Math.min(Math.min(r, g), b);

        float max = (float)max_int/100f;
        float min = (float)min_int/100f;
        float r_float = (float)r/100f;
        float g_float = (float)g/100f;
        float b_float = (float)b/100f;


        float h;

        if(max_int == min_int){
            h = 0f; // achromatic
        }else{
            float d = max - min;

            if(max_int == r) {
                h = (g_float - b_float) / d + (g < b ? 6 : 0);
            } else if (max_int == g) {
                h = (b_float - r_float) / d + 2;
            } else {
                h = (r_float - g_float) / d + 4;
            }
            h /= 6;
        }

        return h * 360;
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

    public float getTipoColor(int id_tipo) {
        float color = 0f;

        switch ( id_tipo) {
            case 1:
                color = COLOR_TIPO1; // BitmapDescriptorFactory.HUE_AZURE
                break;
            case 2:
                color = COLOR_TIPO2;
                break;
            case 3:
                color = COLOR_TIPO3;
                break;
            case 4:
                color = COLOR_TIPO4;
                break;
            case 5:
                color = COLOR_TIPO5;
                break;
            case 6:
                color = COLOR_TIPO6; //BitmapDescriptorFactory.HUE_CYAN;
                break;
        }
        return color;
    }
}