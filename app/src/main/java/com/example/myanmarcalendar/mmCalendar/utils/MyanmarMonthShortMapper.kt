package com.example.myanmarcalendar.mmCalendar.utils

object MyanmarMonthShortMapper {
    private const val pyarTho = "သို"
    private const val taPohTwel = "တွဲ"
    private const val taPaung = "ပေါင်း"
    private const val naungTagu = "နှောင်းခူး"
    private const val tagu = "ခူး"
    private const val kaSone = "ဆုန်"
    private const val naYone = "ယုန်"
    private const val warSo = "ဆို"
    private const val paWarSo = "ပဆို"
    private const val duWarSo = "ဒုဆို"
    private const val warGaung = "ခေါင်"
    private const val tawTaLin = "လင်း"
    private const val taTinKyaut = "ကျွတ်"
    private const val taSaungMone = "မုန်း"
    private const val naTaw = "တော်"
    fun shortMap(mmMonth: String): String {
        return when {
            mmMonth.contains(pyarTho) -> {
                pyarTho
            }
            mmMonth.contains(taPohTwel) -> {
                taPohTwel
            }
            mmMonth.contains(taPaung) -> {
                taPaung
            }
            mmMonth.contains(naungTagu) -> {
                naungTagu
            }
            mmMonth.contains(tagu) -> {
                return if(mmMonth.contains(naungTagu)){
                    naungTagu
                }else{
                    tagu
                }
            }
            mmMonth.contains(kaSone) -> {
                kaSone
            }
            mmMonth.contains(naYone) -> {
                naYone
            }
            mmMonth.contains(warSo) -> {
                return when{
                    mmMonth.contains("ပ") -> paWarSo
                    mmMonth.contains("ဒု") -> duWarSo
                    else -> warSo
                }
            }

            mmMonth.contains(warGaung) -> {
                warGaung
            }
            mmMonth.contains(tawTaLin) -> {
                tawTaLin
            }
            mmMonth.contains(taTinKyaut) -> {
                taTinKyaut
            }
            mmMonth.contains(taSaungMone) -> {
                taSaungMone
            }
            else -> {
                naTaw
            }
        }
    }
}