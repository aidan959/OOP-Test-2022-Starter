package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NematodeVisualiser extends PApplet
{

	public void keyPressed()
	{		
		if(keyCode == LEFT){
			currentNematode--;
			if(currentNematode < 0){
				currentNematode = nematodes.size() -1;
			}
		}	
		else if (keyCode == RIGHT)
		{
			currentNematode++;
			if(currentNematode > nematodes.size()-1){
				currentNematode = 0;
			}
		}
	}


	public void settings()
	{
		size(800, 800);
	}

	public void setup() 
	{
		colorMode(HSB);
		background(0);
		smooth();	
		loadNematodes();			
	}
	ArrayList<Nematode> nematodes;
	int currentNematode;
	public void loadNematodes()
	{
		nematodes = new ArrayList<>();
		Table table;
		table = loadTable("data/nematodes.csv", "header");
		
		for(TableRow row : table.rows()){
			nematodes.add(new Nematode(row, this));

		}

		for(Nematode nematode: nematodes){
			System.out.println(nematode);
		}
	}


	public void draw()
	{	
		clear();
		nematodes.get(currentNematode).render();
		if(frameCount % 60 == 0)
			System.out.println(nematodes.get(currentNematode));
	}
}
