package com.isoguzay.datastoresample.data.local.datasource

import android.util.Log
import androidx.datastore.core.DataStore
import com.isoguzay.datastoresample.LocalConfig
import com.isoguzay.datastoresample.data.model.ConfigurationItemEntity
import com.isoguzay.datastoresample.data.model.ConfigurationSetEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ConfigDataSource @Inject constructor(private val localConfigDataStore: DataStore<LocalConfig>) {

    private val localConfig: Flow<LocalConfig> = localConfigDataStore.data


    suspend fun getConfig(): ConfigurationSetEntity {
        val countryList: ArrayList<ConfigurationItemEntity> = ArrayList()
        val languageList: ArrayList<ConfigurationItemEntity> = ArrayList()
        val genderList: ArrayList<ConfigurationItemEntity> = ArrayList()

        localConfig.first().countriesList.forEach { configurationItemEntity ->
            countryList.add(
                ConfigurationItemEntity(
                    code = configurationItemEntity.code,
                    displayName = configurationItemEntity.displayName
                )
            )
        }
        localConfig.first().languagesList.forEach { configurationItemEntity ->
            languageList.add(
                ConfigurationItemEntity(
                    code = configurationItemEntity.code,
                    displayName = configurationItemEntity.displayName
                )
            )
        }
        localConfig.first().gendersList.forEach { configurationItemEntity ->
            genderList.add(
                ConfigurationItemEntity(
                    code = configurationItemEntity.code,
                    displayName = configurationItemEntity.displayName
                )
            )
        }

        return ConfigurationSetEntity(
            countries = countryList,
            languages = languageList,
            genders = genderList
        )
    }

    suspend fun saveConfig(config: ConfigurationSetEntity) {
        try {
            localConfigDataStore.updateData {
                val localConfig = it.toBuilder().clear()
                config.countries?.forEach { country ->
                    val countryBuilder = LocalConfig.ConfigurationItemEntity.newBuilder()
                        .setCode(country.code)
                        .setDisplayName(country.displayName).build()
                    localConfig.addCountries(countryBuilder)
                }
                config.languages?.forEach { language ->
                    val languageBuilder = LocalConfig.ConfigurationItemEntity.newBuilder()
                        .setCode(language.code)
                        .setDisplayName(language.displayName).build()
                    localConfig.addLanguages(languageBuilder)
                }
                config.genders?.forEach { gender ->
                    val genderBuilder = LocalConfig.ConfigurationItemEntity.newBuilder()
                        .setCode(gender.code)
                        .setDisplayName(gender.displayName).build()
                    localConfig.addGenders(genderBuilder)
                }

                localConfig.build()
            }
        } catch (e: Exception) {
            Log.e("DataStoreTag", "exception is $e")
        }
    }

}