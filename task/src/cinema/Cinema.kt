package cinema

import kotlin.system.exitProcess

var rows = 0
var seatsInRow = 0
val cinema = mutableListOf<MutableList<Char>>()

fun main() {

    println("Enter the number of rows:")
    rows = readln().toInt()
    println("Enter the number of seats in each row:")
    seatsInRow = readln().toInt()
    println()
    for (i in 0 until rows) {
        cinema.add(MutableList(seatsInRow) { 'S' })
    }

    while (true) {
        println(
            """
            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
        """.trimIndent()
        )

        when (readln().toInt()) {
            1 -> printSeats(cinema)
            2 -> buyTicket()
            3 -> statistics()
            0 -> break
        }
        println()
    }

}

fun statistics() {
    var purchasedTickets = 0
    var income = 0
    var total = 0
    for (i in cinema.indices) {
        for (y in cinema[i].indices) {
            if (cinema[i][y] == 'B') {
                purchasedTickets++
                income += calc(i + 1, y + 1)
            }
            total += calc(i + 1, y + 1)
//            print(" $total")
        }
    }
    val perc: Double = purchasedTickets * 100 / (cinema.size * cinema[0].size).toDouble()

    println()
    println("Number of purchased tickets: $purchasedTickets")
    println("Percentage: " + "%.2f".format(perc.toDouble()) + "%")
    println("Current income: $$income")
    println("Total income: $$total")
}

fun buyTicket() {

    var rNum = 0
    var sNum = 0
    while (true) {
        println()
        println("Enter a row number:")
        rNum = readln().toInt()
        println("Enter a seat number in that row:")
        sNum = readln().toInt()

        try {
            if (cinema[rNum - 1][sNum - 1] == 'B') {
                println()
                println("That ticket has already been purchased!")
                continue
            }
        } catch (e: Exception) {
            println()
            println("Wrong input!")
            continue
        }
        break
    }
    println()
    println("Ticket price: $${calc(rNum, sNum)}")
    cinema[rNum - 1][sNum - 1] = 'B'
}

fun calc(row: Int, seat: Int): Int {
    var price = 0
    price = if (rows * seatsInRow <= 60) {
        10
    } else {
        if (row <= rows / 2) {
            10
        } else {
            8
        }
    }
    return price
}

fun printSeats(list: MutableList<MutableList<Char>>) {
    println()
    println("Cinema:")
    print(" ")
    for (i in 1..list.first().size) {
        print(" $i")
    }
    println()
    for (r in 1..list.size) {
        print("$r")
        for (i in 0 until list[r - 1].size) {
            print(" ${list[r - 1][i]}")
        }
        println()
    }
}