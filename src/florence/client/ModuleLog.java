package florence.client;

import java.util.ArrayList;

public class ModuleLog {
	public ModuleLog(){
		moduleLog = new ArrayList<Module>();
	}
	public void addModule(Module mod){
		moduleLog.add(mod);
	}
	public void removeModule(int index){
		moduleLog.remove(index);
	}
	public Module getModule(int index){
		try{
			return moduleLog.get(index);
		}catch(IndexOutOfBoundsException exp){
			//TODO Maybe add some way to deal with exception
			return null;
		}
	}
	public ArrayList<Module> getModuleLog(){
		return moduleLog;
	}
	public int getSize(){
		return moduleLog.size();
	}
	public boolean containsModule(int find){
		boolean found = false;
		int counter = 0;
		do{
			try{
				if(this.getModule(counter).getId() == find){
					found = true;
				}
			}catch (NullPointerException exp){
				//TODO Add some way to handle exception.
			}
		} while (counter < this.getSize() && found == false);
		return found;
	}
	private ArrayList<Module> moduleLog;
}
