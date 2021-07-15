package models;

public class Oval{

	public static final int PADDING = 20;
	private int x;
	private int y;
	private boolean alive;
	private boolean move;
	private int width;
	private int height;
	private int state;


	public Oval(int width, int height) {
		this.width = width - Constants.DIMENSION_OVAL - PADDING - PADDING;
		this.height = height - Constants.DIMENSION_OVAL - Constants.DIMENSION_OVAL - PADDING;
		x = (int) (Math.random() * this.width);
		y = (int) (Math.random() * this.height);
		alive = true;
		move = false;
		refreshState();
	}

	public void refreshState() {
		if(x == PADDING){
			if (y == PADDING) {
				state = 1;
			}else if (y == height) {
				state = 2;
			}
		}else if (x == width) {
			if (y == PADDING) {
				state = 3;
			}else if (y == height) {
				state = 4;
			}
		}else if(y == PADDING){

		}else if (y == height) {

		}else{
			state = 8;
		}

	}

	public void moveOval(){
		int number = 1 + (int) (Math.random() * 8);
		while (state != 1 && state != 2 && state != 3 && state != 4
				&& state != 5 && state != 6 && state != 7){
			System.out.println("ooooo");
			moveWhitOption(number);
			refreshState();
		}
	}

	public void move(int number, int numberTwo, int numberTree) {
		int count = 0;
		int aux = 0;
		if(x == PADDING){
			if (y == PADDING) {
				count = 1;
			}else if (y == height) {
				count = 2;
			}else{
				aux = 1;
			}
		}else if (x == width) {
			if (y == PADDING) {
				count = 3;
			}else if (y == height) {
				count = 4;
			}else{
				aux = 3;
			}
		}else if(y == PADDING){
			aux = 2;

		}else if (y == height) {
			aux = 4;
		}else{
			moveWhitOption(number);
		}
		createRandomOne(count, numberTree);
		createRandomTwo(aux, numberTwo);
	}

	private void moveWhitOption(int number) {
		switch (number) {
		case 1:
			moveNorthOest();
			break;
		case 2:
			moveNorthEast();
			break;
		case 3:
			moveSouthOest();
			break;
		case 4:
			moveSouthEast();
			break;
		case 5:
			moveOest();
			break;
		case 6:
			moveEast();
			break;
		case 7:
			moveNorth();
			break;
		case 8:
			moveSouth();
			break;
		default:
			break;
		}
	}

	private void createRandomOne(int count, int numberTree){
		if (numberTree == 1) {
			if (count == 1 || count == 2) {
				moveEast();
			}else if (count == 3 || count == 4) {
				moveOest();
			}
		}else if(numberTree == 2){
			if (count == 2 || count == 4) {
				moveNorth();
			}else if (count == 1 || count == 3) {
				moveSouth();
			}
		}else{
			if (count == 1) {
				moveSouthEast();
			}else if (count == 2) {
				moveNorthEast();
			}else if (count == 3) {
				moveSouthOest();
			}else{
				moveNorthOest();
			}
		}
	}

	private void createRandomTwo(int auxCont, int numberTwo){
		switch (numberTwo) {
		case 1:
			if (auxCont == 1 || auxCont == 2 || auxCont == 3) {
				moveSouth();
			}else if (auxCont == 4) {
				moveNorth();
			}
			break;
		case 2:
			if (auxCont == 2 ) {
				moveSouth();
			}else if (auxCont == 1 || auxCont == 3 || auxCont == 4) {
				moveNorth();
			}
			break;
		case 3:
			if (auxCont == 1 || auxCont == 4 ) {
				moveNorthEast();
			}else if (auxCont == 3 || auxCont == 2) {
				moveSouthOest();
			}
			break;
		case 4:
			if (auxCont == 1 || auxCont == 2 || auxCont == 4) {
				moveEast();
			}else if ( auxCont == 3) {
				moveOest();
			}
			break;
		case 5:
			if (auxCont == 1 || auxCont == 2 ) {
				moveSouthEast();
			}else if (auxCont == 3 || auxCont == 4) {
				moveNorthOest();
			}
			break;

		default:
			break;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isMove() {
		return move;
	}
	public void die() {	
		alive = false;
	}

	public boolean isAlive() {
		return alive;
	}
	public void setMove(boolean move) {
		this.move = move;
	}

	public void moveNorth(){
		if (y > PADDING) {
			y--;
		}else{

		}
	}

	public void moveSouth(){
		if (y < height) {
			y++;
		}
	}

	public void moveEast(){
		if (x < width) {
			x++;
		}
	}

	public void moveSouthEast(){
		if (x < width && y < height) {
			x++;
			y++;
		}
	}

	public void moveSouthOest(){
		if (x > PADDING && y < height) {
			x--;
			y++;
		}
	}

	public void moveNorthEast(){
		if (x < width && y > PADDING) {
			x++;
			y--;
		}
	}

	public void moveNorthOest(){
		if (x > PADDING && y > PADDING) {
			x--;
			y--;
		}
	}
	public void moveOest(){
		if (x > PADDING) {
			x--;
		}
	}
}