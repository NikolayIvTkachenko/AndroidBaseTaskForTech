package com.rsh.tkachenkoni.apptechdebug

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * Created by Nikolay Tkachenko
 * E-Mail: tkachenni@mail.ru
 */
class MainViewModel : ViewModel() {

    val currentRandomFruitName: LiveData<String>
        get() = TestFakeRepository.currentRandomFruitName

    fun onChangeRandomFruitClick() = TestFakeRepository.changeCurrentRandomFruitName()


    val editTextContent = MutableLiveData<String>()

    private val _displayedEditTextContent = MutableLiveData<String>()
    val displayedEditTextContent: LiveData<String>
        get() = _displayedEditTextContent

    fun onDisplayEditTextContentClick(){
        _displayedEditTextContent.value = editTextContent.value
    }

}