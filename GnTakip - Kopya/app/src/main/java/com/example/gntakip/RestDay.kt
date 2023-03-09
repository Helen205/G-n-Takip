package com.example.gntakip


class RestDay() {

    companion object {
        var remainingDay: Int = 30
        fun GetRemainingRestDay(): Int {
            return remainingDay
        }

        fun SetRemainingRestDay( day: Int ) {
            remainingDay = day
        }

        fun CalculateRemainingRestDay(requestedRestDay: Int) {
            //burada kalan izin gününden izin alınan günü çıkarıyorum
            remainingDay -= requestedRestDay
        }
    }
}
