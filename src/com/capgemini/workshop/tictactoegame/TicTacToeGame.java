package com.capgemini.workshop.tictactoegame;

import java.util.*;

public class TicTacToeGame {
	
	static char[] BOARD;
	static Scanner sc = new Scanner(System.in);
	static int INDEX;
	static char PLAYER = ' ';
	static char COMPUTER = ' ';
	static boolean HEAD, TAIL, IsPlayerTurn, IsComputerTurn;

	public static void main(String[] args) {
		while (true) {
			System.out.println("Welcome to TicTacToe Game");
			createBoard();
			int choice;
			while (true) {
				System.out.println("Enter your choice:\n1)Head\n2)Tail");
				choice = sc.nextInt();
				if (choice == 1 || choice == 2) {
					break;
				} else {
					System.out.println("Enter number corresponding to choice: ");
				}
			}
			Toss(choice);
			System.out.println("The player mark is: " + PLAYER);
			System.out.println("The computer mark is:" + COMPUTER);
			while (true) {
				System.out.println("Board");
				showBoard();
				if (IsPlayerTurn) {
					System.out.println("The Player's turn");
				} else {
					System.out.println("The Computer's turn");
				}
				selectIndex();
				showBoard();
				boolean isWin = IsWin();
				boolean isTie = IsTie();
				if (isWin) {
					if (IsPlayerTurn) {
						System.out.println("Player wins");
					} else {
						System.out.println("Computer wins");
					}
					break;
				} else if (isTie) {
					System.out.println("Tie!!");
					break;
				}
			}
			char gameChoice;
			while (true) {
				System.out.println("Do you want to play again?(y/n)");
				gameChoice = sc.next().charAt(0);
				if (gameChoice == 'y' || gameChoice == 'n') {
					break;
				}
				else {
					System.out.println("Enter y or n as input");
				}
			}
			if(gameChoice == 'n') {
				System.out.println("Thank you!!");
				break;
			}
		}
	}

