package org.twodee.lately

import android.os.AsyncTask
import java.lang.ref.WeakReference
import java.net.URL

class HeadlinesDownloader(context: HeadlinesFragment) : AsyncTask<URL, Void, List<Headline>>() {
  private val context = WeakReference(context)

  override fun doInBackground(vararg urls: URL): List<Headline> {
    val result = getJson(urls[0])

    val headlinesJson = result.getJSONArray("articles")
    val headlines = (0 until headlinesJson.length()).map { i ->
      val headline = headlinesJson.getJSONObject(i)
      Headline(headline.getString("title"), headline.getString("url"))
    }

    return headlines
  }

  override fun onPostExecute(headlines: List<Headline>) {
    super.onPostExecute(headlines)
    context.get()?.headlines = headlines
  }
}