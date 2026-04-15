package it.unibo.trace.data

data class ProfileMock(
    val username: String,
    val iban: String,
    val paypalAccount: String,
    val totalExpenses: String,
    val totalTrips: String
)

val profileMock = ProfileMock(
    username = "Marco_01",
    iban = "IT 60 X 05424 03200 000000123456",
    paypalAccount = "@marco_01",
    totalExpenses = "€4.280",
    totalTrips = "12"
)