package com.oss.teamshare.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtil {

  public static byte[] getFileHash(Path file) throws IOException {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA1");
    } catch (NoSuchAlgorithmException e) {
      throw new Error(e);
    }
    FileInputStream input = new FileInputStream(file.toFile());
    byte[] fileData = new byte[4096];
    int count;
    
    while ((count = input.read(fileData)) != -1) {
      md.update(fileData, 0, count);
    }
    
    input.close();
    
    return md.digest();
  }
  
  public static void main(String[] args) throws IOException {
    byte[] hash = getFileHash(Paths.get("/home/calinburloiu/tmp/swift/big.in"));
    System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(hash));
  }
}
