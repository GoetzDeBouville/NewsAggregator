package com.example.core.data.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class RssDto(
    @field:Attribute(name = "version", required = false)
    var version: String? = null,

    @field:Element(name = "channel")
    var channel: ChannelDto = ChannelDto()
)