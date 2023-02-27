package com.isoguzay.datastoresample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isoguzay.datastoresample.data.local.datasource.ConfigDataSource
import com.isoguzay.datastoresample.data.model.ConfigurationSetEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val configDataSource: ConfigDataSource
) : ViewModel() {

    private val _returnedVal = MutableLiveData<ConfigurationSetEntity>()
    val returnedVal: LiveData<ConfigurationSetEntity>
        get() = _returnedVal

    fun saveConfig(data: ConfigurationSetEntity) = viewModelScope.launch {
        configDataSource.saveConfig(data)
    }

    fun getConfig() = viewModelScope.launch {
        _returnedVal.value = configDataSource.getConfig()
    }
}