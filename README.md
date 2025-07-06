# Blackjack Java Assignment

This project is a full implementation of the Blackjack (21) card game in Java, based on the university assignment requirements.

## 🎮 Features

- Supports multiple players and dealer logic
- Handles blackjack, bust, split, and double actions
- Tracks player money and round results
- Uses multiple decks with automatic reshuffling

## 🗂 File Structure

- `Card.java` – Represents a single playing card
- `River.java` – Manages the deck(s) of cards
- `Hand.java` – Represents a player's or dealer's hand
- `CasinoCustomer.java` – Models a player’s identity and wallet
- `Player.java` – Handles a player’s round logic and actions
- `Dealer.java` – Simulates the dealer's turn and resolves outcomes
- `Round.java` – Runs a full round between dealer and players
- `BlackjackTable.java` – Sets up the table and game loop
- `Blackjack.java` – Entry point with the `main` method

## 🛠 How to Compile & Run

### 1. Open a terminal and navigate to the project folder:

```bash
cd path/to/BlackjackProject
