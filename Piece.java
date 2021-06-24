/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess2;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author danny
 */
public class Piece {
  ArrayList<String> places=new ArrayList<String>();
  private int s;
  private String p;
  private String sv;
  private int moved=0;
  private Image i;
  public Piece(int c, String p1, String sv1){
    s=c;
    p=p1;
    sv=sv1;
    
  }
  public int getSide(){
    return s;
  }
  public void setPlace(String p1){
    p=p1;
  }
  public String getPlace(){
    return p;
  }
  public void setSV(String s){
    sv=s;
  }
  public String getSV(){
    return sv;
  }
  public void moveInc(){
    moved++;
  }
  public int getMoved(){
    return moved;
  }
  public void setMoved(int mvd){
      moved=mvd;
  }
  @Override
  public String toString(){
      return getSV();
  }
}