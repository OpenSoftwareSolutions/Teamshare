/**
 * 
 */
package com.oss.teamshare.sync;

/**
 * 
 *
 */
public interface PushStrategy {

  @Deprecated
  void push(Change change);
  
  void push(Revision revision);
  
}
