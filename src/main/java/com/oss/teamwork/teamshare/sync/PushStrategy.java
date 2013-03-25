/**
 * 
 */
package com.oss.teamwork.teamshare.sync;

/**
 * 
 *
 */
public interface PushStrategy {

  @Deprecated
  void push(Change change);
  
  void push(Revision revision);
  
}
