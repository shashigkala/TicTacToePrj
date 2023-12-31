package com.ttt.tdd;

import com.ttt.constants.TicTacToeConstants;
import com.ttt.exceptions.TicTacToeException;

public class TicTacToe {

	private Character[][] board = { { '\0', '\0', '\0' }, { '\0', '\0', '\0' }, { '\0', '\0', '\0' } };
	private char lastPlayer = '\0';

	public String play(int column, int row) {
		checkAxis(column, TicTacToeConstants.ERROR_MSG_OF_X_VALUE_IF_OUTSIDE_THE_BOARD);
		checkAxis(row, TicTacToeConstants.ERROR_MSG_OF_Y_VALUE_IF_OUTSIDE_THE_BOARD);
		lastPlayer = nextPlayer();
		setField(column, row, TicTacToeConstants.FIELD_IS_OCCUPIED, lastPlayer);
		if (isWinner()) {
			return lastPlayer + " is the Winner";
		} else if (isDraw()) {
			return TicTacToeConstants.ALL_FIELDS_ARE_FILLED_SO_ITS_DRAW;
		} else {
			return TicTacToeConstants.NO_WINNER;
		}
	}

	public char nextPlayer() {
		if (lastPlayer == 'X') {
			return 'O';
		}
		return 'X';

	}

	private void setField(int column, int row, String msg, char lastPlayer) {
		if (board[column - 1][row - 1] != '\0') {
			throw new TicTacToeException(msg);
		} else {
			board[column - 1][row - 1] = lastPlayer;
		}

	}

	private void checkAxis(int axis, String message) {
		if (axis < 1 || axis > 3) {
			throw new TicTacToeException(message);
		}
	}

	private boolean isWinner() {
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == lastPlayer && board[1][i] == lastPlayer && board[2][i] == lastPlayer
					|| board[i][0] == lastPlayer && board[i][1] == lastPlayer && board[i][2] == lastPlayer) {
				return true;
			} else if (board[0][0] == lastPlayer && board[1][1] == lastPlayer && board[2][2] == lastPlayer
					|| board[2][0] == lastPlayer && board[1][1] == lastPlayer && board[0][2] == lastPlayer)
				return true;

		}
		return false;
	}

	private boolean isDraw() {
		for (int x = 0; x < TicTacToeConstants.SIZE; x++) {
			for (int y = 0; y < TicTacToeConstants.SIZE; y++) {
				if (board[x][y] == '\0') {
					return false;
				}
			}
		}
		return true;
	}

}
