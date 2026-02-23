package app.icampuspass.views.components

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun AccountLogOutWebView(
    modifier: Modifier = Modifier,
    onLogOutPageFinished: () -> Unit = {}
) {
    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                getSettings().javaScriptEnabled = true
                setWebViewClient(AccountLogOutWebViewClient(onLogOutPageFinished = onLogOutPageFinished))
            }
        },
        modifier = modifier,
        update = {
            it.loadUrl("https://sso.tku.edu.tw/pkmslogout")
        }
    )
}

private class AccountLogOutWebViewClient(
    val onLogOutPageFinished: () -> Unit = {}
) : WebViewClient() {
    override fun onPageFinished(view: WebView, url: String?) {
        onLogOutPageFinished()
    }
}
