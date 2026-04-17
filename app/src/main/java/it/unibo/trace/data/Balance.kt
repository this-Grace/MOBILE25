package it.unibo.trace.data

enum class BalanceStatus {
    RECEIVE,
    PAY,
    EVEN
}

data class PersonalBalanceMock(
    val amount: Double,
    val status: BalanceStatus
)

data class ParticipantBalanceMock(
    val id: String,
    val name: String,
    val amount: Double
)

val personalBalanceMock = PersonalBalanceMock(
    amount = 38.50,
    status = BalanceStatus.RECEIVE
)

val participantsBalanceMock = listOf(
    ParticipantBalanceMock(id = "you", name = "You", amount = 38.50),
    ParticipantBalanceMock(id = "maria", name = "Maria", amount = -22.00),
    ParticipantBalanceMock(id = "luca", name = "Luca", amount = -10.50),
    ParticipantBalanceMock(id = "anna", name = "Anna", amount = 0.00),
)
