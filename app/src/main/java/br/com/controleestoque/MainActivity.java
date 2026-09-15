package br.com.controleestoque;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {
    private static final String PREFS = "controle_estoque";
    private static final String KEY_URL = "server_url";
    private WebView webView;
    private SharedPreferences prefs;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        String url = prefs.getString(KEY_URL, "");
        if (url.isEmpty()) showConfig(); else showWeb(url);
    }

    private void showConfig() {
        setContentView(R.layout.activity_main);
        EditText input = findViewById(R.id.urlInput);
        input.setText(prefs.getString(KEY_URL, ""));
        Button button = findViewById(R.id.connectButton);
        button.setOnClickListener(v -> {
            String url = input.getText().toString().trim();
            if (!(url.startsWith("http://") || url.startsWith("https://"))) {
                input.setError("Use um endereço começando com http:// ou https://");
                return;
            }
            prefs.edit().putString(KEY_URL, url).apply();
            showWeb(url);
        });
    }

    private void showWeb(String url) {
        webView = new WebView(this);
        webView.setBackgroundColor(Color.WHITE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
            @Override public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(MainActivity.this, "Não foi possível acessar o servidor.", Toast.LENGTH_LONG).show();
            }
        });
        setContentView(webView);
        webView.loadUrl(url);
    }

    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack(); else super.onBackPressed();
    }

    @Override public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add("Configurar servidor");
        return true;
    }

    @Override public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if ("Configurar servidor".contentEquals(item.getTitle())) { showConfig(); return true; }
        return super.onOptionsItemSelected(item);
    }
}
