package org.twodee.lately

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log

class BrowserActivity : FragmentActivity(), UrlKeeper {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    url = intent.getStringExtra("url")
    setContentView(R.layout.activity_browser)
  }

  override lateinit var url: String
}
