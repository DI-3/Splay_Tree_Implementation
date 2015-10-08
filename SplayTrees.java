// Splay trees insertion involves four cases --

 /* Two rotations from the */
 //1. left left - Rotate right at the root twice
 //2. left-right - Rotate left at the left child and then at rotate right at root.
 //3. right right - Rotate left at the root twice
 //4. right-left - Rotate right at right child and then rotate left at root.

import java.util.*;

 class TNode{

    char data ;
    TNode lchild;
    TNode rchild;
    TNode parent;
    int N;

    public TNode(char data){

      this.data = data;
      this.lchild = null;
      this.rchild = null;
      this.N = 1;

    }
 }

 class SplayTrees{

     TNode root ;

     public SplayTrees(){

        root = null;
     }

     public TNode createBinTree(TNode h,char data){

        if(h == null){
             h = new TNode(data);
             return h; 
        }
        if(data < h.data)
            h.lchild = createBinTree(h.lchild,data); 
        else 
        	h.rchild = createBinTree(h.rchild,data); 
        	         
        return h;
     }


     // method to rotate right
     public TNode rotR(TNode h){
 
           TNode temp = h.lchild ;
           h.lchild = temp.rchild;
           temp.rchild = h;
           return temp;
     }


    public TNode rotL(TNode h){
           
           TNode temp = h.rchild;
           h.rchild = temp.lchild;
           temp.lchild = h;
           return temp;

    }

     //insert method that rotates recursively

    public TNode insert(TNode h,char data){

     
       if(h == null) { return new TNode(data); }

       if(data < h.data){  h.lchild = insert(h.lchild,data);  return rotR(h);  }
       else{  h.rchild = insert(h.rchild,data);      }
       return rotL(h);

     } 
   

     public void insert(char data){

        this.root = insert(this.root,data);

     }

     public void createTree(char data){
   
        this.root = createBinTree(this.root,data);
     }

     Queue<TNode> q = new LinkedList<TNode>();
   
     public void levelorder(TNode node){

        q.add(node);
        TNode temp;
        while(!q.isEmpty()){
            temp =q.remove();
            System.out.println(temp.data + "  :  "+ temp.N);
            if(temp.lchild !=null) q.add(temp.lchild);
            if(temp.rchild !=null) q.add(temp.rchild);
        }
     }
     
     public void inorder(TNode node){

         if(node == null) return; 
         inorder(node.lchild);
         System.out.println(node.data);
         inorder(node.rchild);

     }

    
    public int sizeofNode(TNode node){

      
      if(node == null) return 0;
      else{
           node.N = 1;
           node.N = node.N + sizeofNode(node.lchild);
           node.N = node.N + sizeofNode(node.rchild);
     }
      return node.N ;
    } 
  

    public char partR(TNode h,int k){

      int t = (h.lchild == null) ? 0 : h.lchild.N;

      if(t > k){ return partR(h.lchild,k);}
      if(t < k){ return partR(h.rchild,k-t-1);}
      return h.data ; 
    }


   // ***********************************************
   // Code to insert the spla node into the tree  ***
   //************************************************


    public TNode splayInsert(TNode h,char data){

        if(h == null) return new TNode(data);

         if(data < h.data){
        
               if(h.lchild == null) { h.lchild = new TNode(data);
                          //rotation code
                          return rotR(h);
               }
               if(data < h.lchild.data)
               { h.lchild.lchild = splayInsert(h.lchild.lchild,data);  h = rotR(h); }
               else
               { h.lchild.rchild = splayInsert(h.lchild.rchild,data);  h.lchild = rotL(h.lchild); }

               return rotR(h);
         }else{

               if(h.rchild == null){ h.rchild = new TNode(data);
                                return rotL(h);               
                } 
               if(data > h.rchild.data)
               { h.rchild.rchild = splayInsert(h.rchild.rchild,data); h = rotL(h);}
               else
               { h.rchild.lchild = splayInsert(h.rchild.lchild,data); h.rchild = rotR(h.rchild); }

               return rotL(h);  
         }


    }

    public void splay(char data){

      this.root = splayInsert(this.root,data);

    }

    public TNode deleteNode(TNode node,char data){

          if(node == null) return node;

          if(node.data > data) node.lchild = deleteNode(node.lchild,data);
          else if(node.data < data) node.rchild = deleteNode(node.rchild,data);

          else{

                 if(node.lchild == null)
                     return node.rchild ; 
                 else if(node.rchild == null)
                      return node.lchild ;
                 else{
                        //get the inorder successor
                        TNode temp = inorderSuccessor(node.rchild);
                        node.data = temp.data ;
                        node.rchild = deleteNode(node.rchild,temp.data);

                 }       
          }
          return node;
    }

    public static TNode inorderSuccessor(TNode node){

          if(node.lchild == null) return node;

          return inorderSuccessor(node.lchild);

    }    

    /* **************** Main for General Trees ******************** */ 
     // public static void main(String[] args){

     //    SplayTrees trr = new SplayTrees();
     //    trr.createTree('A');
     //    trr.createTree('S');
     //    trr.createTree('E');
     //    trr.createTree('X');
     // 	  trr.createTree('C');
     // 	  trr.createTree('R');
     // 	  //trr.createTree('T');
     // 	  trr.createTree('H');
  		 //  //trr.createTree('M');
     //    trr.inorder(trr.root);

     //    //trr.insert('G');
     //    System.out.println("After insertion of the new NOde");
     //    int size = trr.sizeofNode(trr.root);
          
     //    trr.insert('G');
     //    size = trr.sizeofNode(trr.root);
     //    trr.deleteNode(trr.root,'G');
     //    trr.levelorder(trr.root);
     //    //trr.inorder(trr.root);
     //    //System.out.println("K th node is : Root data is "+ trr.root.data);
     //    //System.out.println(trr.partR(trr.root,8));
     // }
 
     /* **************** Main for Splayy Trees ******************** */ 
     public static void main(String[] args){

      SplayTrees trr = new SplayTrees();
       trr.splay('A'); 
       trr.splay('S');
       trr.splay('E');
       trr.splay('R');
       trr.splay('C');
       trr.splay('H');
       trr.splay('I');
       trr.splay('N');
       trr.splay('G');
       int  size = trr.sizeofNode(trr.root);
        trr.levelorder(trr.root); 


     }  



 }