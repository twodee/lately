package org.twodee.lately

import android.os.AsyncTask
import java.lang.ref.WeakReference
import java.net.URL

class SourcesDownloader(context: HeadlinesFragment) : AsyncTask<URL, Void, List<Source>>() {
  private val context = WeakReference(context)

  override fun doInBackground(vararg urls: URL): List<Source> {
    val result = getJson(urls[0])
    val sourcesJson = result.getJSONArray("sources")
    val sources = (0 until sourcesJson.length()).map { i ->
      val source = sourcesJson.getJSONObject(i)
      Source(source.getString("id"), source.getString("name"))
    }
    return sources
  }

  override fun onPostExecute(sources: List<Source>) {
    super.onPostExecute(sources)
    context.get()?.sources = sources
  }
}