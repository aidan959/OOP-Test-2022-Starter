package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NematodeVisualiser extends PApplet {

	public void keyPressed() {
		if (changingNematode) {

		} else if (keyCode == LEFT) {
			previousNematode = currentNematode;
			currentNematode--;
			if (currentNematode < 0) {
				currentNematode = nematodes.size() - 1;
			}
			changingNematode = true;
			changeFrame = true;
		} else if (keyCode == RIGHT) {
			previousNematode = currentNematode;
			currentNematode++;
			if (currentNematode > nematodes.size() - 1) {
				currentNematode = 0;
			}
			changingNematode = true;
			changeFrame = true;

		}
	}

	public void settings() {
		size(800, 800);
	}

	public void setup() {
		colorMode(HSB);
		background(0);
		smooth();
		loadNematodes();
	}

	ArrayList<Nematode> nematodes;
	int currentNematode;
	int previousNematode;
	boolean changeFrame;

	public void loadNematodes() {
		nematodes = new ArrayList<>();
		Table table;
		table = loadTable("data/nematodes.csv", "header");

		for (TableRow row : table.rows()) {
			nematodes.add(new Nematode(row, this));

		}

		for (Nematode nematode : nematodes) {
			System.out.println(nematode);
		}
	}

	boolean changingNematode;

	public void draw() {
		clear();
		Nematode nematode = nematodes.get(currentNematode);
		Nematode previousNema = null;
		if (changingNematode) {
			previousNema = nematodes.get(previousNematode);
			if (changeFrame) {
				changeFrame = false;
				if (currentNematode > previousNematode) {
					nematode.setMovementX(width / 2);
					previousNema.setMovementX(0);
				} else {
					nematode.setMovementX(-width / 2);
					previousNema.setMovementX(0);

				}
			}
			if (currentNematode > previousNematode) {
				nematode.setMovementX(nematode.getMovementX() - 5);
				previousNema.setMovementX(previousNema.getMovementX() - 5);

				if (previousNema.getMovementX() < -width / 2) {
					nematode.setMovementX(0);
					changingNematode = false;
				}
			} else {
				nematode.setMovementX(nematode.getMovementX() + 5);
				previousNema.setMovementX(previousNema.getMovementX() + 5);

				if (previousNema.getMovementX() > width / 2) {
					nematode.setMovementX(0);

					changingNematode = false;
				}

			}
		}
		nematode.render();
		if (previousNema != null) {
			previousNema.render();
		}
		drawArrow(width / 4, height / 2, 50, true);
		drawArrow(3 * width / 4, height / 2, 50, false);
		if (frameCount % 60 == 0)
			System.out.println(nematodes.get(currentNematode));
	}

	public void drawArrow(int x, int y, float len, boolean left) {
		pushMatrix();
		translate(x, y);
		stroke(360, 360, 360);
		strokeWeight(4);

		if (left) {
			line(0, 0, -len, 0);
			line(-len, 0, -len + 8, +8);
			line(-len, 0, -len + 8, -8);
		} else {
			line(0, 0, len, 0);
			line(len, 0, len - 8, +8);
			line(len, 0, len - 8, -8);
		}
		popMatrix();
	}

}
