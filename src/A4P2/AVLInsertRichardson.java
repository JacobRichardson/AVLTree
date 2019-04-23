/* 
 * A4 P2 AVL Tree
 *  
 * Jacob Richardson 
 * Assignment 4 Part 2
 * Assigned Date: April 11, 2019 
 * Due Date: April 22, 2019 
 * Submission Date: April 22, 2019 
 */ 

package A4P2;

import java.util.*;
import java.io.*;

public class AVLInsertRichardson 
{
	
	//Variable to determine if the tree has grown higher. This is initally set to false.
	private boolean h = false;
	//Variable for a pointer to a node.
	private AVLNodeRichardson p1;
	//Variable for a pointer to a node.
	private AVLNodeRichardson p2; 
	//Root node.
	private AVLNodeRichardson root;
	
	
	/*
	 * This method is the constructor.
	 * @param fileName This is the string of the name of the input file.
	 */
	public AVLInsertRichardson(String fileName)
	{
		
		try {
			
			//Create a scanner object for the file.
			Scanner sc = new Scanner(new File(fileName));
			//Create a print writer for the output file.
			PrintWriter w = new PrintWriter("output.txt");
			
			//While the input file has a next int.
			while(sc.hasNextInt()) 
			{
				//Grab the next int.			
				int key = sc.nextInt();
				//Write to the output file.
				w.print("Insert " + key + ": ");
				//Set root equal to the insert of k and root.
				root = insert(key, root);
				//Output the print tree to the output file.
				w.println(printTree(root));
			}
			
			//Close the print writer.
			w.close();
			
			//Close the file scanner.
			sc.close();
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * This method inserts and AVL node into the tree.
	 * @param p This is the node that is going to be inserted into the AVL tree.
	 */
	public AVLNodeRichardson insert(int k, AVLNodeRichardson p)
	{
			
		//If p is null.
		if(p == null) 
		{
			//Insert the AVL node.
			p = new AVLNodeRichardson(k, 0, null, null);
			
			//Set h equal to true.
			h = true;
		}
		else if( k < p.getKey())
		{
			//Set p's left node equal to the insert of k and p's left node.
			p.setLeftNode(insert(k, p.getLeftNode()));
			
			//If the tree has grown on the left.
			if(h)
			{
				//Switch statment on p's balance factor.
				switch(p.getBalanceFactor())
				{
					case 0:
						//Set p's balance factor to -1.
						p.setBalanceFactor(-1);
						//Break out of the case.
						break;
					case 1:
						//Set the bance to zero.
						p.setBalanceFactor(0);
						//Set h to false.
						h = false;
						//Break out of the case.
						break;
					case -1:
						//Set p1 equal to the left node of p.
						p1 = p.getLeftNode();
						//If the balance factor of p1 == -1.
						if(p1.getBalanceFactor() == -1)
						{
							//Preform an LL rotation.
							p = LLRotation(p);
							//Set h to false;
							h = false;
						}
						else
						{
							//Preform an LR rotation.
							p = LRRotation(p);
							//Set h to false;
							h = false;
						}	
				}			
			}
		}
		else
		{
			//Set the right node of p equal to insert of k and p's right node.
			p.setRightNode(insert(k, p.getRightNode()));
			
			//If the tree has grown on the right.
			if(h)
			{
				//Switch statement on p's balance factor.
				switch(p.getBalanceFactor())
				{
					case 0: 
						//Set p's balance factor to 1.
						p.setBalanceFactor(1); 
						//Break out of the case.
						break;
					case -1:
						//Set the balance factor of p to 0.
						p.setBalanceFactor(0);
						//Set h to false;
						h = false;
						//Break out of the case.
						break;
					case 1:
						//Set p1 = to the right node of p.
						p1 = p.getRightNode();
						//If the balance factor of p1 == 1
						if(p1.getBalanceFactor() == 1)
						{
							//Preform a RR rotation.
							p = RRRotation(p);
							//Set h to false;
							h = false;
						}
						else
						{
							//Preform RL rotation
							p = RLRotation(p);
							//Set h to false;
							h = false;
						}

				}
			}
		}

		//Return p.
		return p;
	}
	
	/*
	 * This method preforms a left left rotation.
	 * @return P is the the rotated node.
	 * @param P is the unbalanced node.
	 */
	public AVLNodeRichardson LLRotation(AVLNodeRichardson p)
	{
		//Set p1 to the left node of p. P is a pointer to the unbalanced node.
		p1 = p.getLeftNode();
		//Set the left node of p to the right node of p1.
		p.setLeftNode(p1.getRightNode());
		//Set the right node of p1 to p.
		p1.setRightNode(p);
		//Set the balanceFactor of p to 0.
		p.setBalanceFactor(0);
		//Set the balanceFactor of p1 to 0.
		p1.setBalanceFactor(0);
		//Set p equal to p1;
		p = p1;

		//Return p;
		return p;

	}
	
	/*
	 * This method preforms a left right rotation.
	 * @return P is the the rotated node.
	 * @param P is the unbalanced node.
	 */
	public AVLNodeRichardson LRRotation(AVLNodeRichardson p)
	{
		//Set p1 to the left node of p. P is a pointer to the unbalanced node.
		p1 = p.getLeftNode();
		//Set p2 to the right node of p1.
		p2 = p1.getRightNode();
		//Set the right node of p1 equal to the left node of p2.
		p1.setRightNode(p2.getLeftNode());
		//Set the left node of p equal to the right node of p2.
		p.setLeftNode(p2.getRightNode());
		//Set the right node of p2 equal to p.
		p2.setRightNode(p);
		//Set the left node of p2 equal to p1.
		p2.setLeftNode(p1);

		//If the balance factor of p2 equals 0.
		if(p2.getBalanceFactor() == 0)
		{
			//Set p's and p1's balance factor to 0.
			p.setBalanceFactor(0);
			p1.setBalanceFactor(0);
		}
		//else if the balance factor of p equals -1.
		else if(p2.getBalanceFactor() == -1)
		{
			//Set p's balance factor to 1.
			p.setBalanceFactor(1);
			//Set p1's balance factor to 0.
			p1.setBalanceFactor(0);
		}
		//else if the balance factor of p equals 1.
		else if(p2.getBalanceFactor() == 1)
		{
			//Set p's balance factor to 0.
			p.setBalanceFactor(0);
			//Set p1's balance factor to -1.
			p1.setBalanceFactor(-1);
		}
	
		//Set p equal to p2.
		p = p2;
		//Set p's balance factor to 0.
		p.setBalanceFactor(0);
		//Return p;
		return p;
		
	}
	
	/*
	 * This method preforms a right right rotation.
	 * @return P is the the rotated node.
	 * @param P is the unbalanced node.
	 */
	public AVLNodeRichardson RRRotation(AVLNodeRichardson p)
	{
		//Set p1 to the right node of p. P is a pointer to the unbalanced node.
		p1 = p.getRightNode();
		//Set the right node of p to the left node of p1.
		p.setRightNode(p1.getLeftNode());
		//Set the left node of p1 to p.
		p1.setLeftNode(p);
		//Set the balanceFactor of p to 0.
		p.setBalanceFactor(0);
		//Set the balanceFactor of p1 to 0.
		p1.setBalanceFactor(0);
		//Set p equal to p1;
		p = p1;

		//Return p.
		return p;
	}
	
	/*
	 * This method preforms a right left rotation.
	 * @return P is the the rotated node.
	 * @param P is the unbalanced node.
	 */
	public AVLNodeRichardson RLRotation(AVLNodeRichardson p)
	{
		//Set p1 to the right node of p. P is a pointer to the unbalanced node.
		p1 = p.getRightNode();
		//Set p2 to the left node of p1.
		p2 = p1.getLeftNode();
		//Set the left node of p1 equal to the right node of p2.
		p1.setLeftNode(p2.getRightNode());
		//Set the right node of p equal to the left node of p2.
		p.setRightNode(p2.getLeftNode());
		//Set the left node of p2 equal to p.
		p2.setLeftNode(p);
		//Set the right node of p2 equal to p1.
		p2.setRightNode(p1);

		//If the balance factor of p2 equals 0.
		if(p2.getBalanceFactor() == 0)
		{
			//Set p's and p1's balance factor to 0.
			p.setBalanceFactor(0);
			p1.setBalanceFactor(0);
		}
		//else if the balance factor of p equals -1.
		else if(p2.getBalanceFactor() == -1)
		{
			//Set p's balance factor to 0.
			p.setBalanceFactor(0);
			//Set p1's balance factor to 1.
			p1.setBalanceFactor(1);
		}
		//else if the balance factor of p equals 1.
		else if(p2.getBalanceFactor() == 1)
		{
			//Set p's balance factor to -1.
			p.setBalanceFactor(-1);
			//Set p1's balance factor to 0.
			p1.setBalanceFactor(0);
		}
		
		//Set p equal to p2.
		p = p2;
		//Set p's balance factor to 0.
		p.setBalanceFactor(0);
		//Retrun p.
		return p;
	}

	/*
	 * This method prints out a fully parenthesized expression of the AVL tree.
	 */
	public String printTree(AVLNodeRichardson node)
	{
		//String that prints out the tree.
		String s = "";
		//If the node is not null.
		if(node != null)
		{
			//Add the key of the tree plus the print tree of the left and right node.
			s += node.getKey() + "(" + printTree(node.getLeftNode()) + ")" + "(" + printTree(node.getRightNode()) + ")";
		}
		//Return s.
		return s;
	}
	
	/*
	 * This method is the main method of the class.
	 */
	public static void main (String args[])
	{
		//If args.length does not equal 1.
		if(args.length != 1)
		{
			//Print error message.
			System.out.println("One argument must be provided.");
			//Exit with a non zero exit status.
			System.exit(1);
		}
		
		//Pass the file name to the constructor.
		new AVLInsertRichardson(args[0]);
		
	}
}
