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
		return moduleLog.get(index);
	}
	public ArrayList<Module> getModuleLog(){
		return moduleLog;
	}
	public int getSize(){
		return moduleLog.size();
	}
	private ArrayList<Module> moduleLog;
}
