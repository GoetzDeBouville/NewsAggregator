package com.example.core.data.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "media:content", strict = false)
data class ContentDto(
    @field:Attribute(name = "type", required = false)
    var type: String? = null,

    @field:Attribute(name = "width", required = false)
    var width: String? = null,

    @field:Attribute(name = "url", required = true)
    var url: String = "",

    @field:Element(name = "media:credit", required = false)
    var credit: CreditDto? = null
)