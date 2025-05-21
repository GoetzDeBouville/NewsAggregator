package com.example.core.data.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text

@Root(name = "category", strict = false)
data class CategoryDto(
    @field:Attribute(name = "domain", required = false)
    var domain: String? = null,

    @field:Text(required = false)
    var value: String? = null
)
