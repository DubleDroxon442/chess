/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess2;

import java.util.ArrayList;

/**
 *
 * @author danny
 */
public class Place {
    public Place(){
        
    }
    public static int yToRow(int x){
        if(x==1){
            return 7;
        }else if(x==2){
            return 6;
        }else if(x==3){
            return 5;
        }else if(x==4){
            return 4;
        }else if(x==5){
            return 3;
        }else if(x==6){
            return 2;
        }else if(x==7){
            return 1;
        }else if(x==8){
            return 0;
        }
        return -1;
    }
    
    
    public static int xToCol(String x){
    if(x.equals("a")){
      return 0;
    }else if(x.equals("b")){
      return 1;
    }else if(x.equals("c")){
      return 2;
    }else if(x.equals("d")){
      return 3;
    }else if(x.equals("e")){
      return 4;
    }else if(x.equals("f")){
      return 5;
    }else if(x.equals("g")){
      return 6;
    }else{
      return 7;
    }
  }
  public static ArrayList<String> decifer(String p){
    ArrayList<String> res=new ArrayList<String>();
    ArrayList<String> list=new ArrayList<String>();
    for(int i=0; i<p.length(); i++){
        list.add(p.substring(i,i+1));
    }
    
    
    
    for(int i=0; i<list.size(); i++){
        if(list.get(i).equals("x")){
            list.remove(i);
            i--;
        }else if(list.get(i).equals("O")){
            list.remove(i);
            i--;
        }else if(list.get(i).equals("=")){
            list.remove(i);
            i--;
        }else if(list.get(i).equals("B")){
            list.remove(i);
            i--;
        }else if(list.get(i).equals("N")){
            list.remove(i);
            i--;
        }else if(list.get(i).equals("R")){
            list.remove(i);
            i--;
        }else if(list.get(i).equals("Q")){
            list.remove(i);
            i--;
        }else if(list.get(i).equals("K")){
            list.remove(i);
            i--;
        }
    }
    
    if(list.size()==4){
        String s1=list.get(0)+list.get(1);
        String s2=list.get(2)+list.get(3);
        res.add(s1);
        res.add(s2);
        
    }else if(list.size()==2){
        res.add(list.get(0)+list.get(1));
    }else{
        String s=list.get(0);
        if((s.equals("1"))||(s.equals("2"))||(s.equals("3"))||(s.equals("4"))||(s.equals("5"))||(s.equals("6"))||(s.equals("7"))||(s.equals("8"))){
            String s1=list.get(1);
            String s2=list.get(0);
            String s3=list.get(2);
            
            res.add(s1+s2);
            res.add(s1+s3);
        }else{
            String s3=list.get(2);
            String s1=list.get(0);
            String s2=list.get(1);
            res.add(s1+s3);
            res.add(s2+s3);
        }
    }
    
    return res;
  }
  public static int[] toRC(String p){
    int r=Integer.parseInt(p.substring(1,2));
    String c=p.substring(0,1);
    int[] res=new int[2];
    if(r==8){
      res[0]=0;
    }else if(r==7){
      res[0]=1;
    }else if(r==6){
      res[0]=2;
    }else if(r==5){
      res[0]=3;
    }else if(r==4){
      res[0]=4;
    }else if(r==3){
      res[0]=5;
    }else if(r==2){
      res[0]=6;
    }else if(r==1){
      res[0]=7;
    }
    if(c.equals("a")){
      res[1]=0;
    }else if(c.equals("b")){
      res[1]=1;
    }else if(c.equals("c")){
      res[1]=2;
    }else if(c.equals("d")){
      res[1]=3;
    }else if(c.equals("e")){
      res[1]=4;
    }else if(c.equals("f")){
      res[1]=5;
    }else if(c.equals("g")){
      res[1]=6;
    }else if(c.equals("h")){
      res[1]=7;
    }
    return res;
  }
  public static String RCtoP(int r, int c){
    int y;
    String x="";
    if(r==0){
      y=8;
    }else if(r==1){
      y=7;
    }else if(r==2){
      y=6;
    }else if(r==3){
      y=5;
    }else if(r==4){
      y=4;
    }else if(r==5){
      y=3;
    }else if(r==6){
      y=2;
    }else{
      y=1;
    }
    if(c==0){
      x="a";
    }else if(c==1){
      x="b";
    }else if(c==2){
      x="c";
    }else if(c==3){
      x="d";
    }else if(c==4){
      x="e";
    }else if(c==5){
      x="f";
    }else if(c==6){
      x="g";
    }else if(c==7){
      x="h";
    }
    return x+y;
  }
}