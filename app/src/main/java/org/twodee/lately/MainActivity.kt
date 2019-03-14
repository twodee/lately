package org.twodee.lately

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

// TODO: replace this with your actual key
const val KEY = "111111111111111111111111111"

class MainActivity : Activity() {
  private lateinit var sourcesPicker: Spinner
  private lateinit var headlinesPicker: RecyclerView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    sourcesPicker = findViewById(R.id.sourcesPicker)
    headlinesPicker = findViewById(R.id.headlinesPicker)

    val layoutManager = LinearLayoutManager(this)
    headlinesPicker.layoutManager = layoutManager
    val decoration = DividerItemDecoration(this, layoutManager.orientation);
    headlinesPicker.addItemDecoration(decoration)

    sourcesPicker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(p0: AdapterView<*>?) {
      }

      override fun onItemSelected(p0: AdapterView<*>?, p1: View?, i: Int, p3: Long) {
        val parameters = mapOf("sources" to sources[i].id, "apiKey" to KEY)
        val url = parameterizeUrl("https://newsapi.org/v2/top-headlines", parameters)
        HeadlinesDownloader(this@MainActivity).execute(url)
      }
    }

    SourcesDownloader(this).execute()
  }

  var sources: List<Source> = listOf()
    set(value) {
      field = value
      sourcesPicker.adapter = ArrayAdapter<Source>(this, android.R.layout.simple_spinner_dropdown_item, field)
    }

  var headlines: List<Headline> = listOf()
    set(value) {
      field = value
      headlinesPicker.adapter = HeadlineAdapter(this, field) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
        startActivity(intent)
      }
    }
}



