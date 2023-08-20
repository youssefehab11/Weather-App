package com.example.weatherapp.data

import com.example.weatherapp.model.NextDays

class GetDay {
    fun getDay(nextDays:NextDays): Int{
        val day = when(nextDays.days){
            Days.ONE -> 1
            Days.TWO -> 2
            Days.THREE -> 3
            Days.FOUR -> 4
            Days.FIVE -> 5
            Days.SIX -> 6
            Days.SEVEN -> 7
            Days.EIGHT -> 8
            Days.NINE -> 9
            Days.TEN -> 10
            Days.ELEVEN -> 11
            Days.TWELVE -> 12
            Days.THIRTEEN -> 13
            Days.FOURTEEN -> 14
            Days.FIFTEEN -> 15
            Days.SIXTEEN -> 16
            Days.SEVENTEEN -> 17
            Days.EIGHTEEN -> 18
            Days.NINETEEN -> 19
            Days.TWENTY -> 20
            Days.TWENTY_ONE -> 21
            Days.TWENTY_TWO -> 22
            Days.TWENTY_THREE -> 23
            Days.TWENTY_FOUR-> 24
            Days.TWENTY_FIVE -> 25
            Days.TWENTY_SIX -> 26
            Days.TWENTY_SEVEN-> 27
            Days.TWENTY_EIGHT -> 28
            Days.TWENTY_NINE -> 29
            else -> 30
        }
        return day
    }
    fun getWeekDay(nextDays: NextDays): String{
        val weekDay = when(nextDays.weekDays){
            WeekDays.SATURDAY -> "Saturday"
            WeekDays.SUNDAY -> "Sunday"
            WeekDays.MONDAY -> "Monday"
            WeekDays.TUESDAY -> "Tuesday"
            WeekDays.WEDNESDAY -> "Wednesday"
            WeekDays.THURSDAY -> "Thursday"
            WeekDays.FRIDAY -> "Friday"
        }
        return weekDay
    }
}