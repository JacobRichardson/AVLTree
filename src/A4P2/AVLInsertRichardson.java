/* 
 * A4 P1 AVL Tree
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
	/*
	 * This method is the constructor.
	 * @param fileName This is the string of the name of the input file.
	 */
	public AVLInsertRichardson(String fileName)
	{
		try {
			
			//Create a scanner object for the file.
			Scanner sc = new Scanner(new File(fileName));
			
			//TODO: Use split by \t to get all the keys in an array.
			
			PrintWriter w = new PrintWriter("output.txt");
			
			
			
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
		//TODO: Figure out how to get h. This means how to tell if the tree has grown in height.
		
		//Variable to determine if the tree has grown higher. This is initally set to false.
		Boolean h = false;
		
		AVLNodeRichardson p1;
		
		//If p is null.
		if(p == null) 
		{
			
		}
		else if( k < p.getKey())
		{
			//If the tree has grown on side.
			if(h)
			{
				
				switch(p.getBalanceFactor())
				{
					case 0:
						//Set the balance factor to one less than what is was before.
						p.setBalanceFactor(p.getBalanceFactor() - 1);
					case 1:
						//Set the bance to zero.
						p.setBalanceFactor(0);
						//Set h to false.
						h = false;
					case -1:
						//Set p1 equal to the left node of p.
						p1 = p.getLeftNode();
						//If the balance factor of p1 == -1.
						if(p1.getBalanceFactor() == -1)
						{
							//Preform an LL rotation.
							LLRotation(p);
							//Set h to false;
							h = false;
						}
						else
						{
							//Preform an LR rotation.
							LRRotation(p);
							//Set h to false;
							h = false;
						}
						
				}		
					
			}
		}
		else
		{
			//Set the right node of p equal to the node that is return with k and the right node of p.
			p.setRightNode(insert(k, p.getRightNode()));
			
			//If the tree has grown on side.
			if(h)
			{
				switch(p.getBalanceFactor())
				{
					case 0: 
						//Set the balanceFactor to one more than what it was before.
						p.setBalanceFactor(p.getBalanceFactor() + 1); 
					case 1:
						//Set p1 = to the right node of p.
						p1 = p.getRightNode();
						//If the balance factor of p1 == 1
						if(p1.getBalanceFactor() == 1)
						{
							//Preform a RR rotation.
							RRRotation(p);
							//Set h to false;
							h = false;
						}
						else
						{
							//Preform RL rotation
							RLRotation(p);
							//Set h to false;
							h = false;
						}
					case -1:
						//Set the balance factor of p to 0.
						p.setBalanceFactor(0);
						//Set h to false;
						h = false;
				}
			}
		}
		
		
		//TODO: CHANGE RETURN NULL TO SOMETHHING ELSE.
		//Return an AVL node.
		return null;
		
	}
	
	/*
	 * This method preforms a left left rotation.
	 * @param P is the unbalanced node.
	 */
	public void LLRotation(AVLNodeRichardson p)
	{
		//Set p1 to the left node of p. P is a pointer to the unbalanced node.
		AVLNodeRichardson p1 = p.getLeftNode();
		//Set the left node of p to the right node of p1.
		p.setLeftNode(p1.getRightNode());
		//Set the right node of p1 to p.
		p1.setRightNode(p);
		//Set p equal to p;
		p = p1;
		//Set the balanceFactor of p to 0.
		p.setBalanceFactor(0);
		//Set the balanceFactor of p1 to 0.
		p1.setBalanceFactor(0);

	}
	
	/*
	 * This method preforms a left right rotation.
	 * @param P is the unbalanced node.
	 */
	public void LRRotation(AVLNodeRichardson p)
	{
		//Set p1 to the left node of p. P is a pointer to the unbalanced node.
		AVLNodeRichardson p1 = p.getLeftNode();
		//Set p2 to the right node of p1.
		AVLNodeRichardson p2 = p1.getRightNode();
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
		
	}
	
	/*
	 * This method preforms a right right rotation.
	 * @param P is the unbalanced node.
	 */
	public void RRRotation(AVLNodeRichardson p)
	{
		//Set p1 to the right node of p. P is a pointer to the unbalanced node.
		AVLNodeRichardson p1 = p.getRightNode();
		//Set the right node of p to the left node of p1.
		p.setRightNode(p1.getLeftNode());
		//Set the left node of p1 to p.
		p1.setLeftNode(p);
		//Set p equal to p;
		p = p1;
		//Set the balanceFactor of p to 0.
		p.setBalanceFactor(0);
		//Set the balanceFactor of p1 to 0.
		p1.setBalanceFactor(0);

	}
	
	/*
	 * This method preforms a right left rotation.
	 * @param P is the unbalanced node.
	 */
	public void RLRotation(AVLNodeRichardson p)
	{
		//Set p1 to the right node of p. P is a pointer to the unbalanced node.
		AVLNodeRichardson p1 = p.getRightNode();
		//Set p2 to the left node of p1.
		AVLNodeRichardson p2 = p1.getLeftNode();
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
			//Set p1's balance factor to 0.
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
	}

	/*
	 * This method prints out a fully parenthesized expression of the AVL tree.
	 */
	public void printTree()
	{
		
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
