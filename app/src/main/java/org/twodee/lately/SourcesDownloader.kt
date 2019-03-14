package org.twodee.lately

import android.os.AsyncTask
import java.lang.ref.WeakReference

class SourcesDownloader(activity: MainActivity) : AsyncTask<Void, Void, List<Source>>() {
  private val context = WeakReference(activity)

  override fun doInBackground(vararg p0: Void?): List<Source> {
    val parameters = mapOf("language" to "en", "apiKey" to KEY)
    val result = getJson(parameterizeUrl("https://newsapi.org/v2/sources", parameters))

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