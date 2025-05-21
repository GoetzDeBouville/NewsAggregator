package com.example.core.data.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "image", strict = false)
data class ImageDto(
    @field:Element(name = "title", required = false)
    var title: String? = null,

    @field:Element(name = "url", required = false)
    var url: String? = null,

    @field:Element(name = "link", required = false)
    var link: String? = null
)