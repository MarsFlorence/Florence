package florence.client;

import com.google.gwt.user.client.ui.Image;

public class Module {

	public Module(int modId, Status modStatus, int modOrientation, double modXCoord, double modYCoord){
		id = modId;
		status = modStatus;
		orientation = modOrientation;
		xCoord = modXCoord;
		yCoord = modYCoord;
	}

	public Module(){
		
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int modId){
		if(modId >= 1 && modId <= 190){
			id = modId;
		}
	}
	
	public Type getModType(){
	
			if (id >= 1 && id <= 40){
				return Type.PLAIN;
			}
			else if (id >= 61 && id <= 80){
				return Type.DORMITORY;
			}
			else if (id >= 91 && id <= 100){
				return Type.SANITATION;
			}
			else if (id >= 111 && id <= 120){
				return Type.FWS;
			}
			else if (id >= 131 && id <= 134){
				return Type.GR;					
			}
			else if (id >= 141 && id <= 144){
				return Type.CANTEEN;
			}
			else if (id >= 151 && id <= 154){
				return Type.POWER;
			}
			else if (id >= 161 && id <= 164){
				return Type.CONTROL;
			}
			else if (id >= 171 && id <= 174){
				return Type.AIRLOCK;
			}
			else if (id >= 181 && id <= 184){
				return Type.MEDICAL;
			}
			else{
				return null;
			}
	}
	
	public Status getStatus(){
		return status;
	}
	
	public void setStatus(Status modStatus){
		status = modStatus;
	}
	

	
	public void setStatus(String modStatus){
		if(modStatus.equals("UNDAMAGED")){
			status = Status.UNDAMAGED;
		}
		else if(modStatus.equals("DAMAGED")){
			status = Status.DAMAGED;
		}
		else if(modStatus.equals("UNCERTAIN")){
			status = Status.UNCERTAIN;
		}
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
	
	public Image imageLocate(){
		final Image foundjpg;
		if(id >= 1 && id <= 40){
			foundjpg = new Image("images/Plain.jpg");
		}else if (id >= 61 && id <= 80){
			foundjpg =  new Image("images/Dormitory.jpg");
		}else if (id >= 91 && id <= 100){
			foundjpg =  new Image("images/Sanitation.jpg");
		}else if (id >= 111 && id <= 120){
			foundjpg =  new Image("images/Food.jpg");
		}else if (id >= 131 && id <= 134){
			foundjpg =  new Image("images/Gym.jpg");
		}else if (id >= 141 && id <= 144){
			foundjpg =  new Image("images/Canteen.jpg");
		}else if (id >= 151 && id <= 154){
			foundjpg =  new Image("images/Power.jpg");
		}else if (id >= 161 && id <= 164){
			foundjpg =  new Image("images/Control.jpg");
		}else if (id >= 171 && id <= 174){
			foundjpg =  new Image("images/Airlock.jpg");
		}else if (id >= 181 && id <= 184){
			foundjpg =  new Image("images/Medical.jpg");
		}else{
			foundjpg = null;
		}
		
		return foundjpg;
	}
	
	public boolean validIDcheck(int checking){
		Module compare = new Module(checking, Status.UNCERTAIN, 0, 0.0, 0.0);
		boolean checked = true;
		try{
			if(compare.getModType() == null){
				checked = false;
			}
		}catch (NullPointerException exp){
			
		}
		return checked;
	}
	
	private int id;
	private Status status;
	private int orientation;
	private double xCoord;
	private double yCoord;
}
