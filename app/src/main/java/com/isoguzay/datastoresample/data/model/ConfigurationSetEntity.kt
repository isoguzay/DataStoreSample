package com.isoguzay.datastoresample.data.model

data class ConfigurationSetEntity constructor(
    var countries: List<ConfigurationItemEntity>? = null,
    var languages: List<ConfigurationItemEntity>? = null,
    var genders: List<ConfigurationItemEntity>? = null
)
