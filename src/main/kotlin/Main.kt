import kotlin.Char
import kotlin.collections.MutableList

fun main() {
    val rows = enterStringValues("Enter the number of rows:")
    val rowSeats = enterStringValues("Enter the number of seats in each row:")

    val cinemaSeatsData = getCinemaSeatsData(rows, rowSeats)

    cinemaSeatsRepresentation(rows, rowSeats, cinemaSeatsData)

    cinemaMenuDisplay(rows, rowSeats, cinemaSeatsData)

}

fun enterStringValues(enteringString: String): Int {
    println(enteringString)
    print("> ")

    val enteredString: String = readln()
    val enteredStringValue = enteredString.toInt()

    return enteredStringValue
}

fun getCinemaSeatsData(rows: Int, rowSeats: Int): List<MutableList<Char>> {
    val cinemaSeatsData = List(rows) { MutableList(rowSeats) { 'S' } }

    return cinemaSeatsData
}

fun buyCinemaTicket(rows: Int, rowSeats: Int, cinemaSeats: List<MutableList<Char>>) {
    val chosenRowNumber = enterStringValues("Enter a row number:")
    val chosenSeatNumber = enterStringValues("Enter a seat number in that row:")

    val checkRow = chosenRowNumber in 1..rows
    val checkSeat = chosenSeatNumber in 1..rowSeats
    val fullCheck = checkRow && checkSeat

    if (!fullCheck) {
        println("Wrong input! Numbers of row and seat are meaningless. Please choose another seat.")
        return
    }

    if (cinemaSeats[chosenRowNumber - 1][chosenSeatNumber - 1] == 'B') {
        println("That ticket has already been purchased! Please choose another seat.")
        return
    }

    print("Ticket price: ")
    print('$')
    println("${cinemaTicketPriceCalculation(rows, rowSeats, chosenRowNumber)}")


    changeCinemaSeatStatus(chosenRowNumber, chosenSeatNumber, cinemaSeats)
}

fun changeCinemaSeatStatus(chosenRowNumber: Int, chosenSeatNumber: Int, cinemaSeats: List<MutableList<Char>>) {
    cinemaSeats[chosenRowNumber - 1][chosenSeatNumber - 1] = 'B'
}

fun cinemaTicketPriceCalculation(rows: Int, rowSeats: Int, chosenRowNumber: Int): Int {
    val totalSeatsQuantity = rows * rowSeats
    val frontRows = rows / 2

    val cinemaTicketPrice = if (totalSeatsQuantity <= 60) { 10 } else { if (chosenRowNumber <= frontRows) 10 else 8 }

    return cinemaTicketPrice
}

fun cinemaSeatsRepresentation(rows: Int, rowSeats: Int, cinemaSeats: List<MutableList<Char>>) {
    println("Cinema:")

    print("  ")
    for (column in 1 .. rowSeats) {
        print("$column ")
    }

    println("")

    for (row in 1 .. rows) {
        print("$row ")

        for (n in cinemaSeats[row - 1].indices) {
            print(cinemaSeats[row - 1][n])
            print(" ")

        }
        println("")
    }
    println("")
}

fun cinemaIncomeCalculation(rows: Int, rowSeats: Int): Int {
    val frontRows = rows / 2
    val endRows = rows - frontRows

    val totalSeats = rows * rowSeats
    var calculatedCinemaIncome: Int

    if (totalSeats <= 60) {
        calculatedCinemaIncome = totalSeats * 10
    } else {
        val frontIncome = frontRows * rowSeats * 10
        val endIncome = endRows * rowSeats * 8

        calculatedCinemaIncome = frontIncome + endIncome
    }

    return calculatedCinemaIncome
}

fun showCinemaStatistics(rows: Int, rowSeats: Int, cinemaSeats: List<MutableList<Char>>) {
    var purchasedTickets = 0
    var currentIncome = 0

    for (row in 1..rows) {
        for (seat in 1..rowSeats) {
            if (cinemaSeats[row - 1][seat - 1] == 'B') {
                purchasedTickets++
                currentIncome += cinemaTicketPriceCalculation(rows, rowSeats, row)
            }
        }
    }

    val totalSeats = rows * rowSeats
    val calculatedCinemaIncome = cinemaIncomeCalculation(rows, rowSeats)
    val doubledPurchasedTickets = purchasedTickets.toDouble()
    val percentage = if (totalSeats > 0) doubledPurchasedTickets / totalSeats * 100 else 0.0
    val formatPercentage = "%.2f".format(percentage)

    println("Number of purchased tickets: $purchasedTickets")
    println("Percentage: ${formatPercentage}%")
    println("Current income: $$currentIncome")
    println("Total income: $$calculatedCinemaIncome")
}

fun cinemaMenuDisplay(rows: Int, rowSeats: Int, cinemaSeats: List<MutableList<Char>>) {

    while (true) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")

        print("> ")
        val menuOption = readln().toInt()

        when (menuOption) {
            1 -> cinemaSeatsRepresentation(rows, rowSeats, cinemaSeats)
            2 -> buyCinemaTicket(rows, rowSeats, cinemaSeats)
            3 -> showCinemaStatistics(rows, rowSeats, cinemaSeats)
            0 -> return
            else -> println("Wrong Option!")
        }
    }
}