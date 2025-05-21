package com.example.core.data.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class ChannelDto(
    @field:Element(name = "title", required = false)
    var title: String? = null,

    @field:Element(name = "link", required = false)
    var link: String? = null,

    @field:Element(name = "description", required = false)
    var description: String? = null,

    @field:Element(name = "copyright", required = false)
    var copyright: String? = null,

    @field:Element(name = "pubDate", required = false)
    var pubDate: String? = null,

    @field:Element(name = "image", required = false)
    var image: ImageDto? = null,

    @field:ElementList(name = "item", inline = true, required = false)
    var items: List<ItemDto> = mutableListOf() // simpleXml заполняет списки динамически ):
)