package application;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javafx.beans.property.*;

public class Car {
	private final SimpleStringProperty mark;
	private final SimpleIntegerProperty productionYear;
	private final SimpleFloatProperty engineCapacity, maxSpeed;

	public Car(String mark, int productionYear, float engineCapacity, float maxSpeed) {
		this.mark = new SimpleStringProperty(mark);
		this.productionYear = new SimpleIntegerProperty(productionYear);
		this.engineCapacity = new SimpleFloatProperty(engineCapacity);
		this.maxSpeed = new SimpleFloatProperty(maxSpeed);
	}
	 
	public String getMark() { return mark.get(); }
	public int getProductionYear() { return productionYear.get(); }
	public float getEngineCapacity() { return engineCapacity.get(); }
	public float getMaxSpeed() { return maxSpeed.get(); }
	
	public void setMark(String m) { mark.set(m); }
	public void setProductionYear(int py) { productionYear.set(py); }
	public void setEngineCapacity(float ec) { engineCapacity.set(ec); }
	public void setMaxSpeed(float ms) { maxSpeed.set(ms); }

	public void write(DataOutputStream output) throws IOException {
		output.writeUTF(getMark());
		output.writeInt(getProductionYear());
		output.writeFloat(getEngineCapacity());
		output.writeFloat(getMaxSpeed());
	}
	
	public static Car read(DataInputStream input) throws IOException {
		return new Car(input.readUTF(), input.readInt(), input.readFloat(), input.readFloat());
	}
}
