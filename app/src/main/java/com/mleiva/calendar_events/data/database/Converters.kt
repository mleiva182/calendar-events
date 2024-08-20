package com.mleiva.calendar_events.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mleiva.calendar_events.domain.Accessibility
import com.mleiva.calendar_events.domain.AgeRestrictions
import com.mleiva.calendar_events.domain.Country
import com.mleiva.calendar_events.domain.Dates
import com.mleiva.calendar_events.domain.EmbeddedXX
import com.mleiva.calendar_events.domain.ImageX
import com.mleiva.calendar_events.domain.PriceRange
import com.mleiva.calendar_events.domain.Sales
import com.mleiva.calendar_events.domain.Seatmap
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data
 * Creted by: Marcelo Leiva on 23-07-2024 at 16:45
 ***/
class Converters {

    @TypeConverter
    fun fromSales(sales: Sales): String {
        return Json.encodeToString(sales)
    }

    @TypeConverter
    fun toSales(salesString: String): Sales {
        return Json.decodeFromString(salesString)
    }

    @TypeConverter
    fun fromPriceRangeList(priceRanges: List<PriceRange>): String {
        return Json.encodeToString(priceRanges)
    }

    @TypeConverter
    fun toPriceRangeList(priceRangesString: String): List<PriceRange> {
        return Json.decodeFromString(priceRangesString)
    }

    @TypeConverter
    fun fromSeatmap(seatmap: Seatmap): String {
        return Json.encodeToString(seatmap)
    }

    @TypeConverter
    fun toSeatmap(seatmapString: String): Seatmap {
        return Json.decodeFromString(seatmapString)
    }

    @TypeConverter
    fun fromAccessibility(accessibility: Accessibility): String {
        return Json.encodeToString(accessibility)
    }

    @TypeConverter
    fun toAccessibility(accessibilityString: String): Accessibility {
        return Json.decodeFromString(accessibilityString)
    }

    @TypeConverter
    fun fromAgeRestrictions(ageRestrictions: AgeRestrictions): String {
        return Json.encodeToString(ageRestrictions)
    }

    @TypeConverter
    fun toAgeRestrictions(ageRestrictionsString: String): AgeRestrictions {
        return Json.decodeFromString(ageRestrictionsString)
    }

    @TypeConverter
    fun fromEmbeddedXX(embeddedXX: EmbeddedXX): String {
        return Json.encodeToString(embeddedXX)
    }

    @TypeConverter
    fun toEmbeddedXX(embeddedXXString: String): EmbeddedXX {
        return Json.decodeFromString(embeddedXXString)
    }

    @TypeConverter
    fun fromImageXList(images: List<ImageX>): String {
        return Json.encodeToString(images)
    }

    @TypeConverter
    fun toImageXList(imagesString: String): List<ImageX> {
        return Json.decodeFromString(imagesString)
    }

    @TypeConverter
    fun fromDates(dates: Dates): String {
        return Json.encodeToString(dates)
    }

    @TypeConverter
    fun toDates(dates: String): Dates {
        return Json.decodeFromString(dates)
    }

    @TypeConverter
    fun fromCountry(country: Country): String {
        return Json.encodeToString(country)
    }

    @TypeConverter
    fun toCountry(country: String): Country {
        return Json.decodeFromString(country)
    }
}
