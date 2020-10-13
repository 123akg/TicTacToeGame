package com.capgemini.workshop.tictactoegame;

import java.util.*;

public class TicTacToeGame {
	
	static char[] BOARD;
	static Scanner sc = new Scanner(System.in);
	static int INDEX;
	public static void main(String[] args) {
		char player = ' ';
		char computer = ' ';
		System.out.println("Welcome to TicTacToe Game");
		createBoard();
		char input = playerInput();
		player = input;
		if (input == 'X') {
			computer = 'O';
		} else {
			computer = 'X';
		}
		System.out.println("Player mark: " + player);
		System.out.println("Computer mark: " + computer);
		showBoard();
		System.out.println("Enter the index from 1 to 9 where you want to place your move: ");
		INDEX = sc.nextInt();
		selectIndex(INDEX);
		showBoard();
	}
	public static void selectIndex(int index) {
		if(BOARD[index] == ' ') {
			System.out.println("Its valid move");
			BOARD[index] = 'X';
		}
		else {
			System.out.println("Already occupied! please select another index");
		}
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
		System.out.println(BOARD[1]+" | "+BOARD[2]+" | "+BOARD[3]);
		System.out.println("----------");
		System.out.println(BOARD[4]+" | "+BOARD[5]+" | "+BOARD[6]);
		System.out.println("----------");
		System.out.println(BOARD[7]+" | "+BOARD[8]+" | "+BOARD[9]);
	}
}
