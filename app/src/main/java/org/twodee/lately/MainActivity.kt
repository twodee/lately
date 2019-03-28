package org.twodee.lately

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.widget.FrameLayout

// TODO: replace this with your actual key
const val KEY = "a2066e2840454fe19e40d14e0ede031d"

class MainActivity : FragmentActivity(), UrlKeeper {
  private var fragmentContainer: FrameLayout? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    fragmentContainer = findViewById(R.id.fragment_container)
  }

  override var url: String = ""
    set(value) {
      field = value
      if (fragmentContainer == null) {
        val intent = Intent(this, BrowserActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
      } else {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = BrowserFragment()
        transaction.apply {
          replace(R.id.fragment_container, fragment)
          setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//          addToBackStack(null)
          commit()
        }
      }
    }
}



