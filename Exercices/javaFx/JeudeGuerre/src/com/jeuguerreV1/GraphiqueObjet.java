package com.jeuguerreV1;

import javafx.scene.Node;

public class GraphiqueObjet {
	private boolean alive = true;
	protected Node corps;

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isDead() {
		return !alive;
	}

	public Node getCorps() {
		return corps;
	}

	public void setCorps(Node corps) {
		this.corps = corps;
	}

	public boolean touch(GraphiqueObjet obj) {
		return corps.getBoundsInParent().intersects(obj.getCorps().getBoundsInParent());
	}
}
