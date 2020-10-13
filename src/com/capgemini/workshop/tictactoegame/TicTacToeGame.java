package com.capgemini.workshop.tictactoegame;

public class TicTacToeGame {
	
	static char[] BOARD;
	public static void main(String[] args) {
		System.out.println("Welcome to TicTacToe game");
		createBoard();
	}
	public static void createBoard() {
		BOARD= new char[10];
		for(int i=1;i<10;i++) {
			BOARD[i] = ' ';
		}
	}

}
