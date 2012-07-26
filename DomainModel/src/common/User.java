package common;

import java.util.ArrayList;

public class User {
	public long userID;
	public String username;
	public String email;
	public boolean online; //sa adaug si la policies ca un alt user poate sa 
								//vada daca un user este online sau nu, adica are 
								//cel putin un device online
	public ArrayList<Device> devices;
}
