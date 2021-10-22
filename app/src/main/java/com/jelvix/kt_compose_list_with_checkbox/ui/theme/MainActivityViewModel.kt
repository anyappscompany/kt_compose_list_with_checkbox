package com.jelvix.kt_compose_list_with_checkbox.ui.theme

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.jelvix.kt_compose_list_with_checkbox.Interest
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.ArrayList

class MainActivityViewModel : ViewModel() {
    private val _interests = MutableStateFlow<List<Interest>>(listOf<Interest>())
    val interests: StateFlow<List<Interest>> = _interests

    init {
        viewModelScope.launch {
            val newList = arrayListOf<Interest>()
            newList.add(
                Interest(
                    null,
                    "Physical Health",
                    "Physical health can be defined as normal functioning of the body at all levels.",
                    false,
                    false
                )
            )
            newList.add(
                Interest(
                    null,
                    "Mental Health",
                    "Mental health includes our emotional, psychological, and social well-being. It affects how we think, feel, and act.",
                    false,
                    false
                )
            )
            newList.add(
                Interest(
                    null,
                    "Intellectual Health",
                    "Definition: Intellectual Health refers to being open to new ideas and experiences, and the desire to increase understanding, improve skills, and continually challenge yourself.",
                    false,
                    false
                )
            )
            newList.add(
                Interest(
                    null,
                    "Social Health",
                    "Social health is a term that refers to the ways in which people create healthy and positive interpersonal relationships with one another.",
                    false,
                    false
                )
            )
            newList.add(
                Interest(
                    null,
                    "Spiritual Health",
                    "Spiritual health includes a purposeful life, transcendence and actualization of different dimensions and capacities of human beings.",
                    false,
                    false
                )
            )
            newList.sortedBy {
                it.title
            }
            _interests.emit(newList)
        }
    }

    fun interestRowClicked(id: Int) {
        val interests2: ArrayList<Interest> = arrayListOf()
        _interests.value.forEachIndexed { index, interest ->
            if(index==id){
                interests2.add(interest.copy(expanded = !interest.expanded))

            }else{
                interests2.add(interest)
            }
        }
        _interests.value = arrayListOf()
        _interests.value = interests2
    }
}