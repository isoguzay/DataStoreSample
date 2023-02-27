package com.isoguzay.datastoresample.data.mock

import com.isoguzay.datastoresample.data.model.ConfigurationItemEntity
import com.isoguzay.datastoresample.data.model.ConfigurationSetEntity

object MockConfigData {

    fun getMockConfig() =
        ConfigurationSetEntity(
            languages = listOf(
                ConfigurationItemEntity(
                    code = "en", displayName = "English"
                ), ConfigurationItemEntity(
                    code = "tr", displayName = "Turkce"
                )
            ),
            countries = listOf(
                ConfigurationItemEntity(
                    code = "AT", displayName = "Austria"
                ), ConfigurationItemEntity(
                    code = "TR", displayName = "Turkiye"
                )
            ),
            genders = listOf(
                ConfigurationItemEntity(
                    code = "M", displayName = "Male"
                ), ConfigurationItemEntity(
                    code = "F", displayName = "Female"
                )
            ),
        )

}