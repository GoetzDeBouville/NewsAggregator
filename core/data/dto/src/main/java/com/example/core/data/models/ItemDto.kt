package com.example.core.data.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class ItemDto(
    @field:Element(name = "title", required = false)
    var title: String? = null,

    @field:Element(name = "link", required = false)
    var link: String? = null,

    @field:Element(name = "description", required = false)
    var description: String? = null,

    @field:ElementList(entry = "category", inline = true, required = false)
    var categories: List<CategoryDto> = mutableListOf(), // simpleXml заполняет списки динамически ):

    @field:Element(name = "pubDate", required = false)
    var pubDate: String? = null,

    @field:Element(name = "guid", required = false)
    var guid: String? = null,

    @field:ElementList(name = "media:content", inline = true, required = false)
    var contents: List<ContentDto> = mutableListOf(), // simpleXml заполняет списки динамически ):

    @field:Element(name = "dc:creator", required = false)
    var dcCreator: String? = null,

    @field:Element(name = "dc:date", required = false)
    var dcDate: String? = null
)