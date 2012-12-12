package com.oss.teamwork.teamshare.messaging;

public enum MessageStatus {
	
  /**
   * Status of a newly created message which was not sent yet.
   */
  DRAFT,
  
  /**
   * Status of a message after it was sent to a user.
   */
	SENT,
	
	/**
	 * Status of a received message which was not processed / read yet.
	 */
	UNREAD,
	
	/**
	 * Status of a received message after it was processed / read.
	 */
	READ;
}
