package gm;

import basic.User;

public class MessageManager {

	public void sendInvite(Group g, String userEmail){}
	
	public void processInvite(InvitationMessage msg){}
	
	public void sendOwnerTransfer(Group g, User newOwner){}

	public void processOwnerTransfer(TransferMessage msg){}
	
}
