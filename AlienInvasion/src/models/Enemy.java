package models;

import views.Constants;

public class Enemy {

	private static final int LIMIT_CRACKED_EGG = 80;
	private static final int LIMIT_ALIEN = 160;
	private static final int PERCENTAGE_TOTAL = 100;
	private static final int PERCENTAGE_ADD_HEIGHT = 70;
	private static final int PERCENTAGE_ADD_WIDTH = 25;
	private static final int PERCENTAGE_LIMIT = 95;
	private static final int SPACE_MIN_Y = 100;
	private int x;
	private int y;
	private boolean alive;
	private int generationWidth;
	private int generationHeight;
	private int moveLimit;
	private int count;
	private EstateEnemy state;
	private EstateEnemy stateMove;

	public Enemy(int height, int width){
		this.generationWidth = ((int) (width * PERCENTAGE_ADD_WIDTH) / PERCENTAGE_TOTAL) - Constants.DIMENSION_OVAL;
		this.generationHeight = ((int) (height * PERCENTAGE_ADD_HEIGHT) / PERCENTAGE_TOTAL) - Constants.DIMENSION_OVAL;
		this.moveLimit = (width * PERCENTAGE_LIMIT) / PERCENTAGE_TOTAL;
		x =  (int) (Math.random() * generationWidth);
		y = SPACE_MIN_Y + (int) (Math.random() * generationHeight);
		count = 0;
		state = EstateEnemy.EGG;
		alive = true;
		stateMove = EstateEnemy.ALIEN_1;
	}

	public boolean isAlive() {
		return alive;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void die(){
		if (state == EstateEnemy.EGG || state == EstateEnemy.CRACKED_EGG) {
			state = EstateEnemy.EGG_DIE;
		}else if (state == EstateEnemy.ALIEN) {
			state = EstateEnemy.ALIEN_DEAD;
		}
		this.alive = false;
	}

	public void move() {
		if (state == EstateEnemy.ALIEN) {
			moveEast(2);
			if (stateMove == EstateEnemy.ALIEN_1) {
				stateMove = EstateEnemy.ALIEN_2;
			}else {
				stateMove = EstateEnemy.ALIEN_1;
			}
		}else {
			moveEast(1);
		}
	}

	public EstateEnemy getStateMove() {
		return stateMove;
	}
	
	public void moveEast(int n){
		if (x <= moveLimit) {
			x += n;
			count += n;
			if (count == LIMIT_CRACKED_EGG) {
				state = EstateEnemy.CRACKED_EGG;
			}else if(count == LIMIT_ALIEN){
				state = EstateEnemy.ALIEN;
			}
		}else {
			state = EstateEnemy.GAME_OVER;
		}
	}

	public void setState(EstateEnemy state) {
		this.state = state;
	}

	public EstateEnemy getState() {
		return state;
	}
}
