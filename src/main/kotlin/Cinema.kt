import kotlin.Char
import kotlin.collections.MutableList


class Cinema() {
    private val rows: Int = enterStringValues("Enter the number of rows:")
    private val rowSeats: Int = enterStringValues("Enter the number of seats in each row:")

    private val cinemaSeats: List<MutableList<Char>> = getCinemaSeatsData()
    private val totalSeatsQuantity: Int = rows * rowSeats

    private fun enterStringValues(enteringString: String): Int {
        println(enteringString)
        print("> ")

        val enteredString: String = readln()
        val enteredStringValue = enteredString.toInt()

        return enteredStringValue
    }

    private fun getCinemaSeatsData(): List<MutableList<Char>> {
        val cinemaSeatsData = List(rows) { MutableList(rowSeats) { 'S' } }

        return cinemaSeatsData
    }

    private fun buyCinemaTicket() {
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
        println("${cinemaTicketPriceCalculation(chosenRowNumber)}")


        changeCinemaSeatStatus(chosenRowNumber, chosenSeatNumber)
    }

    private fun changeCinemaSeatStatus(chosenRowNumber: Int, chosenSeatNumber: Int) {
        cinemaSeats[chosenRowNumber - 1][chosenSeatNumber - 1] = 'B'
    }

    private fun cinemaTicketPriceCalculation(chosenRowNumber: Int): Int {
        val frontRows = rows / 2

        val cinemaTicketPrice = if (totalSeatsQuantity <= 60) { 10 } else { if (chosenRowNumber <= frontRows) 10 else 8 }

        return cinemaTicketPrice
    }

    private fun cinemaSeatsRepresentation() {
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

    private fun cinemaIncomeCalculation(): Int {
        val frontRows = rows / 2
        val endRows = rows - frontRows

        var calculatedCinemaIncome: Int

        if (totalSeatsQuantity <= 60) {
            calculatedCinemaIncome = totalSeatsQuantity * 10
        } else {
            val frontIncome = frontRows * rowSeats * 10
            val endIncome = endRows * rowSeats * 8

            calculatedCinemaIncome = frontIncome + endIncome
        }

        return calculatedCinemaIncome
    }

    private fun showCinemaStatistics() {
        var purchasedTickets = 0
        var currentIncome = 0

        for (row in 1..rows) {
            for (seat in 1..rowSeats) {
                if (cinemaSeats[row - 1][seat - 1] == 'B') {
                    purchasedTickets++
                    currentIncome += cinemaTicketPriceCalculation(row)
                }
            }
        }

        val calculatedCinemaIncome = cinemaIncomeCalculation()
        val doubledPurchasedTickets = purchasedTickets.toDouble()
        val percentage = if (totalSeatsQuantity > 0) doubledPurchasedTickets / totalSeatsQuantity * 100 else 0.0
        val formatPercentage = "%.2f".format(percentage)

        println("Number of purchased tickets: $purchasedTickets")
        println("Percentage: ${formatPercentage}%")
        println("Current income: $$currentIncome")
        println("Total income: $$calculatedCinemaIncome")
    }

    fun cinemaMenuDisplay() {

        while (true) {
            println("1. Show the seats")
            println("2. Buy a ticket")
            println("3. Statistics")
            println("0. Exit")

            print("> ")
            val menuOption = readln().toInt()

            when (menuOption) {
                1 -> cinemaSeatsRepresentation()
                2 -> buyCinemaTicket()
                3 -> showCinemaStatistics()
                0 -> return
                else -> println("Wrong Option!")
            }
        }
    }
}