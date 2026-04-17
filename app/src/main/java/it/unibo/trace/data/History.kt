package it.unibo.trace.data

data class ExpenseMock(
    val id: String,
    val title: String,
    val category: String,
    val paidBy: String,
    val amount: Double,
    val date: String,
    val iconType: String,
    val lat: Double,
    val lon: Double
)

val historyMockup = listOf(
    ExpenseMock(
        id = "1",
        title = "Sushiro Shinjuku",
        category = "Cena di gruppo",
        paidBy = "you",
        amount = 42.00,
        date = "Oggi, 14 Ottobre",
        iconType = "restaurant",
        lat = 35.6905,
        lon = 139.7049
    ),
    ExpenseMock(
        id = "2",
        title = "Abbonamento metro",
        category = "Trasporti",
        paidBy = "Maria",
        amount = 22.50,
        date = "Oggi, 14 Ottobre",
        iconType = "subway",
        lat = 35.6812,
        lon = 139.7671
    ),
    ExpenseMock(
        id = "3",
        title = "Carte Pokemon",
        category = "Souvenir",
        paidBy = "you",
        amount = 85.00,
        date = "Ieri, 13 Ottobre",
        iconType = "shopping",
        lat = 35.6987,
        lon = 139.7712
    ),
    ExpenseMock(
        id = "4",
        title = "Carte Pokemon",
        category = "Souvenir",
        paidBy = "you",
        amount = 85.00,
        date = "Ieri, 13 Ottobre",
        iconType = "shopping",
        lat = 35.7131,
        lon = 139.7741
    ),
    ExpenseMock(
        id = "5",
        title = "Carte Pokemon",
        category = "Souvenir",
        paidBy = "you",
        amount = 85.00,
        date = "Ieri, 13 Ottobre",
        iconType = "shopping",
        lat = 35.6605,
        lon = 139.6975
    ),
    ExpenseMock(
        id = "6",
        title = "Carte Pokemon",
        category = "Souvenir",
        paidBy = "you",
        amount = 85.00,
        date = "Ieri, 13 Ottobre",
        iconType = "shopping",
        lat = 35.6264,
        lon = 139.7758
    ),
    ExpenseMock(
        id = "7",
        title = "Carte Pokemon",
        category = "Souvenir",
        paidBy = "you",
        amount = 85.00,
        date = "Ieri, 13 Ottobre",
        iconType = "shopping",
        lat = 34.6649,
        lon = 135.5013
    ),
    ExpenseMock(
        id = "9",
        title = "Carte Pokemon",
        category = "Souvenir",
        paidBy = "you",
        amount = 85.00,
        date = "Ieri, 13 Ottobre",
        iconType = "shopping",
        lat = 34.9858,
        lon = 135.7588
    ),
    ExpenseMock(
        id = "10",
        title = "Carte Pokemon",
        category = "Souvenir",
        paidBy = "you",
        amount = 85.00,
        date = "Ieri, 13 Ottobre",
        iconType = "shopping",
        lat = 35.4437,
        lon = 139.6380
    )
)