# Cinema Seat Reservation System

A Kotlin-based console application for managing cinema seat reservations, ticket purchases, and statistics.
- Project source: https://hyperskill.org/projects/138/

## Features

- **Seat Visualization**: Display cinema seating arrangement with available and occupied seats
- **Ticket Purchasing**: Buy tickets with automatic price calculation
- **Smart Pricing**: 
  - $10 for all seats if total capacity ≤ 60
  - $10 for front half rows, $8 for back half rows for larger cinemas
- **Statistics**: Track purchased tickets, current income, and occupancy percentage
- **Input Validation**: Prevent invalid seat selections and double bookings

## Menu Options

- **1.Show the seats** - Display current seating arrangement
- **2.Buy a ticket** - Purchase a ticket for a specific seat
- **3.Statistics** - View sales and occupancy statistics
- **0.Exit** - Close the application

## Example Session
```text
Enter the number of rows:
> 5
Enter the number of seats in each row:
> 5

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 2

Enter a row number:
> 3
Enter a seat number in that row:
> 3
Ticket price: $10

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 1

Cinema:
  1 2 3 4 5
1 S S S S S 
2 S S S S S 
3 S S B S S 
4 S S S S S 
5 S S S S S


1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 3

Number of purchased tickets: 1
Percentage: 4.00%
Current income: $10
Total income: $250
```
