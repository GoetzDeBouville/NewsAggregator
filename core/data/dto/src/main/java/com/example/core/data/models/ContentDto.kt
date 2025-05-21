package com.example.core.data.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(name = "content", strict = false)
@Namespace(prefix = "media", reference = "http://search.yahoo.com/mrss/")
data class ContentDto(
    @field:Attribute(name = "type", required = false)
    var type: String? = null,

    @field:Attribute(name = "width", required = false)
    var width: String? = null,

    @field:Attribute(name = "url", required = true)
    var url: String = "",

    @field:Element(name = "credit", required = false)
    @Namespace(prefix = "media", reference = "http://search.yahoo.com/mrss/")
    var credit: CreditDto? = null
)