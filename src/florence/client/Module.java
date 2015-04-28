package florence.client;

import com.google.gwt.user.client.ui.Image;
/**
 * Class that deals with all individual Module information.
 */
public class Module {
	/**
	 * The identification number of module.
	 */
	private int id;
	/**
	 * The current status of a module.
	 */
	private Status status;
	/**
	 * The orientation of a module, based on how many turns are
	 * required to be standing upright.
	 */
	private int orientation;
	/**
	 * The x-coordinate of the module.
	 */
	private double xCoord;
	/**
	 * The y-coordinate of the module.
	 */
	private double yCoord;
	
	/**
	 * Constructor that builds a new Module.
	 * @param modId the ID of module
	 * @param modStatus the Status of module
	 * @param modOrientation the orientation of module
	 * @param modXCoord the x coordinate of module
	 * @param modYCoord the y coordinate of module
	 */
	public Module(int modId, Status modStatus,
			int modOrientation, double modXCoord, double modYCoord) {
		id = modId;
		status = modStatus;
		orientation = modOrientation;
		xCoord = modXCoord;
		yCoord = modYCoord;
	}
	/**
	 * Constructor.
	 */
	public Module() {
		
	}
	/**
	 * Method that gets a Module's ID.
	 * @return int the ID of a module
	 */
	public int getId() {
		return id;
	}
	/**
	 * Method that sets a Module's ID.
	 * @param modId the new ID of a module
	 */
	public void setId(int modId) {
		if (modId >= 1 && modId <= 190) {
			id = modId;
		}
	}
	/**
	 * Method that sets a Module's Type.
	 * @return Type the type of module
	 */
	public Type getModType() {
		if (id >= 1 && id <= 40) {
			return Type.PLAIN;
		} else if (id >= 61 && id <= 80) {
			return Type.DORMITORY;
		} else if (id >= 91 && id <= 100) {
			return Type.SANITATION;
		} else if (id >= 111 && id <= 120) {
			return Type.FWS;
		} else if (id >= 131 && id <= 134) {
			return Type.GR;					
		} else if (id >= 141 && id <= 144) {
			return Type.CANTEEN;
		} else if (id >= 151 && id <= 154) {
			return Type.POWER;
		} else if (id >= 161 && id <= 164) {
			return Type.CONTROL;
		} else if (id >= 171 && id <= 174) {
			return Type.AIRLOCK;
		} else if (id >= 181 && id <= 184) {
			return Type.MEDICAL;
		} else {
			return null;
		}
	}
	/**
	 * Method that gets a Module's status.
	 * @return Status the status of a module
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * Method that sets a Module's status with STATUS.
	 * @param modStatus the status of a module in Status form
	 */
	public void setStatus(Status modStatus) {
		status = modStatus;
	}
	/**
	 * Method that sets a Module's status with a String.
	 * @param modStatus the status of a module in string form
	 */
	public void setStatus(String modStatus) {
		if (modStatus.equals("UNDAMAGED")) {
			status = Status.UNDAMAGED;
		} else if (modStatus.equals("DAMAGED")) {
			status = Status.DAMAGED;
		} else if (modStatus.equals("UNCERTAIN")) {
			status = Status.UNCERTAIN;
		}
	}
	/**
	 * Method that gets a Module's orientation.
	 * @return orientation this is the orientation of module
	 */
	public int getOrientation() {
		return orientation;
	}
	/**
	 * Method that sets a Module's orientation.
	 * @param modOrientation this is the orientation of module
	 */
	public void setOrientation(int modOrientation) {
		if (modOrientation <= 2 && modOrientation >= 0) {
			orientation = modOrientation;
		}
	}
	/**
	 * Method that gets a Module's X coordinate.
	 * @return double this is the X coordinate
	 */
	public double getXCoord() {
		return xCoord;
	}
	/**
	 * Method that sets a Module's X coordinate.
	 * @param modXCoord this is the X coordinate
	 */
	public void setXCoord(double modXCoord) {
		xCoord = modXCoord;
	}
	/**
	 * Method that gets a Module's Y coordinate.
	 * @return double this is the Y coordinate
	 */
	public double getYCoord() {
		return yCoord;
	}
	/**
	 * Method that sets a Module's Y coordinate.
	 * @param modYCoord this is the Y coordinate
	 */
	public void setYCoord(double modYCoord) {
		yCoord = modYCoord;
	}
	/**
	 * Method that gets required image for a module.
	 * @return Image the image that matches ID
	 */
	public Image imageLocate() {
		final Image foundjpg = new Image();
		if (id >= 1 && id <= 40) {
			foundjpg.setUrl("images/Plain.jpg");
		} else if (id >= 61 && id <= 80) {
			foundjpg.setUrl("images/Dormitory.jpg");
		} else if (id >= 91 && id <= 100) {
			foundjpg.setUrl("images/Sanitation.jpg");
		} else if (id >= 111 && id <= 120) {
			foundjpg.setUrl("images/Food.jpg");
		} else if (id >= 131 && id <= 134) {
			foundjpg.setUrl("images/Gym.jpg");
		} else if (id >= 141 && id <= 144) {
			foundjpg.setUrl("images/Canteen.jpg");
		} else if (id >= 151 && id <= 154) {
			foundjpg.setUrl("images/Power.jpg");
		} else if (id >= 161 && id <= 164) {
			foundjpg.setUrl("images/Control.jpg");
		} else if (id >= 171 && id <= 174) {
			foundjpg.setUrl("images/Airlock.jpg");
		} else if (id >= 181 && id <= 184) {
			foundjpg.setUrl("images/Medical.jpg");
		} else {
			//foundjpg = null;
		}
		try{
			foundjpg.setPixelSize(50, 50);
		} catch (NullPointerException ex) {
			
		}
		
		return foundjpg;
	}
	/**
	 * Method used to check if given ID is valid.
	 * @param checking integer in question that user wants to know is a 
	 * valid ID
	 * @return true if valid ID, false if invalid ID
	 */
	public final boolean validIDcheck(int checking) {
		Module compare = new Module(checking, Status.UNCERTAIN, 0, 0.0, 0.0);
		boolean checked = true;
		try {
			if (compare.getModType() == null) {
				checked = false;
			}
		} catch (NullPointerException exp) {		
			checked = false;
		}
		return checked;
	}
}
