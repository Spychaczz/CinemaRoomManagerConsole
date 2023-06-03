package cinema

import java.util.*
import kotlin.system.exitProcess
    var nrRows: Int = 0
    var seatsInRow: Int = 0
    var ticketSold: Int = 0
    var currIncome: Int = 0
    var totalIncome: Int = 0
fun main() {

    println("Enter the number of rows:")
    nrRows = readln().toInt()
    println("Enter the number of seats in each row:")
    seatsInRow = readln().toInt()
    println()
    var seatsList: MutableList<MutableList<Char>> = MutableList(nrRows) {
        MutableList(seatsInRow) { 'S' }
    }

    val uTicket = 8
    val nTicket = 10

    println("Total income:")
    if( nrRows*seatsInRow <= 60){ //small room
        totalIncome = nrRows * seatsInRow * nTicket
    }
    else{ // big room
        if((nrRows % 2) == 1){
            // odd nrRows
            totalIncome = ((nrRows / 2 + 1) * seatsInRow * uTicket + (nrRows / 2 ) * seatsInRow * nTicket)
        }
        else if(nrRows % 2 == 0){
            totalIncome = (nrRows / 2 * seatsInRow * nTicket + nrRows/2 * seatsInRow * uTicket)
        }

    }

    menu(seatsList)


}

fun showSeats(seatsList: MutableList<MutableList<Char>>) {
    println()
    println("Cinema:")
    print(" ")
    for(i in 1 .. seatsInRow) {
        print(" $i")
    }
    println()
    for(i in 0 until nrRows) {
        print(i+1)
        print(" ")
        println(seatsList[i].joinToString(" "))
    }
    println()
    menu(seatsList)
}
fun menu(seatsList: MutableList<MutableList<Char>>) {
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
    when (readln().toInt()){
        1 -> showSeats(seatsList)
        2 -> {
            buyTicket(seatsList)
        }
        3 -> statistics(seatsList)
        0 -> return//
        else -> exitProcess(1)
    }
}
fun buyTicket(seatsList: MutableList<MutableList<Char>>){
    println("Enter a row number:")
    val selectedRow = readln().toInt()
    println("Enter a seat number in that row:")
    val selectedNrInRow = readln().toInt()
    // try catch
    try{
        if(seatsList[selectedRow-1][selectedNrInRow-1] == 'B'){
            println("That ticket has already been purchased!")
            buyTicket(seatsList)
        }else{
            seatsList[selectedRow-1][selectedNrInRow-1] = 'B'
            ticketSold++
            var ticketPrice = 8
            if((seatsInRow * nrRows) <= 60){
                ticketPrice = 10
            }
            else if(selectedRow <= (nrRows/2)){
                ticketPrice = 10
            }

            println("Ticket price: $$ticketPrice")
            currIncome += ticketPrice
            //println()
            menu(seatsList)
        }
    }
    catch(e:IndexOutOfBoundsException){
        println("Wrong input!")
        return buyTicket(seatsList)
    }
}
fun statistics(seatsList: MutableList<MutableList<Char>>){
    val total = nrRows * seatsInRow
    //var percentage = ticketSold.toDouble()/(nrRows* seatsInRow)*100
    //var percentageFormat = "%.2f".format(percentage)
    var percentage: String = String.format(locale = Locale.ENGLISH, format ="%.2f", ticketSold.toDouble()/(nrRows* seatsInRow)*100)
    //targetSring: String = String.format(locale = Locale.ENGLISH, format=  "%.2f", percTicketsSold.toFloat())
    println()
    println("Number of purchased tickets: $ticketSold")
    println("Percentage: $percentage%")
    println("Current income: $$currIncome")
    println("Total income: $$totalIncome")
    println()
    menu(seatsList)
}
