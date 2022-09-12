package com.devhassan.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.devhassan.database.entity.GenreEntity
import com.devhassan.database.entity.ProductionCompanyEntity
import com.devhassan.database.entity.SpokenLanguageEntity
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

@ProvidedTypeConverter
class Converter @Inject constructor (
    private val moshi: Moshi
) {

    //Genre Entity Converter
    @TypeConverter
    fun fromListOfGenreEntity(value: List<GenreEntity>?): String? {
        val listType = object : TypeToken<List<GenreEntity?>?>() {}.type
        val adapter: JsonAdapter<List<GenreEntity>>? = moshi.adapter(listType)
        return adapter?.toJson(value)
    }

    @TypeConverter
    fun toListOfGenreEntity(json: String?): List<GenreEntity>? {
        val listType = object : TypeToken<List<GenreEntity?>?>() {}.type
        val adapter: JsonAdapter<List<GenreEntity>>? = moshi.adapter(listType)
        return if (json.isNullOrEmpty()) emptyList() else adapter?.fromJson(json)
    }

    //Production Company Entity Converter
    @TypeConverter
    fun fromListOfProductionCompanyEntity(value: List<ProductionCompanyEntity>?): String? {
        val listType = object : TypeToken<List<ProductionCompanyEntity?>?>() {}.type
        val adapter: JsonAdapter<List<ProductionCompanyEntity>>? = moshi.adapter(listType)
        return adapter?.toJson(value)
    }

    @TypeConverter
    fun toListOfProductionCompanyEntity(json: String?): List<ProductionCompanyEntity>? {
        val listType = object : TypeToken<List<GenreEntity?>?>() {}.type
        val adapter: JsonAdapter<List<ProductionCompanyEntity>>? = moshi.adapter(listType)
        return if (json.isNullOrEmpty()) emptyList() else adapter?.fromJson(json)
    }

    //Spoken Languages Entity Converter
    @TypeConverter
    fun fromListOfSpokenLanguagesEntity(value: List<SpokenLanguageEntity>?): String? {
        val listType = object : TypeToken<List<SpokenLanguageEntity?>?>() {}.type
        val adapter: JsonAdapter<List<SpokenLanguageEntity>>? = moshi.adapter(listType)
        return adapter?.toJson(value)
    }

    @TypeConverter
    fun toListOfSpokenLanguagesEntity(json: String?): List<SpokenLanguageEntity>? {
        val listType = object : TypeToken<List<SpokenLanguageEntity?>?>() {}.type
        val adapter: JsonAdapter<List<SpokenLanguageEntity>>? = moshi.adapter(listType)
        return if (json.isNullOrEmpty()) emptyList() else adapter?.fromJson(json)
    }


}