	public static boolean IsTie() {
		boolean flag = true;
		int count = 0;
		for (int i = 1; i < 10; i++) {
			if (BOARD[i] != ' ') {
				count++;
			}
		}
		if (count == 9) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public static boolean IsWin() {
		if (IsPlayerTurn) {
			if ((PLAYER == BOARD[1] && PLAYER == BOARD[2] && PLAYER == BOARD[3])
					|| (PLAYER == BOARD[4] && PLAYER == BOARD[5] && PLAYER == BOARD[6])
					|| (PLAYER == BOARD[7] && PLAYER == BOARD[8] && PLAYER == BOARD[9])
					|| (PLAYER == BOARD[1] && PLAYER == BOARD[4] && PLAYER == BOARD[7])
					|| (PLAYER == BOARD[2] && PLAYER == BOARD[5] && PLAYER == BOARD[8])
					|| (PLAYER == BOARD[3] && PLAYER == BOARD[6] && PLAYER == BOARD[9])
					|| (PLAYER == BOARD[1] && PLAYER == BOARD[5] && PLAYER == BOARD[9])
					|| (PLAYER == BOARD[3] && PLAYER == BOARD[5] && PLAYER == BOARD[7])) {
				return true;
			}
		} else {
			if ((COMPUTER == BOARD[1] && COMPUTER == BOARD[2] && COMPUTER == BOARD[3])
					|| (COMPUTER == BOARD[4] && COMPUTER == BOARD[5] && COMPUTER == BOARD[6])
					|| (COMPUTER == BOARD[7] && COMPUTER == BOARD[8] && COMPUTER == BOARD[9])
					|| (COMPUTER == BOARD[1] && COMPUTER == BOARD[4] && COMPUTER == BOARD[7])
					|| (COMPUTER == BOARD[2] && COMPUTER == BOARD[5] && COMPUTER == BOARD[8])
					|| (COMPUTER == BOARD[3] && COMPUTER == BOARD[6] && COMPUTER == BOARD[9])
					|| (COMPUTER == BOARD[1] && COMPUTER == BOARD[5] && COMPUTER == BOARD[9])
					|| (COMPUTER == BOARD[3] && COMPUTER == BOARD[5] && COMPUTER == BOARD[7])) {
				return true;
			}
		}
		return false;
	}

	public static void selectIndex() {
		while (true) {
			if (IsPlayerTurn) {
				System.out.println("Enter the index from 1 to 9 where you want to place your move: ");
				INDEX = sc.nextInt();
				if (isFreeSpace(BOARD, INDEX) && (INDEX > 0) && (INDEX < 10)) {
					System.out.println("Its valid move");
					BOARD[INDEX] = PLAYER;
					if (IsWin()) {
						break;
					} else {
						IsPlayerTurn = false;
						IsComputerTurn = true;
						break;
					}
				} else {
					System.out.println("Already occupied! please select another index");
				}
			} else {
				int win = canIWin();
				if (win > 0) {
					BOARD[win] = COMPUTER;
					break;
				} else {
					int block = canIBlock();
					if (block > 0) {
						BOARD[block] = COMPUTER;
						IsPlayerTurn = true;
						IsComputerTurn = false;
						break;
					} else {
						int corner = selectCorner();
						BOARD[corner] = COMPUTER;
						IsPlayerTurn = true;
						IsComputerTurn = false;
						break;
					}
				}
			}
		}
	}

	public static boolean isFreeSpace(char[] board, int index) {
		return board[index] == ' ';
	}

	public static void createBoard() {
		BOARD = new char[10];
		for (int i = 1; i < 10; i++) {
			BOARD[i] = ' ';
		}
	}

	public static char playerInput() {
		char input;
		while (true) {
			System.out.println("Choose a letter 'X' or 'O':");
			input = sc.next().charAt(0);
			if (input == 'X' || input == 'O') {
				break;
			} else {
				System.out.println("Invalid letter!");
			}
		}
		return input;
	}

	public static void showBoard() {
		System.out.println(BOARD[1] + " | " + BOARD[2] + " | " + BOARD[3]);
		System.out.println("----------");
		System.out.println(BOARD[4] + " | " + BOARD[5] + " | " + BOARD[6]);
		System.out.println("----------");
		System.out.println(BOARD[7] + " | " + BOARD[8] + " | " + BOARD[9]);
	}

	public static void Toss(int choice) {
		int toss = (int) ((Math.random() * 10) % 2) +1;
		if (toss == choice) {
			HEAD = true;
			TAIL = false;
			System.out.println("Player wins and got the first turn");
		} else {
			HEAD = false;
			TAIL = true;
			System.out.println("Computer wins and got the first turn");
		}
		if (HEAD) {
			PLAYER = playerInput();
			IsPlayerTurn = true;
			IsComputerTurn = false;
			if (PLAYER == 'X') {
				COMPUTER = 'O';
			} else {
				COMPUTER = 'X';
			}
		} else {
			int select = (int) (Math.random() * 10) % 2;
			if (select == 0) {
				COMPUTER = 'X';
			} else {
				COMPUTER = 'O';
			}
			IsPlayerTurn = false;
			IsComputerTurn = true;
			if (COMPUTER == 'X') {
				PLAYER = 'O';
			} else {
				PLAYER = 'X';
			}
		}
	}

	public static int canIWin() {
		for (int i = 1; i < 10; i++) {
			if (BOARD[i] == ' ') {
				BOARD[i] = COMPUTER;
				if (IsWin()) {
					return i;
				} else {
					BOARD[i] = ' ';
				}
			}
		}
		return 0;
	}

	public static int canIBlock() {
		IsPlayerTurn = true;
		IsComputerTurn = false;
		for (int i = 1; i < 10; i++) {
			if (BOARD[i] == ' ') {
				BOARD[i] = PLAYER;
				if (IsWin()) {
					BOARD[i] = ' ';
					IsPlayerTurn = false;
					IsComputerTurn = true;
					return i;
				} else {
					BOARD[i] = ' ';
				}
			}
		}
		IsPlayerTurn = false;
		IsComputerTurn = true;
		return 0;
	}

	public static int selectCorner() {
		int corner = 5;
		if (isCornerAvailable()) {
			while (true) {
				corner = (int) (Math.random() * 10) % 10;
				if ((corner != 5) && (isFreeSpace(BOARD, corner))) {
					return corner;
				}
			}
		}
		return corner;
	}

	public static boolean isCornerAvailable() {
		for (int i = 1; i < 9; i++) {
			if (i == 5) {
				continue;
			} else if (BOARD[i] == ' ') {
				return true;
			}
		}
		return false;
	}
}
