package checkers;

import java.util.LinkedList;

public class CheckersMap {
	private LinkedList<Piece> blacks = new LinkedList<Piece>();
	private LinkedList<Piece> whites = new LinkedList<Piece>();
	private int scorePlayer1 = 0;
	private int scorePlayer2 = 0;
	private Piece[][] map = new Piece[8][8];
	// 1 = negras, 2 = blancasIA
	
	public CheckersMap() {
		setStartPosition();
	}

	public CheckersMap(CheckersMap m) {
		this.blacks = new LinkedList<Piece>();
		this.whites = new LinkedList<Piece>();
		this.scorePlayer1 = m.scorePlayer1;
		this.scorePlayer2 = m.scorePlayer2;
		
		for (int  i = 0; i < 8; i++) {
			for(int j = 0; j < 8 ; j++) {
				if (m.map[i][j] != null) {
					this.map[i][j] = new Piece(m.getMap()[i][j]);
					if (this.map[i][j].getType() == 2) this.whites.add(this.map[i][j]);
					else blacks.add(this.map[i][j]);
				}
			}
		}
	}

	void showMap() {
		for (int i = 0; i < map.length; i++) {
			System.out.print(i + "->");
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] != null) {
					System.out.print("|"+map[i][j].getType());
				} else {
					System.out.print("| ");
				}
			}
			System.out.println("|");
		}
		
		System.out.println("    0 1 2 3 4 5 6 7\n");
		System.out.println("Score Player 1: " + scorePlayer1);
		System.out.println("Score Player 2: " + scorePlayer2);
	}
	
	Piece getBlackPiece(int x, int y) {
		Piece d = map[x][y];
		if (d != null) {
			if (d.getType() == 2) {
				return null;
			}
		}
		return d;
	}

	Piece getWhitePiece(int x, int y) {
		Piece d = map[x][y];
		if (d != null) {
			if (d.getType() == 1) {
				return null;
			}
		}
		return d;
	}
	
	void eatPiece(Piece eaten, Piece eater) {
		for (int i = 0; i < map.length; i++)
			for (int j= 0; j < map.length; j++)
				if (map[i][j] != null) {
					if(map[i][j].getId() == eaten.getId()) {
						map[i][j] = null;		
					}
				} 
		
		 
		 if (eater.getType() == 1) {
			scorePlayer1++;	
			whites.remove(eaten);
		 } else {
			scorePlayer2++;
			blacks.remove(eaten);
		}
	}
	
	boolean movePiece(Piece d) {
		int x = d.getX();		
		int y = d.getY();
		
		if (MoveChecker.checkMovement(d.getMovement(), map)) {
			int nx = d.getMovement().getGoX();
			int ny = d.getMovement().getGoY();
			map[x][y] = null;
			map[nx][ny] = d;
			
			if ( (d.getType() == 1 && nx == 0) || (d.getType() == 2 && nx == 7) ) {
				d.setKing(true);
			}
			
			d.setX(nx);
			d.setY(ny);
			
			if(d.getMovement().isEatMovement()) {
				eatPiece(d.getMovement().getEatedPiece(), d);
			}
			return true;
		} else {
			System.out.println("Wrong move. Try again!");
			return false;
		}
		
	}
	
	void setStartPosition() {
		int id = 0;
		for (int i = 0; i < map.length; i++) {
			if (i%2 == 0) {
				Piece d = new Piece(0, i);
				map[0][i] = d;
				d.setType(2);
				d.setId(id++); 
				whites.addLast(d);
			}
				
		}

		for (int i = 0; i < map.length; i++) {
			if (i%2 != 0) {
				Piece d = new Piece(1, i);
				map[1][i] = d;
				d.setType(2); 
				d.setId(id++); 
				whites.addLast(d);

			}
		}
		
		for (int i = 0; i < map.length; i++) {
			if (i%2 == 0) {
				Piece d = new Piece(2, i);
				map[2][i] = d;
				d.setType(2); 
				d.setId(id++); 
				whites.addLast(d);
			}
		}
		

		for (int i = 0; i < map.length; i++) {
			if (i%2 != 0) {
				Piece d = new Piece(5, i);
				map[5][i] = d;
				d.setType(1); 
				d.setId(id++); 
				blacks.addLast(d);
				
			}
		}
	
		for (int i = 0; i < map.length; i++) {
			if (i%2 == 0) {
				Piece d = new Piece(6, i);
				map[6][i] = d;			
				d.setType(1);
				d.setId(id++);  
				blacks.addLast(d);
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			if (i%2 != 0) {
				Piece d = new Piece(7, i);
				map[7][i] = d;
				d.setType(1); 
				d.setId(id++); 
				blacks.addLast(d);
			}
		}			
	}

	public LinkedList<Piece> getBlacks() {
		return blacks;
	}

	public void setBlacks(LinkedList<Piece> blacks) {
		this.blacks = blacks;
	}

	public LinkedList<Piece> getWhites() {
		return whites;
	}

	public void setWhites(LinkedList<Piece> whites) {
		this.whites = whites;
	}

	public Piece[][] getMap() {
		return map;
	}

	public void setMap(Piece[][] map) {
		this.map = map;
	}

}
