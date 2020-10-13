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

		System.out.println("Welcome to TicTacToe Game");
		createBoard();
		Toss();
		char input = playerInput();
		if (HEAD) {
			PLAYER = input;
			IsPlayerTurn = true;
			IsComputerTurn = false;
			if (PLAYER == 'X') {
				COMPUTER = 'O';
			} else {
				COMPUTER = 'X';
			}
		} else {
			COMPUTER = input;
			IsPlayerTurn = false;
			IsComputerTurn = true;
			if (COMPUTER == 'X') {
				PLAYER = 'O';
			} else {
				PLAYER = 'X';
			}
		}
		while (true) {
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
			if (isWin || isTie) {
				if (isTie) {
					System.out.println("Tie!!");
				}
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
				System.out.println("Player wins");
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
					|| (COMPUTER == BOARD[2] && COMPUTER == BOARD[5] && COMPUTER == BOARD[7])) {
				System.out.println("computer wins");
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
					IsPlayerTurn = false;
					IsComputerTurn = true;
					break;
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
					if(block>0) {
						BOARD[block] = COMPUTER;
						IsPlayerTurn = true;
						IsComputerTurn = false;
						break;
					}
					else {
					System.out.println("There is no position to block and win!!");
					System.out.println("Enter the index from 1 to 9 where you want to place your move: ");
					INDEX = sc.nextInt();
					if (isFreeSpace(BOARD, INDEX) && (INDEX > 0) && (INDEX < 10)) {
						System.out.println("Its valid move");
						BOARD[INDEX] = COMPUTER;
						IsPlayerTurn = true;
						IsComputerTurn = false;
						break;
					} else {
						System.out.println("Already occupied! please select another index");
					}}
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
		System.out.println("Choose a letter 'X' or 'O' as input:");
		char input = sc.next().charAt(0);
		return input;
	}

	public static void showBoard() {
		System.out.println(BOARD[1] + " | " + BOARD[2] + " | " + BOARD[3]);
		System.out.println("----------");
		System.out.println(BOARD[4] + " | " + BOARD[5] + " | " + BOARD[6]);
		System.out.println("----------");
		System.out.println(BOARD[7] + " | " + BOARD[8] + " | " + BOARD[9]);
	}

	public static void Toss() {
		int toss = (int) (Math.random() * 10) % 2;
		if (toss == 0) {
			HEAD = true;
			TAIL = false;
		} else {
			HEAD = false;
			TAIL = true;
		}
		if (HEAD) {
			System.out.println("Player got the first turn");
		} else {
			System.out.println("Computer got the first turn");
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
		for(int i=1;i<10;i++) {
			if(BOARD[i] == ' ') {
				BOARD[i] = PLAYER;
				if(IsWin()) {
					BOARD[i] =' ';
					IsPlayerTurn = false;
					IsComputerTurn = true;
					return i;
				}
				else {
					BOARD[i] =' ';
				}
			}
		}
		IsPlayerTurn = false;
		IsComputerTurn = true;
		return 0;
	}
}
