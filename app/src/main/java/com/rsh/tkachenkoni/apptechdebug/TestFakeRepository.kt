package com.rsh.tkachenkoni.apptechdebug

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

/**
 *
 * Created by Nikolay Tkachenko
 * E-Mail: tkachenni@mail.ru
 */
object TestFakeRepository {

    private val fruitNames: List<String> = listOf("Apple", "Orange", "Banana", "Raspberry", "Strawberry", "Kiwi", "Grapes")

    private val _currentRandomFruitName = MutableLiveData<String>()
    val currentRandomFruitName: LiveData<String>
        get() = _currentRandomFruitName

    init {
        _currentRandomFruitName.value = fruitNames.first()
    }

    fun getRandomFruitName(): String {
        val random = Random()
        return fruitNames[random.nextInt(fruitNames.size)]
    }

    fun changeCurrentRandomFruitName() {
        _currentRandomFruitName.value = getRandomFruitName()
    }

}