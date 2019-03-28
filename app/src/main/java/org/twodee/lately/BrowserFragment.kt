package org.twodee.lately

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class BrowserFragment : Fragment() {
  private lateinit var webView: WebView
  private lateinit var progressWheel: ProgressBar

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_browser, container, false)
    progressWheel = view.findViewById(R.id.progress)

    webView = view.findViewById(R.id.webView)
    webView.settings.javaScriptEnabled = true
    webView.webViewClient = object : WebViewClient() {
      override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        progressWheel.visibility = View.GONE
      }
    }

    val keeper = activity as UrlKeeper
    webView.loadUrl(keeper.url)
    progressWheel.visibility = View.VISIBLE

    return view
  }
}
