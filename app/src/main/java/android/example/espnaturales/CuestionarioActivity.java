package android.example.espnaturales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CuestionarioActivity extends AppCompatActivity {

    private WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String url = getIntent().getStringExtra(ListaEspacios.urlForm);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        myWebView = (WebView) findViewById(R.id.wbCuestionario);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        //   myWebView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSeRMy9q23RVzwSnL_Nlo7rRXDVxmqbk4VCludVJwYlu-sE9Ww/viewform");


        myWebView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (myWebView.canGoBack()) {
                        myWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}