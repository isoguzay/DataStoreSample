package com.isoguzay.datastoresample.data.local.datastore

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.isoguzay.datastoresample.LocalConfig
import java.io.InputStream
import java.io.OutputStream

object LocalConfigSerializer : Serializer<LocalConfig> {

    override val defaultValue: LocalConfig = LocalConfig.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): LocalConfig {
        try {
            return LocalConfig.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: LocalConfig, output: OutputStream) = t.writeTo(output)

}


val Context.localConfigDataStore: DataStore<LocalConfig> by dataStore(
    fileName = "config.pb",
    serializer = LocalConfigSerializer
)