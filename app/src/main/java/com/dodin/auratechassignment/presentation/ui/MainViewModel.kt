package com.dodin.auratechassignment.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dodin.auratechassignment.Dependency
import com.dodin.auratechassignment.usecase.GetUiTextUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val getNotificationTextUseCase: GetUiTextUseCase by lazy {
        GetUiTextUseCase(Dependency.repository)
    }

    private val _textData = MutableLiveData<String>()
    val textData: LiveData<String>
        get() = _textData

    fun fetchRebootsData() = viewModelScope.launch(Dispatchers.IO) {
        val text = getNotificationTextUseCase()
        _textData.postValue(text)
    }
}