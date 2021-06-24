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
public class boardAnalyzer {
    private ArrayList<boardPlace[][]> pml=new ArrayList<boardPlace[][]>();
    
    public static final int PLACE_SIZE=100;
    private boardPlace[][] bab=new boardPlace[8][8];
    private ArrayList<Piece> a1=new ArrayList<Piece>();
    private ArrayList<Piece> a2=new ArrayList<Piece>();
    private ArrayList<Piece> b1=new ArrayList<Piece>();
    private ArrayList<Piece> b2=new ArrayList<Piece>();
    
    
    public boardAnalyzer(boardPlace[][] ba){
        boolean b4=true;
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(ba[r][c].getPiece()==null){
                    boardPlace temp=new boardPlace(r,c,b4);
                    bab[r][c]=temp;
                }else{
                    boardPlace temp=new boardPlace(r,c,b4);
                    int side=ba[r][c].getPieceSide();
                    int moved=ba[r][c].getPiece().getMoved();
                    String sv=ba[r][c].getPieceSV();
                    String place=Place.RCtoP(r, c);
                    Piece tempP=new Piece(side, place,sv);
                    tempP.setMoved(moved);
                    temp.setPiece(tempP);
                    bab[r][c]=temp;
                }
                b4=!b4;
            }
            b4=!b4;
        }
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(bab[r][c].getPiece()!=null){
                    if(bab[r][c].getPieceSV().substring(0,1).equals("K")){
                        if(bab[r][c].getPieceSide()==1){
                            a1.add(bab[r][c].getPiece());
                        }else{
                            b1.add(bab[r][c].getPiece());
                        }
                    }
                }
            }
        }
        
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(bab[r][c].getPiece()!=null){
                    if(!(bab[r][c].getPieceSV().substring(0,1).equals("K"))){
                        if(bab[r][c].getPieceSide()==1){
                            a1.add(bab[r][c].getPiece());
                        }else{
                            b1.add(bab[r][c].getPiece());
                        }
                    }
                }
            }
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="getter/setter">
    public boardPlace[][] getBab(){
        return bab;
    }
    
    public void setBab(boardPlace[][] bab1){
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                boardPlace temp=new boardPlace(r,c,false);
                temp.setPiece(bab1[r][c].getPiece());
                bab[r][c]=temp;
            }
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="--pawnPosMovesString--">
    public ArrayList<String> pawnPosMovesString(boardPlace bp){
        ArrayList<String> res=new ArrayList<String>();
        int r=bp.getR();
        int c=bp.getC();
        String p=Place.RCtoP(r,c);
        int side=bp.getPieceSide();
        if(side==1){
            boardPlace bp1=bab[r-1][c];
            if(bp1.getPiece()==null){
                if(r-1==0){
                    int r1=bp1.getR();
                    int c1=bp1.getC();
                    String s=Place.RCtoP(r1, c1);
                    res.add(Place.RCtoP(r, c)+s+"=N");
                    res.add(Place.RCtoP(r, c)+s+"=R");
                    res.add(Place.RCtoP(r, c)+s+"=B");
                    res.add(Place.RCtoP(r, c)+s+"=Q");
                }else{
                    int r1=bp1.getR();
                    int c1=bp1.getC();
                    String s=Place.RCtoP(r1, c1);
                    res.add(Place.RCtoP(r, c)+s);
                }
                if(bp.getPiece().getMoved()==0){
                    boardPlace bp2=bab[r-2][c];
                    if(bp2.getPiece()==null){
                        int r1=bp2.getR();
                        int c1=bp2.getC();
                        String s=Place.RCtoP(r1, c1);
                        res.add(Place.RCtoP(r, c)+s);
                    }
                }
              
            }
            
            if((c+1)<8){
                bp1=bab[r-1][c+1];
                if((bp1.getPiece()!=null)&&(bp1.getPieceSide()!=bp.getPieceSide())){
                    if(r-1==0){
                        int r1=bp1.getR();
                        int c1=bp1.getC();
                        String s=Place.RCtoP(r1, c1);
                        res.add(p+"x"+s+"=N");
                        res.add(p+"x"+s+"=B");
                        res.add(p+"x"+s+"=R");
                        res.add(p+"x"+s+"=Q");
                    }else{
                        int r1=bp1.getR();
                        int c1=bp1.getC();
                        String s=Place.RCtoP(r1, c1);
                        res.add(p+"x"+s);
                    }
                    
                }
            }
            if((c-1)>=0){
                bp1=bab[r-1][c-1];
                if((bp1.getPiece()!=null)&&(bp1.getPieceSide()!=bp.getPieceSide())){
                    if(r-1==0){
                        int r1=bp1.getR();
                        int c1=bp1.getC();
                        String s=Place.RCtoP(r1, c1);
                        res.add(p+"x"+s+"=N");
                        res.add(p+"x"+s+"=R");
                        res.add(p+"x"+s+"=B");
                        res.add(p+"x"+s+"=Q");
                    }else{
                        int r1=bp1.getR();
                        int c1=bp1.getC();
                        String s=Place.RCtoP(r1, c1);
                        res.add(p+"x"+s);
                    }
                }
            }
       
        }else{
            boardPlace bp1=bab[r+1][c];
            if(bp1.getPiece()==null){
                if(r+1==7){
                    int r1=bp1.getR();
                    int c1=bp1.getC();
                    String s=Place.RCtoP(r1, c1);
                    
                    res.add(Place.RCtoP(r, c)+s+"=R");
                    res.add(Place.RCtoP(r, c)+s+"=N");
                    res.add(Place.RCtoP(r, c)+s+"=B");
                    res.add(Place.RCtoP(r, c)+s+"=Q");
                }else{
                    int r1=bp1.getR();
                    int c1=bp1.getC();
                    String s=Place.RCtoP(r1, c1);
                    res.add(Place.RCtoP(r, c)+s);
                }
                if(bp.getPiece().getMoved()==0){
                    boardPlace bp2=bab[r+2][c];
                    if(bp2.getPiece()==null){
                        int r1=bp2.getR();
                        int c1=bp2.getC();
                        String s=Place.RCtoP(r1, c1);
                        res.add(Place.RCtoP(r, c)+s);
                    }
                }
                
            }
            
            if((c+1)<8){
                bp1=bab[r+1][c+1];
                if((bp1.getPiece()!=null)&&(bp1.getPieceSide()!=bp.getPieceSide())){
                    if(r+1==7){
                        int r1=bp1.getR();
                        int c1=bp1.getC();
                        String s=Place.RCtoP(r1, c1);
                        res.add(Place.RCtoP(r, c)+"x"+s+"=R");
                        res.add(Place.RCtoP(r, c)+"x"+s+"=B");
                        res.add(Place.RCtoP(r, c)+"x"+s+"=N");
                        res.add(Place.RCtoP(r, c)+"x"+s+"=Q");
                    }else{
                        int r1=bp1.getR();
                        int c1=bp1.getC();
                        String s=Place.RCtoP(r1, c1);
                        res.add(Place.RCtoP(r, c)+"x"+s);
                    }   
                }
            }
            if((c-1)>=0){
                bp1=bab[r+1][c-1];
                if((bp1.getPiece()!=null)&&(bp1.getPieceSide()!=bp.getPieceSide())){
                    if(r+1==7){
                        int r1=bp1.getR();
                        int c1=bp1.getC();
                        String s=Place.RCtoP(r1, c1);
                        res.add(Place.RCtoP(r, c)+"x"+s+"=R");
                        res.add(Place.RCtoP(r, c)+"x"+s+"=B");
                        res.add(Place.RCtoP(r, c)+"x"+s+"=N");
                        res.add(Place.RCtoP(r, c)+"x"+s+"=Q");
                    }else{
                        int r1=bp1.getR();
                        int c1=bp1.getC();
                        String s=Place.RCtoP(r1, c1);
                        res.add(Place.RCtoP(r, c)+"x"+s);
                    }   
                }
            }
        }
        /*checklist
        1. che
        
        */
        
        return res;
    }
    public ArrayList<String> pawnPosMovesString(boardPlace bp, boardPlace[][] bpb){
        ArrayList<String> res=new ArrayList<String>();
        int r=bp.getR();
        int c=bp.getC();
        String p=Place.RCtoP(r,c);
        int side=bp.getPieceSide();
        if(side==1){
            if(bpb[r-1][c].getPiece()==null){
                if(r-1==0){
                    int r1=r-1;
                    int c1=c;
                    String s=Place.RCtoP(r1,c1);
                    res.add(p+s+"=R");
                    res.add(p+s+"=B");
                    res.add(p+s+"=N");
                    res.add(p+s+"=Q");
                }else{
                    int r1=r-1;
                    int c1=c;
                    String s=Place.RCtoP(r1,c1);
                    res.add(p+s);
                }
                if(bp.getPiece().getMoved()==0){
                    if(bpb[r-2][c].getPiece()==null){
                        int r1=r-2;
                        int c1=c;
                        String s=Place.RCtoP(r1, c1);
                        res.add(p+s);
                    }
                }
            }
            
            if(c+1<8){
                if((bpb[r-1][c+1].getPiece()!=null)&&(bpb[r-1][c+1].getPieceSide()!=side)){
                    if(r-1==0){
                        int r1=r-1;
                        int c1=c+1;
                        String s=Place.RCtoP(r1,c1);
                        res.add(p+"x"+s+"=R");
                        res.add(p+"x"+s+"=B");
                        res.add(p+"x"+s+"=N");
                        res.add(p+"x"+s+"=Q");
                    }else{
                        int r1=r-1;
                        int c1=c+1;
                        String s=Place.RCtoP(r1,c1);
                        res.add(p+"x"+s);
                    }
                }
            }
            
            if(c-1>=0){
                if((bpb[r-1][c-1].getPiece()!=null)&&(bpb[r-1][c-1].getPieceSide()!=side)){
                    if(r-1==0){
                        int r1=r-1;
                        int c1=c-1;
                        String s=Place.RCtoP(r1,c1);
                        res.add(p+"x"+s+"=R");
                        res.add(p+"x"+s+"=B");
                        res.add(p+"x"+s+"=N");
                        res.add(p+"x"+s+"=Q");
                    }else{
                        int r1=r-1;
                        int c1=c-1;
                        String s=Place.RCtoP(r1,c1);
                        res.add(p+"x"+s);
                    }
                }
            }
            
            
        }else{
            if(bpb[r+1][c].getPiece()==null){
                if(r==7){
                    int r1=r+1;
                    int c1=c;
                    String s=Place.RCtoP(r1, c1);
                    res.add(p+s+"=B");
                    res.add(p+s+"=N");
                    res.add(p+s+"=R");
                    res.add(p+s+"=Q");
                }else{
                    int r1=r+1;
                    int c1=c;
                    String s=Place.RCtoP(r1, c1);
                    res.add(p+s);
                }
                if(bp.getPiece().getMoved()==0){
                    if(bpb[r+2][c].getPiece()==null){
                        int r1=r+2;
                        int c1=c;
                        String s=Place.RCtoP(r1, c1);
                        res.add(p+s);
                    }
                }
            }
            
            if(c+1<8){
                if((bpb[r+1][c+1].getPiece()!=null)&&(bpb[r+1][c+1].getPieceSide()!=side)){
                    if(r+1==7){
                        int r1=r+1;
                        int c1=c+1;
                        String s=Place.RCtoP(r1, c1);
                        res.add(p+"x"+s+"=B");
                        res.add(p+"x"+s+"=N");
                        res.add(p+"x"+s+"=R");
                        res.add(p+"x"+s+"=Q");
                    }else{
                        int r1=r+1;
                        int c1=c+1;
                        String s=Place.RCtoP(r1, c1);
                        res.add(p+"x"+s);
                    }
                }
            }
            
            
            if(c-1>=0){
                if((bpb[r+1][c-1].getPiece()!=null)&&(bpb[r+1][c-1].getPieceSide()!=side)){
                    if(r+1==7){
                        int r1=r+1;
                        int c1=c-1;
                        String s=Place.RCtoP(r1, c1);
                        res.add(p+"x"+s+"=B");
                        res.add(p+"x"+s+"=N");
                        res.add(p+"x"+s+"=R");
                        res.add(p+"x"+s+"=Q");
                    }else{
                        int r1=r+1;
                        int c1=c-1;
                        String s=Place.RCtoP(r1, c1);
                        res.add(p+"x"+s);
                    }
                }
            }
        }
        return res;
    }
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="--queenPosMovesString--">
    public ArrayList<String> queenPosMovesString(boardPlace bp){
        ArrayList<String> res=new ArrayList<String>();
        ArrayList<String> bpos=this.bishopPosMovesString(bp);
        ArrayList<String> rpos=this.rookPosMovesString(bp);
        ArrayList<String> res1=new ArrayList<String>();
        for(int i=0; i<bpos.size(); i++){
            res.add(bpos.get(i));
        }
        for(int i=0; i<rpos.size(); i++){
            res.add(rpos.get(i));
        }
        for(int i=0; i<res.size(); i++){
            String s1="Q"+res.get(i).substring(1,res.get(i).length());
            res1.add(s1);
            
        }
        return res1;
    }
    
    
    public ArrayList<String> queenPosMovesString(boardPlace bp, boardPlace[][] bpb){
        ArrayList<String> res=new ArrayList<String>();
        ArrayList<String> bpos=this.bishopPosMovesString(bp,bpb);
        ArrayList<String> rpos=this.rookPosMovesString(bp,bpb);
        ArrayList<String> res1=new ArrayList<String>();
        for(int i=0; i<bpos.size(); i++){
            res.add(bpos.get(i));
        }
        for(int i=0; i<rpos.size(); i++){
            res.add(rpos.get(i));
        }
        for(int i=0; i<res.size(); i++){
            String s1="Q"+res.get(i).substring(1,res.get(i).length());
            res1.add(s1);
            
        }
        return res1;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="--kingPosMovesString--">
    public ArrayList<String> kingPosMovesString(boardPlace bp){
        ArrayList<String> res=new ArrayList<String>();
        int r=bp.getR();
        int c=bp.getC();
        String p=Place.RCtoP(r,c);
        String sv=bp.getPieceSV().substring(0,1);
        if(r-1>=0){
          if(bab[r-1][c].getPiece()==null){
              String res1=Place.RCtoP(r-1,c);
              res.add(sv+p+res1);
          }else{
              if(bab[r-1][c].getPieceSide()!=bp.getPieceSide()){
                  String res1=Place.RCtoP(r-1,c);
                  res.add(sv+p+"x"+res1);
              }
          }
        }
        if(r+1<8){
            if(bab[r+1][c].getPiece()==null){
                String res1=Place.RCtoP(r+1,c);
                res.add(sv+p+res1);
            }else{
                if(bab[r+1][c].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r+1,c);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if(c-1>=0){
            if(bab[r][c-1].getPiece()==null){
                String res1=Place.RCtoP(r,c-1);
                res.add(sv+p+res1);
            }else{
                if(bab[r][c-1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r,c-1);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if(c+1<8){
            if(bab[r][c+1].getPiece()==null){
                String res1=Place.RCtoP(r,c+1);
                res.add(sv+p+res1);
            }else{
                if(bab[r][c+1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r,c+1);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if((r+1<8)&&(c+1<8)){
            if(bab[r+1][c+1].getPiece()==null){
                String res1=Place.RCtoP(r+1,c+1);
                res.add(sv+p+res1);
            }else{
                if(bab[r+1][c+1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r+1,c+1);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if((r-1>=0)&&(c+1<8)){
            if(bab[r-1][c+1].getPiece()==null){
                String res1=Place.RCtoP(r-1,c+1);
                res.add(sv+p+res1);
            }else{
                if(bab[r-1][c+1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r-1,c+1);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if((r-1>=0)&&(c-1>=0)){
            if(bab[r-1][c-1].getPiece()==null){
                String res1=Place.RCtoP(r-1,c-1);
                res.add(sv+p+res1);
            }else{
                if(bab[r-1][c-1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r-1,c-1);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if((r+1<8)&&(c-1>=0)){
            if(bab[r+1][c-1].getPiece()==null){
                String res1=Place.RCtoP(r+1,c-1);
                res.add(sv+p+res1);
            }else{
                if(bab[r+1][c-1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r+1,c-1);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if(bp.getPiece().getMoved()==0){
            
            if(bp.getPieceSide()==1){
                ArrayList<Piece> rooks=new ArrayList<Piece>();
                for(int i=0; i<a1.size(); i++){
                    if(a1.get(i).getSV().substring(0,1).equals("R")){
                        rooks.add(a1.get(i));
                        
                    }
                }
                
                for(int i=0; i<rooks.size(); i++){
                    if(rooks.get(i).getMoved()==0){
                        
                        Piece r3=rooks.get(i);
                        int[] cords=Place.toRC(r3.getPlace());
                        int r1=cords[0];
                        int c1=cords[1];
                        
                        
                        String p2=r3.getPlace();
                        
                        String p1=bp.getPiece().getPlace();
                        if(r1==r){
                            
                            boolean b3=true;
                            if(c1>c){
                                for(int k=c+1; k<c1; k++){
                                    if(bab[r][k].getPiece()!=null){
                                        
                                        b3=false;
                                    }
                                }
                                if(b3){
                                    res.add("K"+p1+"O"+p2);
                                }
                            }else{
                                for(int k=c-1; k>c1; k--){
                                    if(bab[r][k].getPiece()!=null){
                                        b3=false;
                                    }
                                }
                                if(b3){
                                    res.add("K"+p1+"O"+p2);
                                }
                            }
                        }
                    }
                }
            }else{
                
                ArrayList<Piece> rooks=new ArrayList<Piece>();
                for(int i=0; i<b1.size(); i++){
                    if(b1.get(i).getSV().substring(0,1).equals("R")){
                        rooks.add(b1.get(i));
                        
                    }
                }
                for(int i=0; i<rooks.size(); i++){
                    if(rooks.get(i).getMoved()==0){
                        Piece r3=rooks.get(i);
                        int[] cords=Place.toRC(r3.getPlace());
                        int r1=cords[0];
                        int c1=cords[1];
                        String p2=r3.getPlace();
                        String p1=bp.getPiece().getPlace();
                        if(r1==r){
                            boolean b3=true;
                            if(c1>c){
                                for(int k=c+1; k<c1; k++){
                                    if(bab[r][k].getPiece()!=null){
                                        b3=false;
                                    }
                                }
                                if(b3){
                                    res.add("K"+p1+"O"+p2);
                                }
                            }else{
                                for(int k=c-1; k>c1; k--){
                                    if(bab[r][k].getPiece()!=null){
                                        b3=false;
                                    }
                                }
                                if(b3){
                                    res.add("K"+p1+"O"+p2);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return res;
    }
    
    public ArrayList<String> kingPosMovesString(boardPlace bp, boardPlace[][] bpb){
        ArrayList<String> res=new ArrayList<String>();
        int r=bp.getR();
        int c=bp.getC();
        String p=Place.RCtoP(r,c);
        String sv=bp.getPieceSV().substring(0,1);
        ArrayList<ArrayList<Piece>> lists=getLists(bpb);
        ArrayList<Piece> a12=lists.get(0);
        ArrayList<Piece> b12=lists.get(1);
        if(r-1>=0){
          if(bpb[r-1][c].getPiece()==null){
              String res1=Place.RCtoP(r-1,c);
              res.add(sv+p+res1);
          }else{
              if(bpb[r-1][c].getPieceSide()!=bp.getPieceSide()){
                  String res1=Place.RCtoP(r-1,c);
                  res.add(sv+p+"x"+res1);
              }
          }
        }
        if(r+1<8){
            if(bpb[r+1][c].getPiece()==null){
                String res1=Place.RCtoP(r+1,c);
                res.add(sv+p+res1);
            }else{
                if(bpb[r+1][c].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r+1,c);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if(c-1>=0){
            if(bpb[r][c-1].getPiece()==null){
                String res1=Place.RCtoP(r,c-1);
                res.add(sv+p+res1);
            }else{
                if(bpb[r][c-1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r,c-1);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if(c+1<8){
            if(bpb[r][c+1].getPiece()==null){
                String res1=Place.RCtoP(r,c+1);
                res.add(sv+p+res1);
            }else{
                if(bpb[r][c+1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r,c+1);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if((r+1<8)&&(c+1<8)){
            if(bpb[r+1][c+1].getPiece()==null){
                String res1=Place.RCtoP(r+1,c+1);
                res.add(sv+p+res1);
            }else{
                if(bpb[r+1][c+1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r+1,c+1);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if((r-1>=0)&&(c+1<8)){
            if(bpb[r-1][c+1].getPiece()==null){
                String res1=Place.RCtoP(r-1,c+1);
                res.add(sv+p+res1);
            }else{
                if(bpb[r-1][c+1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r-1,c+1);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if((r-1>=0)&&(c-1>=0)){
            if(bpb[r-1][c-1].getPiece()==null){
                String res1=Place.RCtoP(r-1,c-1);
                res.add(sv+p+res1);
            }else{
                if(bpb[r-1][c-1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r-1,c-1);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if((r+1<8)&&(c-1>=0)){
            if(bpb[r+1][c-1].getPiece()==null){
                String res1=Place.RCtoP(r+1,c-1);
                res.add(sv+p+res1);
            }else{
                if(bpb[r+1][c-1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r+1,c-1);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        if(bp.getPiece().getMoved()==0){
            if(bp.getPieceSide()==1){
                ArrayList<Piece> rooks=new ArrayList<Piece>();
                for(int i=0; i<a12.size(); i++){
                    if(a12.get(i).getSV().substring(0,1).equals("R")){
                        rooks.add(a12.get(i));
                        
                    }
                }
                for(int i=0; i<rooks.size(); i++){
                    if(rooks.get(i).getMoved()==0){
                        Piece r3=rooks.get(i);
                        int[] cords=Place.toRC(r3.getPlace());
                        int r1=cords[0];
                        int c1=cords[1];
                        String p2=r3.getPlace();
                        String p1=bp.getPiece().getPlace();
                        if(r1==r){
                            boolean b3=true;
                            if(c1>c){
                                for(int k=c; k<c1; k++){
                                    if(bpb[r][k].getPiece()!=null){
                                        b3=false;
                                    }
                                }
                                if(b3){
                                    res.add("K"+p1+"O"+p2);
                                }
                            }else{
                                for(int k=c; k>c1; k--){
                                    if(bpb[r][k].getPiece()!=null){
                                        b3=false;
                                    }
                                }
                                if(b3){
                                    res.add("K"+p1+"O"+p2);
                                }
                            }
                        }
                    }
                }
            }else{
                ArrayList<Piece> rooks=new ArrayList<Piece>();
                for(int i=0; i<b12.size(); i++){
                    if(b12.get(i).getSV().substring(0,1).equals("R")){
                        rooks.add(b12.get(i));
                        
                    }
                }
                for(int i=0; i<rooks.size(); i++){
                    if(rooks.get(i).getMoved()==0){
                        Piece r3=rooks.get(i);
                        int[] cords=Place.toRC(r3.getPlace());
                        int r1=cords[0];
                        int c1=cords[1];
                        String p2=r3.getPlace();
                        String p1=bp.getPiece().getPlace();
                        if(r1==r){
                            boolean b3=true;
                            if(c1>c){
                                for(int k=c; k<c1; k++){
                                    if(bpb[r][k].getPiece()!=null){
                                        b3=false;
                                    }
                                }
                                if(b3){
                                    res.add("K"+p1+"O"+p2);
                                }
                            }else{
                                for(int k=c; k>c1; k--){
                                    if(bpb[r][k].getPiece()!=null){
                                        b3=false;
                                    }
                                }
                                if(b3){
                                    res.add("K"+p1+"O"+p2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="--bishopPosMovesString--">
    public ArrayList<String> bishopPosMovesString(boardPlace bp){
        ArrayList<String> res=new ArrayList<String>();
        
        boolean b1=true;
        boolean b2=true;
        boolean b3=true;
        boolean b4=true;
        
        int r=bp.getR();
        int c=bp.getC();
        String sv=bp.getPieceSV().substring(0,1);
        int r1=r+1;
        int r2=r-1;
        int c1=c+1;
        int c2=c-1;
        String p=Place.RCtoP(r, c);
        while((b1)&&((r1<8)&&(c1<8))){
            if(bab[r1][c1].getPiece()==null){
                String res1=Place.RCtoP(r1, c1);
                res.add(sv+p+res1);
            }else{
                if(bab[r1][c1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r1, c1);
                    res.add(sv+p+"x"+res1);   
                }
                b1=false;
            }
            r1++;
            c1++;
        }
        r1=r+1;
        r2=r-1;
        c1=c+1;
        c2=c-1;
        while((b2)&&((r1<8)&&(c2>=0))){
            if(bab[r1][c2].getPiece()==null){
                String res1=Place.RCtoP(r1, c2);
                res.add(sv+p+res1);
            }else{
                if(bab[r1][c2].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r1, c2);
                    res.add(sv+p+"x"+res1);  
                }
                b2=false;
            }
            r1++;
            c2--;
        }
        r1=r+1;
        r2=r-1;
        c1=c+1;
        c2=c-1;
        while((b3)&&((r2>=0)&&(c2>=0))){
            if(bab[r2][c2].getPiece()==null){
                String res1=Place.RCtoP(r2, c2);
                res.add(sv+p+res1);
            }else{
                if(bab[r2][c2].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r2, c2);
                    res.add(sv+p+"x"+res1);
                }
                b3=false;
            }
            r2--;
            c2--;
        }
        r1=r+1;
        r2=r-1;
        c1=c+1;
        c2=c-1;
        while((b4)&&((r2>=0)&&(c1<8))){
            if(bab[r2][c1].getPiece()==null){
                String res1=Place.RCtoP(r2, c1);
                res.add(sv+p+res1);
            }else{
                if(bab[r2][c1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r2, c1);
                    res.add(sv+p+"x"+res1);
                    
                }
                b4=false;
            }
            r2--;
            c1++;
        }
        return res;
    }
    
    
    
    
    
    public ArrayList<String> bishopPosMovesString(boardPlace bp,boardPlace[][] bpb){
        ArrayList<String> res=new ArrayList<String>();
        
        boolean b1=true;
        boolean b2=true;
        boolean b3=true;
        boolean b4=true;
        
        int r=bp.getR();
        int c=bp.getC();
        String sv=bp.getPieceSV().substring(0,1);
        int r1=r+1;
        int r2=r-1;
        int c1=c+1;
        int c2=c-1;
        String p=Place.RCtoP(r, c);
        while((b1)&&((r1<8)&&(c1<8))){
            if(bpb[r1][c1].getPiece()==null){
                String res1=Place.RCtoP(r1, c1);
                res.add(sv+p+res1);
            }else{
                if(bpb[r1][c1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r1, c1);
                    res.add(sv+p+"x"+res1);   
                }
                b1=false;
            }
            r1++;
            c1++;
        }
        r1=r+1;
        r2=r-1;
        c1=c+1;
        c2=c-1;
        while((b2)&&((r1<8)&&(c2>=0))){
            if(bpb[r1][c2].getPiece()==null){
                String res1=Place.RCtoP(r1, c2);
                res.add(sv+p+res1);
            }else{
                if(bpb[r1][c2].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r1, c2);
                    res.add(sv+p+"x"+res1);  
                }
                b2=false;
            }
            r1++;
            c2--;
        }
        r1=r+1;
        r2=r-1;
        c1=c+1;
        c2=c-1;
        while((b3)&&((r2>=0)&&(c2>=0))){
            if(bpb[r2][c2].getPiece()==null){
                String res1=Place.RCtoP(r2, c2);
                res.add(sv+p+res1);
            }else{
                if(bpb[r2][c2].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r2, c2);
                    res.add(sv+p+"x"+res1);
                }
                b3=false;
            }
            r2--;
            c2--;
        }
        r1=r+1;
        r2=r-1;
        c1=c+1;
        c2=c-1;
        while((b4)&&((r2>=0)&&(c1<8))){
            if(bpb[r2][c1].getPiece()==null){
                String res1=Place.RCtoP(r2, c1);
                res.add(sv+p+res1);
            }else{
                if(bpb[r2][c1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r2, c1);
                    res.add(sv+p+"x"+res1);
                    
                }
                b4=false;
            }
            r2--;
            c1++;
        }
        return res;
    }
    //</editor-fold>      
    //<editor-fold defaultstate="collapsed" desc="--knightPosMovesString--">
    public ArrayList<String> knightPosMovesString(boardPlace bp){
        ArrayList<String> res=new ArrayList<String>();
        
        int r=bp.getR();
        int c=bp.getC();
        String p=Place.RCtoP(r,c);
        String sv=bp.getPieceSV().substring(0,1);
        int r4=r+2;
        int r3=r+1;
        int r2=r-1;
        int r1=r-2;
        
        int c4=c+2;
        int c3=c+1;
        int c2=c-1;
        int c1=c-2;
        
        if((r4<8)&&(c3<8)){
            int r5=r4;
            int c5=c3;
            if(bab[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bab[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r3<8)&&(c4<8)){
            int r5=r3;
            int c5=c4;
            if(bab[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bab[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r2>=0)&&(c4<8)){
            int r5=r2;
            int c5=c4;
            if(bab[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bab[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r1>=0)&&(c3<8)){
            int r5=r1;
            int c5=c3;
            if(bab[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bab[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r1>=0)&&(c2>=0)){
            int r5=r1;
            int c5=c2;
            if(bab[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bab[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r2>=0)&&(c1>=0)){
            int r5=r2;
            int c5=c1;
            if(bab[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bab[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r3<8)&&(c1>=0)){
            int r5=r3;
            int c5=c1;
            if(bab[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bab[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r4<8)&&(c2>=0)){
            int r5=r4;
            int c5=c2;
            if(bab[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bab[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        return res;
    }
    
    
    public ArrayList<String> knightPosMovesString(boardPlace bp, boardPlace[][] bpb){
        ArrayList<String> res=new ArrayList<String>();
        
        int r=bp.getR();
        int c=bp.getC();
        String p=Place.RCtoP(r,c);
        String sv=bp.getPieceSV().substring(0,1);
        int r4=r+2;
        int r3=r+1;
        int r2=r-1;
        int r1=r-2;
        
        int c4=c+2;
        int c3=c+1;
        int c2=c-1;
        int c1=c-2;
        
        if((r4<8)&&(c3<8)){
            int r5=r4;
            int c5=c3;
            if(bpb[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bpb[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r3<8)&&(c4<8)){
            int r5=r3;
            int c5=c4;
            if(bpb[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bpb[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r2>=0)&&(c4<8)){
            int r5=r2;
            int c5=c4;
            if(bpb[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bpb[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r1>=0)&&(c3<8)){
            int r5=r1;
            int c5=c3;
            if(bpb[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bpb[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r1>=0)&&(c2>=0)){
            int r5=r1;
            int c5=c2;
            if(bpb[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bpb[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r2>=0)&&(c1>=0)){
            int r5=r2;
            int c5=c1;
            if(bpb[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bpb[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r3<8)&&(c1>=0)){
            int r5=r3;
            int c5=c1;
            if(bpb[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bpb[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        
        if((r4<8)&&(c2>=0)){
            int r5=r4;
            int c5=c2;
            if(bpb[r5][c5].getPiece()==null){
                String res1=Place.RCtoP(r5, c5);
                res.add(sv+p+res1);
            }else{
                if(bpb[r5][c5].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r5, c5);
                    res.add(sv+p+"x"+res1);
                }
            }
        }
        return res;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="--rookPosMovesString--">
    public ArrayList<String> rookPosMovesString(boardPlace bp){
        ArrayList<String> res=new ArrayList<String>();
        int r=bp.getR();
        int c=bp.getC();
        String p=Place.RCtoP(r, c).substring(0,1);
        String p1=Place.RCtoP(r, c).substring(1,2);
        String sv=bp.getPieceSV().substring(0,1);
        int r1=r+1;
        int r2=r-1;
        int c1=c+1;
        int c2=c-1;
        
        boolean b1=true;
        boolean b2=true;
        boolean b3=true;
        boolean b4=true;
        
        while((b1)&&(r1<8)){
            if(bab[r1][c].getPiece()==null){
                String res1=Place.RCtoP(r1, c);
                res.add(sv+p1+res1);
            }else{
                if(bab[r1][c].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r1, c);
                    res.add(sv+p1+"x"+res1);
                }
                b1=false;
            }
            r1++;
        }
        
        while((b2)&&(r2>=0)){
            
            if(bab[r2][c].getPiece()==null){
                String res1=Place.RCtoP(r2, c);
                res.add(sv+p1+res1);
            }else{
                if(bab[r2][c].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r2, c);
                    res.add(sv+p1+"x"+res1);
                }
                b2=false;
            }
            r2--;
        }
        
        while((b3)&&(c1<8)){
            if(bab[r][c1].getPiece()==null){
                String res1=Place.RCtoP(r, c1);
                res.add(sv+p+res1);
            }else{
                if(bab[r][c1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r, c1);
                    res.add(sv+p+"x"+res1);
                }
                b3=false;
            }
            c1++;
        }
        
        while((b4)&&(c2>=0)){
            if(bab[r][c2].getPiece()==null){
                String res1=Place.RCtoP(r, c2);
                res.add(sv+p+res1);
            }else{
                if(bab[r][c2].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r, c2);
                    res.add(sv+p+"x"+res1);
                }
                b4=false;
            }
            c2--;
        }
        
        return res;
    }
    
    
    
    public ArrayList<String> rookPosMovesString(boardPlace bp, boardPlace[][] bpb){
        ArrayList<String> res=new ArrayList<String>();
        int r=bp.getR();
        int c=bp.getC();
        String p=Place.RCtoP(r, c).substring(0,1);
        String p1=Place.RCtoP(r, c).substring(1,2);
        String sv=bp.getPieceSV().substring(0,1);
        int r1=r+1;
        int r2=r-1;
        int c1=c+1;
        int c2=c-1;
        
        boolean b1=true;
        boolean b2=true;
        boolean b3=true;
        boolean b4=true;
        
        while((b1)&&(r1<8)){
            if(bpb[r1][c].getPiece()==null){
                String res1=Place.RCtoP(r1, c);
                res.add(sv+p1+res1);
            }else{
                if(bpb[r1][c].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r1, c);
                    res.add(sv+p1+"x"+res1);
                }
                b1=false;
            }
            r1++;
        }
        
        while((b2)&&(r2>=0)){
            
            if(bpb[r2][c].getPiece()==null){
                String res1=Place.RCtoP(r2, c);
                res.add(sv+p1+res1);
            }else{
                if(bpb[r2][c].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r2, c);
                    res.add(sv+p1+"x"+res1);
                }
                b2=false;
            }
            r2--;
        }
        
        while((b3)&&(c1<8)){
            if(bpb[r][c1].getPiece()==null){
                String res1=Place.RCtoP(r, c1);
                res.add(sv+p+res1);
            }else{
                if(bpb[r][c1].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r, c1);
                    res.add(sv+p+"x"+res1);
                }
                b3=false;
            }
            c1++;
        }
        
        while((b4)&&(c2>=0)){
            if(bpb[r][c2].getPiece()==null){
                String res1=Place.RCtoP(r, c2);
                res.add(sv+p+res1);
            }else{
                if(bpb[r][c2].getPieceSide()!=bp.getPieceSide()){
                    String res1=Place.RCtoP(r, c2);
                    res.add(sv+p+"x"+res1);
                }
                b4=false;
            }
            c2--;
        }
        
        return res;
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="--promote--">
    public boardPlace[][] promote(String m){
        boolean b1=false;
        boardPlace[][] res=new boardPlace[8][8];
        boardPlace[][] bpb=new boardPlace[8][8];
        String s=m.substring(m.length()-1,m.length());
        ArrayList<String> places=Place.decifer(m);
        for(int i=0; i<m.length(); i++){
            if(m.substring(i,i+1).equals("x")){
                b1=true;
            }
        }
        
        if(b1){
            boardPlace[][] tempB=kill(m);
            boolean b4=true;
            for(int r=0; r<8; r++){
                for(int c=0; c<8; c++){
                    if(tempB[r][c].getPiece()==null){
                        boardPlace temp=new boardPlace(r,c,b4);
                        bpb[r][c]=temp;
                    }else{
                        boardPlace temp=new boardPlace(r,c,b4);
                        int side=tempB[r][c].getPieceSide();
                        int moved=tempB[r][c].getPiece().getMoved();
                        String sv=tempB[r][c].getPieceSV();
                        String place=Place.RCtoP(r,c);
                        Piece tempP=new Piece(side,place,sv);
                        tempP.setMoved(moved);
                        temp.setPiece(tempP);
                        bpb[r][c]=temp;
                    }
                    b4=!b4;
                }
                b4=!b4;
            }
        }else{
            boardPlace[][] tempB=swap(m);
            boolean b4=true;
            for(int r=0; r<8; r++){
                for(int c=0; c<8; c++){
                    if(tempB[r][c].getPiece()==null){
                        boardPlace temp=new boardPlace(r,c,b4);
                        bpb[r][c]=temp;
                    }else{
                        boardPlace temp=new boardPlace(r,c,b4);
                        int side=tempB[r][c].getPieceSide();
                        int moved=tempB[r][c].getPiece().getMoved();
                        String sv=tempB[r][c].getPieceSV();
                        String place=Place.RCtoP(r,c);
                        Piece tempP=new Piece(side,place,sv);
                        tempP.setMoved(moved);
                        temp.setPiece(tempP);
                        bpb[r][c]=temp;
                    }
                    b4=!b4;
                }
                b4=!b4;
            }
        }
        boolean b4=true;
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(bpb[r][c].getPiece()==null){
                    boardPlace temp=new boardPlace(r,c,b4);
                    res[r][c]=temp;
                }else{
                    boardPlace temp=new boardPlace(r,c,b4);
                    int side=bpb[r][c].getPieceSide();
                    int moved=bpb[r][c].getPiece().getMoved();
                    String sv=bpb[r][c].getPieceSV();
                    String place=Place.RCtoP(r, c);
                    Piece tempP=new Piece(side,place,sv);
                    tempP.setMoved(moved);
                    temp.setPiece(tempP);
                    res[r][c]=temp;
                }
                b4=!b4;
            }
            b4=!b4;
        }
        int[] cords=Place.toRC(places.get(1));
        int r1=cords[0];
        int c1=cords[1];
        int side=res[r1][c1].getPieceSide();
        String sv=s+res[r1][c1].getPieceSV().substring(1,res[r1][c1].getPieceSV().length());
        String place=Place.RCtoP(r1, c1);
        Piece tempP=new Piece(side,place,sv);
        res[r1][c1].setPiece(null);
        res[r1][c1].setPiece(tempP);
        return res;
    }
   //</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="--move--">
    public boardPlace[][] move(String m){
        boolean b1=false;
        boolean b2=false;
        boolean b3=false;
        
        ArrayList<String> places=Place.decifer(m);
        for(int i=0; i<m.length(); i++){
            if(m.substring(i,i+1).equals("x")){
                b1=true;
            }
        }
        for(int i=0; i<m.length(); i++){
            if(m.substring(i,i+1).equals("O")){
                b2=true;
            }
        }
        for(int i=0; i<m.length(); i++){
            if(m.substring(i,i+1).equals("=")){
                b3=true;
            }
        }
        if(b3){
            return promote(m);
        }else if(b2){
            return castle(m);
        }else if(b1){
            return kill(m);
        }else{
            return swap(m);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="swap">
    public boardPlace[][] swap(String m){
        ArrayList<String> places=Place.decifer(m);
        boardPlace[][] res=new boardPlace[8][8];
        boolean b4=true;
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                boardPlace temp=new boardPlace(r,c,b4);
                temp.setPiece(bab[r][c].getPiece());
                res[r][c]=temp;
                b4=!b4;
            }
            b4=!b4;
        }
        
        /*if(places.size()==1){
            String s=places.get(0).substring(0,1);
            if(turn==1){
                int[] cords2=Place.toRC(places.get(0));
                int r2=cords2[0];
                int c2=cords2[1];
                int r1=-1;
                int c1=-1;
                
                for(int r=0; r<8; r++){
                    for(int c=0; c<8; c++){
                        boardPlace bp1=bab[r][c];
                        if(bp1.getPiece()!=null){
                            if(bp1.getPieceSide()==1){
                                if(bp1.getPieceSV().substring(0,1).equals("p")){
                                    String p1=Place.RCtoP(r, c);
                                    if(p1.substring(0,1).equals(s)){
                                        r1=r;
                                        c1=c;
                                    }
                                }
                            }
                        }
                    }
                }
                Piece temp=res[r1][c1].getPiece();
                res[r1][c1].setPiece(res[r2][c2].getPiece());
                res[r2][c2].setPiece(temp);
                
                
            }else{
                int[] cords2=Place.toRC(places.get(0));
                int r2=cords2[0];
                int c2=cords2[1];
                int r1=-1;
                int c1=-1;
                for(int r=0; r<8; r++){
                    for(int c=0; c<8; c++){
                        boardPlace bp1=bab[r][c];
                        if(bp1.getPiece()!=null){
                            if(bp1.getPieceSide()==1){
                                if(bp1.getPieceSV().substring(0,1).equals("p")){
                                    String p1=Place.RCtoP(r, c);
                                    if(p1.substring(0,1).equals(s)){
                                        r1=r;
                                        c1=c;
                                    }
                                }
                            }
                        }
                    }
                }
                
                Piece temp=res[r1][c1].getPiece();
                res[r1][c1].setPiece(res[r2][c2].getPiece());
                res[r2][c2].setPiece(temp);
            }
        }else{*/
            
            int[] cords1= Place.toRC(places.get(0));
            int[] cords2=Place.toRC(places.get(1));
            int r1=cords1[0];
            int c1=cords1[1];
            int r2=cords2[0];
            int c2=cords2[1];
            Piece temp=res[r1][c1].getPiece();
            res[r1][c1].setPiece(res[r2][c2].getPiece());
            res[r2][c2].setPiece(temp);
        //}
        return res;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="kill">
    public boardPlace[][] kill(String m){
        ArrayList<String> places=Place.decifer(m);
        int[] cords1= Place.toRC(places.get(0));
        int[] cords2=Place.toRC(places.get(1));
        int r1=cords1[0];
        int c1=cords1[1];
        int r2=cords2[0];
        int c2=cords2[1];
        
        boardPlace[][] res=new boardPlace[8][8];
        boolean b4=true;
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                boardPlace temp=new boardPlace(r,c,b4);
                temp.setPiece(bab[r][c].getPiece());
                res[r][c]=temp;
                b4=!b4;
            }
            b4=!b4;
        }
        res[r2][c2].setPiece(res[r1][c1].getPiece());
        res[r1][c1].setPiece(null);
        return res;
        
    }
    //</editor-fold>
    
    public boardPlace[][] castle(String m){
        ArrayList<String> places=Place.decifer(m);
        int[] cords1= Place.toRC(places.get(0));
        int[] cords2=Place.toRC(places.get(1));
        int r1=cords1[0];
        int c1=cords1[1];
        int r2=cords2[0];
        int c2=cords2[1];
        boardPlace[][] res=new boardPlace[8][8];
        boolean b4=true;
        for(int r=0; r<8; r++){
            
            for(int c=0; c<8; c++){
                boardPlace temp=new boardPlace(r,c,b4);
                temp.setPiece(bab[r][c].getPiece());
                res[r][c]=temp;
                b4=!b4;
            }
            b4=!b4;
        }
        if(res[r2][c2].getPieceSV().substring(0,1).equals("R")){
            if(c2>c1){
                Piece temp=res[r2][c2].getPiece();
                res[r2][c2].setPiece(res[r1][c1].getPiece());
                res[r1][c1].setPiece(null);
                res[r2][c2-1].setPiece(temp);
            }else{
                Piece temp=res[r2][c2].getPiece();
                res[r2][c2].setPiece(res[r1][c1].getPiece());
                res[r1][c1].setPiece(null);
                res[r2][c2+1].setPiece(temp);
            }
            return res;
        }else{
            throw new ArithmeticException("CASTLE ERROR: SECOND PIECE NOT ROOK");
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="--blackKingDanger--">
    public boolean blackKingDanger(boardPlace[][] bpb){
        boolean res=false;
        ArrayList<ArrayList<Piece>> lists=getLists(bpb);
        ArrayList<Piece> a12=lists.get(0);
        //System.out.println("ALIST: "+a12);
        ArrayList<Piece> b12=lists.get(1);
        //System.out.println("BLIST: "+b12);
        for(int i=0; i<a12.size(); i++){
            Piece king=b12.get(0);
            Piece p1=a12.get(i);
            String kp=null;
            int kr=-1;
            int kc=-1;
            int[] cords=Place.toRC(p1.getPlace());
            int r=cords[0];
            int c=cords[1];
            
            
            for(int r1=0; r1<8; r1++){
                for(int c1=0; c1<8; c1++){
                    boardPlace bp1=bpb[r1][c1];
                    if(bp1.getPiece()!=null){
                        
                        if(bp1.getPiece().equals(king)){
                            kr=r1;
                            kc=c1;
                        }
                    }
                    
                }
            }
            
            
            kp=Place.RCtoP(kr, kc);
            boardPlace bp=bpb[r][c];
            String s1=p1.getSV().substring(0,1);
            ArrayList<String> step1=null;
            if(s1.equals("p")){
                step1=pawnPosMovesString(bp, bpb);
            }else if(s1.equals("Q")){
                step1=queenPosMovesString(bp, bpb);
            }else if(s1.equals("R")){
                step1=rookPosMovesString(bp, bpb);
            }else if(s1.equals("B")){
                step1=bishopPosMovesString(bp, bpb);
            }else if(s1.equals("N")){
                step1=knightPosMovesString(bp, bpb);
            }else if(s1.equals("K")){
                step1=kingPosMovesString(bp,bpb);
            }
            
            
            for(int k=0; k<step1.size(); k++){
                ArrayList<String> places=Place.decifer(step1.get(k));
                
                if(places.get(places.size()-1).equals(kp)){
                    return true;
                }
            }
            
        }
        return false;
    }
    
    public ArrayList<ArrayList<Piece>> getLists(boardPlace[][] bpb){
        ArrayList<Piece> a12=new ArrayList<Piece>();
        ArrayList<Piece> b12=new ArrayList<Piece>();
        ArrayList<ArrayList<Piece>> res= new ArrayList<ArrayList<Piece>>();
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(bpb[r][c].getPiece()!=null){
                    if(bpb[r][c].getPieceSV().substring(0,1).equals("K")){
                        if(bpb[r][c].getPieceSide()==1){
                            a12.add(bpb[r][c].getPiece());
                        }else{
                            b12.add(bpb[r][c].getPiece());
                        }
                    }
                }
            }
        }
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(bpb[r][c].getPiece()!=null){
                    if(!(bpb[r][c].getPieceSV().substring(0,1).equals("K"))){
                        if(bpb[r][c].getPieceSide()==1){
                            a12.add(bpb[r][c].getPiece());
                        }else{
                            b12.add(bpb[r][c].getPiece());
                        }
                    }
                }
            }
        }
        res.add(a12);
        res.add(b12);
        return res;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="--whiteKingDanger--">
    public boolean whiteKingDanger(boardPlace[][] bpb){
        boolean res=false;
        ArrayList<ArrayList<Piece>> lists=getLists(bpb);
        
        ArrayList<Piece> a12=lists.get(0);
        //System.out.println(a12);
        
        ArrayList<Piece> b12=lists.get(1);
        for(int i=0; i<b12.size(); i++){
            Piece king=a12.get(0);
            Piece p1=b12.get(i);
            String kp=null;
            int kr=-1;
            int kc=-1;
            int[] cords=Place.toRC(p1.getPlace());
            int r=cords[0];
            int c=cords[1];
            
            
            for(int r1=0; r1<8; r1++){
                for(int c1=0; c1<8; c1++){
                    boardPlace bp1=bpb[r1][c1];
                    if(bp1.getPiece()!=null){
                        
                        if(bp1.getPiece().equals(king)){
                            kr=r1;
                            kc=c1;
                        }
                    }
                    
                }
            }
            
            
            kp=Place.RCtoP(kr, kc);
            
            boardPlace bp=bpb[r][c];
            String s1=p1.getSV().substring(0,1);
            ArrayList<String> step1=null;
            if(s1.equals("p")){
                step1=pawnPosMovesString(bp, bpb);
            }else if(s1.equals("Q")){
                step1=queenPosMovesString(bp, bpb);
            }else if(s1.equals("R")){
                step1=rookPosMovesString(bp, bpb);
            }else if(s1.equals("B")){
                step1=bishopPosMovesString(bp, bpb);
            }else if(s1.equals("N")){
                step1=knightPosMovesString(bp, bpb);
            }else if(s1.equals("K")){
                step1=kingPosMovesString(bp,bpb);
            }
            
            
            for(int k=0; k<step1.size(); k++){
                ArrayList<String> places=Place.decifer(step1.get(k));
                
                if(places.get(1).equals(kp)){
                    return true;
                }
            }
            
        }
        return false;
    }
    //</editor-fold>
    
    
    
    public ArrayList<String> whitePosMoves(){
        ArrayList<String> res=new ArrayList<String>();
        
        for(int i=0; i<a1.size(); i++){
            Piece p1=a1.get(i);
            
            ArrayList<String> step1=null;
            String sv=p1.getSV().substring(0,1);
            String place=p1.getPlace();
            int[] cords=Place.toRC(place);
            
            int r1=cords[0];
            int c1=cords[1];
            /*for(int r=0; r<8; r++){
                for(int c=0; c<8; c++){
                    if(bab[r][c].getPiece()!=null){
                        if(bab[r][c].getPiece().equals(p1)){
                            r1=r;
                            c1=c;
                        }
                    }
                }
            }*/
            
            if(sv.equals("p")){
                step1=pawnPosMovesString(bab[r1][c1]);
            }else if(sv.equals("R")){
                step1=rookPosMovesString(bab[r1][c1]);
            }else if(sv.equals("N")){
                step1=knightPosMovesString(bab[r1][c1]);
            }else if(sv.equals("B")){
                step1=bishopPosMovesString(bab[r1][c1]);
            }else if(sv.equals("Q")){
                step1=queenPosMovesString(bab[r1][c1]);
            }else if(sv.equals("K")){
                step1=kingPosMovesString(bab[r1][c1]);
            }
            for(int k=0; k<step1.size(); k++){
                
                boardPlace[][] temp=move(step1.get(k));
                if(whiteKingDanger(temp)){
                    step1.remove(k);
                    k--;
                }
            }
            for(int k=0; k<step1.size(); k++){
                res.add(step1.get(k));
            }
            
        }
        return res;
    }
    
    public ArrayList<boardPlace[][]> whitePossiblePositions(){
        ArrayList<String> moves=whitePosMoves();
        ArrayList<boardPlace[][]> res=new ArrayList<boardPlace[][]>();
        for(int i=0; i<moves.size(); i++){
            boardPlace[][] temp=move(moves.get(i));
            res.add(temp);
        }
        return res;
    }
    public ArrayList<boardPlace[][]> blackPossiblePositions(){
        ArrayList<String> moves=blackPosMoves();
        ArrayList<boardPlace[][]> res=new ArrayList<boardPlace[][]>();
        for(int i=0; i<moves.size(); i++){
            boardPlace[][] temp=move(moves.get(i));
            res.add(temp);
        }
        return res;
    }
    
    public ArrayList<String> blackPosMoves(){
        
        
        ArrayList<String> res=new ArrayList<String>();
        for(int i=0; i<b1.size(); i++){
            Piece p1=b1.get(i);
            
            ArrayList<String> step1=null;
            String sv=p1.getSV().substring(0,1);
            int r1=-1;
            int c1=-1;
            for(int r=0; r<8; r++){
                for(int c=0; c<8; c++){
                    if(bab[r][c].getPiece()!=null){
                        if(bab[r][c].getPiece().equals(p1)){
                            r1=r;
                            c1=c;
                        }
                    }
                }
            }
            
            if(sv.equals("p")){
                step1=pawnPosMovesString(bab[r1][c1]);
            }else if(sv.equals("R")){
                step1=rookPosMovesString(bab[r1][c1]);
            }else if(sv.equals("N")){
                step1=knightPosMovesString(bab[r1][c1]);
            }else if(sv.equals("B")){
                step1=bishopPosMovesString(bab[r1][c1]);
            }else if(sv.equals("Q")){
                step1=queenPosMovesString(bab[r1][c1]);
            }else if(sv.equals("K")){
                step1=kingPosMovesString(bab[r1][c1]);
            }
            
            for(int k=0; k<step1.size(); k++){
                
                boardPlace[][] temp=move(step1.get(k));
                if(blackKingDanger(temp)){
                    step1.remove(k);
                    k--;
                }
            }
            for(int k=0; k<step1.size(); k++){
                res.add(step1.get(k));
            }
            
        }
        return res;
    }
    @Override
    public String toString(){
        String res="";
        for(int r=0; r<8; r++){
            String s1="";
            for(int c=0; c<8; c++){
                s1+=bab[r][c].toString()+" ";
            }
            s1+="\n";
            res+=s1;
        }
        
        
        return res;
        
    }
}