package com.mleiva.calendar_events.domain

data class Embedded(
    val _embedded: EmbeddedX,
    val _links: LinksXXX,
    val page: Page
)