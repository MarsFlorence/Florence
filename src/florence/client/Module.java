package florence.client;

public class Module {

	public Module(int modId, Status modStatus, int modOrientation, double modXCoord, double modYCoord){
		id = modId;
		status = modStatus;
		orientation = modOrientation;
		xCoord = modXCoord;
		yCoord = modYCoord;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int modId){
		if(moduleID >= 1 && moduleID <= 190){
			id = modId;
		}
	}
	
	public Status getStatus(){
		return status;
	}
	
	public void setStatus(Status modStatus){
		status = modStatus;
	}
	
	public int getOrientation(){
		return orientation;
	}
	
	public void setOrientation(int modOrientation){
		if(modOrientation <= 2 && modOrientation >= 0){
			orientation = modOrientation;
		}
	}
	
	public double getXCoord(){
		return xCoord;
	}
	
	public void setXCoord(double modXCoord){
		xCoord = modXCoord;
	}
	
	public double getYCoord(){
		return yCoord;
	}
	
	public void setYCoord(double modYCoord){
		yCoord = modYCoord;
	}
	
	private int id;
	private Status status;
	private int orientation;
	private double xCoord;
	private double yCoord;
}
