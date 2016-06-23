package com.example.yora.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yora.R;
import com.example.yora.infrastructure.YoraApplication;
import com.example.yora.services.Account;
import com.squareup.otto.Subscribe;

public class ExternalLoginActivity extends BaseActivity {
    public static final String EXTRA_EXTERNAL_SERVICE = "EXTRA_EXTERNAL_SERVICE";

    private static final int REQUEST_REGISTER = 1;

    private WebView _webView;
    private String _service;
    private View _progressFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CookieManager.getInstance().removeAllCookie(); // Forget previous sign-in
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_external_login);
        _webView = (WebView) findViewById(R.id.activity_external_login_webView);
        _service = getIntent().getStringExtra(EXTRA_EXTERNAL_SERVICE);

        _progressFrame = findViewById(R.id.activity_external_login_progressFrame);
        _progressFrame.setVisibility(View.GONE);

        _webView.getSettings().setJavaScriptEnabled(true);
        _webView.loadUrl(YoraApplication.API_ENDPOINT
                + "/api/v1/auth/externallogin?provider=" + _service
                + "&response_type=token&client_id=android&redirect_uri=http%3A%2F%2F" + YoraApplication.API_ENDPOINT.getHost() + "%2Fmobile-log"
                + "&studenttoken=" + YoraApplication.STUDENT_TOKEN);

        _webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if (uri.getHost().equals(YoraApplication.API_ENDPOINT.getHost()) && uri.getPath().equals("/mobile-login")) {
                    externalLoginSuccess(uri);
                    return true;
                }

                return false;
            }
        });
    }

    private void externalLoginSuccess(Uri uri) {
        String externalToken = uri.getQueryParameter("externalAccessToken");
        String provider = uri.getQueryParameter("provider");
        boolean hasLocalAccount = uri.getQueryParameter("hasLocalAccount").equalsIgnoreCase("true");
        String externalUsername = uri.getQueryParameter("externalUsername");

        if (!hasLocalAccount) {
            Intent intent = new Intent(this, RegisterActivity.class);
            intent.putExtra(RegisterActivity.EXTRA_EXTERNAL_PROVIDER, provider);
            intent.putExtra(RegisterActivity.EXTRA_EXTERNAL_TOKEN, externalToken);
            intent.putExtra(RegisterActivity.EXTRA_EXTERNAL_USERNAME, externalUsername);
            startActivityForResult(intent, REQUEST_REGISTER);
        } else {
            _progressFrame.setVisibility(View.VISIBLE);
            bus.post(new Account.LoginWithExternalTokenRequest(provider, externalToken));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_REGISTER && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Subscribe
    public void onFinishLogin(Account.LoginWithExternalTokenResponse response) {
        if (!response.didSucceed()) {
            _progressFrame.setVisibility(View.GONE);
            response.showErrorToast(this);
            return;
        }

        setResult(RESULT_OK);
        finish();
    }
}
