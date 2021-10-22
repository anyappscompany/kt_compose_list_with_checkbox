package com.jelvix.kt_compose_list_with_checkbox

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jelvix.kt_compose_list_with_checkbox.ui.theme.Kt_compose_list_with_checkboxTheme
import com.jelvix.kt_compose_list_with_checkbox.ui.theme.MainActivityViewModel

class MainActivity : ComponentActivity() {

    //private val mainActivityViewModel by viewModels<MainActivityViewModel>()
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Kt_compose_list_with_checkboxTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainActivityScreen(mainActivityViewModel)
                }
            }
        }
    }
}

@Composable
fun MainActivityScreen(mainActivityViewModel: MainActivityViewModel?=null) {
    val interests = mainActivityViewModel?.interests?.collectAsState()

    LazyColumn {
        interests?.value?.forEachIndexed { index, interest ->
        Log.d("debapp", "${interest.title} ${interest.expanded}")
            item() {
                Text(text = interest.title, Modifier.clickable {
                    mainActivityViewModel.interestRowClicked(index)
                }.background(color = Color(0xFFFFEB3B)))
                Divider(modifier = Modifier.fillMaxWidth().height(10.dp))
                if(interest.expanded){
                    Image(painter = painterResource(id = R.drawable.ic_android_off), contentDescription = "off")
                }else{
                    Image(painter = painterResource(id = R.drawable.ic_android_on), contentDescription = "on")
                }
                Divider(modifier = Modifier.fillMaxWidth().height(10.dp))
                if(interest.expanded){
                    Text(text = interest.description)
                }
                Divider(modifier = Modifier.fillMaxWidth().height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Kt_compose_list_with_checkboxTheme {
        MainActivityScreen()
    }
}

data class Interest(val _id: Int?=null, val title: String, val description: String, val checked: Boolean = false, val expanded: Boolean = false)