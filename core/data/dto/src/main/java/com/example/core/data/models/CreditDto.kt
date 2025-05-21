package com.example.core.data.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text

@Root(name = "media:credit", strict = false)
data class CreditDto(
    @field:Attribute(name = "scheme", required = false)
    var scheme: String? = null,

    @field:Text(required = false)
    var value: String? = null
)