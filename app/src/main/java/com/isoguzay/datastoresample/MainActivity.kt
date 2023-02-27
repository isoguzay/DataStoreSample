package com.isoguzay.datastoresample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.isoguzay.datastoresample.data.mock.MockConfigData
import com.isoguzay.datastoresample.ui.theme.DataStoreSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    DataStoreTestScreen()
                }
            }
        }
    }
}

@Composable
fun DataStoreTestScreen(
    viewModel: DataStoreViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    val data = viewModel.returnedVal.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Button(onClick = {
            viewModel.saveConfig(MockConfigData.getMockConfig())
        }) {
            Text(text = "Set Config to Local")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.getConfig()
            println(viewModel.returnedVal.value.toString())
        }) {
            Text(text = "Get Config From Local")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxSize(),
            text = data.value?.genders?.get(0)?.code.toString()
        )
    }
}