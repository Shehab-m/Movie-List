package com.agb.movielist.data.local.converters

import androidx.room.TypeConverter
import com.agb.movielist.domain.utils.enums.MovieCategory

class Converters {
    @TypeConverter
    fun fromMovieCategory(category: MovieCategory): String {
        return category.name
    }

    @TypeConverter
    fun toMovieCategory(category: String): MovieCategory {
        return MovieCategory.valueOf(category)
    }
}