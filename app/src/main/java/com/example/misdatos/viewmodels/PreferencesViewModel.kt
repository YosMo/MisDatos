package com.example.misdatos.viewmodels

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class PreferencesViewModel (val context: Context):ViewModel(){
    companion object{
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        val NAME = stringPreferencesKey( "name")
        val AGE = intPreferencesKey( "age")
        val HEIGHT = intPreferencesKey( "height")
        val WEIGHT = intPreferencesKey( "weight")
        val HOBBIE = stringPreferencesKey( "hobbie")
    }
    val age: Flow<Int> = context.dataStore.data.map {
            preferences ->
            // No type safety .
            preferences [AGE] ?:0
        }

    val name: Flow<String> = context.dataStore.data.map {
            preferences ->
        // No type safety .
        preferences [NAME] ?:""
    }

    val height: Flow<Int> = context.dataStore.data.map {
            preferences ->
        // No type safety .
        preferences [HEIGHT] ?:0
    }

    val weight: Flow<Int> = context.dataStore.data.map {
            preferences ->
        // No type safety .
        preferences [WEIGHT] ?:0
    }

    val hobbie: Flow<String> = context.dataStore.data.map {
            preferences ->
        // No type safety .
        preferences [HOBBIE] ?:""
    }

    //Equivalente a SetName y SetAge
    suspend fun setNameAndAge(name:String, age:Int, height:Int, weight:Int, hobbie:String){
        context.dataStore.edit { settings ->
            settings[NAME] = name
            settings[HOBBIE] = hobbie
            settings[AGE] = age
            settings[HEIGHT] = height
            settings[WEIGHT] = weight
        }
    }

}