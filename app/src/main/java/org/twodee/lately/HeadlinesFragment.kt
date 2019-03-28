package org.twodee.lately

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class HeadlinesFragment : Fragment() {
  private lateinit var sourcesPicker: Spinner
  private lateinit var headlinesPicker: RecyclerView

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_headlines, container, false)

    sourcesPicker = view.findViewById(R.id.sourcesPicker)
    headlinesPicker = view.findViewById(R.id.headlinesPicker)

    val layoutManager = LinearLayoutManager(context)
    headlinesPicker.layoutManager = layoutManager
    val decoration = DividerItemDecoration(context, layoutManager.orientation);
    headlinesPicker.addItemDecoration(decoration)

    sourcesPicker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(p0: AdapterView<*>?) {
      }

      override fun onItemSelected(p0: AdapterView<*>?, p1: View?, i: Int, p3: Long) {
        val parameters = mapOf("sources" to sources[i].id, "apiKey" to KEY)
        val url = parameterizeUrl("https://newsapi.org/v2/top-headlines", parameters)
        HeadlinesDownloader(this@HeadlinesFragment).execute(url)
      }
    }

    val parameters = mapOf("language" to "en", "apiKey" to KEY)
    val url = parameterizeUrl("https://newsapi.org/v2/sources", parameters)
    SourcesDownloader(this).execute(url)

    return view
  }

  var sources: List<Source> = listOf()
    set(value) {
      field = value
      sourcesPicker.adapter = ArrayAdapter<Source>(context!!, android.R.layout.simple_spinner_dropdown_item, field)
    }

  var headlines: List<Headline> = listOf()
    set(value) {
      field = value
      headlinesPicker.adapter = HeadlineAdapter(context!!, field) {
        (activity as UrlKeeper).url = it.url
      }
    }
}