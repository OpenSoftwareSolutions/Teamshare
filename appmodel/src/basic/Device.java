package basic;
import java.util.HashMap;


/**
 * Class that represents a Device. Includes information about the user of the device, its address, it properties and keys.
 * @author adriana
 *
 */
public class Device {

	private String name;
	private User user;
	private DeviceType type;
	private DeviceStatus status;
	private HashMap<DeviceProp, Float> properties;
	private String publicKey;
	private String address;
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public DeviceType getType() {
		return type;
	}
	public void setType(DeviceType type) {
		this.type = type;
	}
	public DeviceStatus getStatus() {
		return status;
	}
	public void setStatus(DeviceStatus status) {
		this.status = status;
	}
	public HashMap<DeviceProp, Float> getProperties() {
		return properties;
	}
	public void setProperties(HashMap<DeviceProp, Float> properties) {
		this.properties = properties;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
}
