package com.oss.teamwork.teamshare.common;

// TODO Id class: should be a SHA1 hash
/**
 * Base class for unique identifiers.
 */
public class Id {
  protected byte[] hash = null;
  
  protected Id() {}
  
  protected Id(byte[] hash) {
    this.hash = hash;
  }

  public byte[] getHash() {
    if (hash == null)
      throw new Error("Invalid object implementation. A static method which creates an object with a hash value should be implemented.");
    
    return hash;
  }
}
