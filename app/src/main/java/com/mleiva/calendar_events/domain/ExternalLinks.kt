package com.mleiva.calendar_events.domain

data class ExternalLinks(
    val facebook: List<Facebook>,
    val homepage: List<Homepage>,
    val instagram: List<Instagram>,
    val twitter: List<Twitter>,
    val wiki: List<Wiki>
)