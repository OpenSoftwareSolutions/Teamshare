package topology;

import java.util.ArrayList;

import common.Device;
import common.Group;

public interface TopologyService {
	public ArrayList<Device> getDevices();
	public void updateGroupOverlay(Group group);
}
