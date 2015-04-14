package florence.client;

import florence.client.Module;

public class Mapper {
	
	private Module[][] moduleMap = new Module[99][99];
	
	public Mapper(){
		for(int x = 0; x < 100; x++){
			for(int y = 0; y < 100; y++){
				moduleMap[x][y] = null;
			}
		}
	}
	
	public Mapper(Module[] modules, int modCount){
		int moduleX;
		int moduleY;
		
		
		for(int x = 0; x < 99; x++){
			for(int y = 0; y < 99; y++){
				moduleMap[x][y] = null;
			}
		}
		//TODO Implement module storage with array.
		int select = 0;
		do {
			moduleX = (int)modules[select].getXCoord();
			moduleY = (int)modules[select].getYCoord();
			moduleMap[moduleX][moduleY] = modules[select];
			select++;
		} while (select < modCount);
			
	}
	
	public void addModule(Module newMod){
		int moduleX = (int)newMod.getXCoord();
		int moduleY = (int)newMod.getYCoord();
		moduleMap[moduleX][moduleY] = newMod;
	}
	
	private String findModule(Module findMod){
		String search = "";
		for(int finderX = 0; finderX < 100; finderX++){
			for(int finderY = 0; finderY < 100; finderY++){
				if(findMod.equals(moduleMap[finderX][finderY])){
					search = finderX + " " + finderY;
					finderY = 100;
					finderX = 100;
				}
			}
		}
		return search;
	}
	
	public boolean removeModule(Module removeMod){
		boolean done = false;
		//If location == "" then module was not found
		String location = findModule(removeMod);
		if(location != ""){
			int xcoor = Integer.parseInt(location, location.indexOf(' '));
			int ycoor = Integer.parseInt(location, location.indexOf(' '));
			moduleMap[xcoor][ycoor] = null;
			done = true;
		}
		return done;
	}
	
	public Module[][] getMap(){
		return moduleMap;
	}
	
	

}
