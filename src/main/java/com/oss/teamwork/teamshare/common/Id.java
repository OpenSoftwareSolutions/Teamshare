package com.oss.teamwork.teamshare.common;

// TODO Id class: should be a SHA1 hash
/**
 * Base class for unique identifiers.
 */
public abstract class Id<T> {
  protected T value;
  
  public Id(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Id other = (Id) obj;
    if (value == null) {
      if (other.value != null)
        return false;
    } else if (!value.equals(other.value))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "" + value;
  }

  
}
