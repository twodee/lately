package org.twodee.lately

data class Source(val id: String, val name: String) {
  override fun toString() = name
}
