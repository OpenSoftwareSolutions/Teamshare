package topo;

import java.util.List;
import basic.Device;
import basic.DeviceStatus;
import gm.Group;

public class Overlay {

	private Group group;
	private List<Device> onlineDevices;
	private List<Device> allDevices;
	private OverlayManager overlayManager;
	
	public void setGroup(Group g){}
	public List<Device> getOnlineDevices(){ return null;}
	public List<Device> getAllDevices(){ return null;}
	public void setDeviceStatus(Device device, DeviceStatus status){}	
	
}
