package app.icampuspass.views.components

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun AccountLogInWebView(
    modifier: Modifier = Modifier,
    onLogInPageFinished: (value: String) -> Unit = {}
) {
    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                getSettings().javaScriptEnabled = true
                setWebViewClient(AccountLogInWebViewClient(onLogInPageFinished = onLogInPageFinished))
            }
        },
        modifier = modifier,
        update = {
            it.loadUrl("https://sso.tku.edu.tw/ilife/CoWork/AndroidSsoLogin.cshtml")
        }
    )
}

private class AccountLogInWebViewClient(
    val onLogInPageFinished: (value: String) -> Unit = {}
) : WebViewClient() {
    override fun onPageFinished(view: WebView, url: String?) {
        view.evaluateJavascript(
            "getSsoLoginToken();",
            { value ->
                val result = value.removeSurrounding("\"")

                if (result.isNotBlank() && result != "null") {
                    onLogInPageFinished(result)
                }
            }
        );
    }
}
