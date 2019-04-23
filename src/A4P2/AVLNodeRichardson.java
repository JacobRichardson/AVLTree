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

public class AVLNodeRichardson 
{
	//Instance Variables.
	private int balanceFactor;
	private int key;
	private AVLNodeRichardson leftNode;
	private AVLNodeRichardson rightNode;
	
	
	/*
	 * This is the constructor for the class.
	 * @param BalanceFactor Integer to represent the nodes balance factor.
	 */
	public AVLNodeRichardson(int key, int balanceFactor, AVLNodeRichardson leftNode, AVLNodeRichardson rightNode)
	{
		this.key = key;
		this.balanceFactor = balanceFactor;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
	
	/*
	 * This method returns the key instance variable.
	 */
	public int getKey()
	{
		return key;
	}
	
	/*
	 * This method returns the balanceFactor instance variable.
	 */
	public int getBalanceFactor()
	{
		return balanceFactor;
	}
	
	/*
	 * This method sets the instance variable balanceFactor to the value passed into the method.
	 * @param balanceFactor The desired value to set balanceFactor to.
	 */
	public void setBalanceFactor(int balanceFactor)
	{
		this.balanceFactor = balanceFactor;
	}
	
	/*
	 * This method returns the leftNode instance variable.
	 */
	public AVLNodeRichardson getLeftNode()
	{
		return leftNode;
	}
	
	/*
	 * This method returns the rightNode instance variable.
	 */
	public AVLNodeRichardson getRightNode()
	{
		return rightNode;
	}
	
	/*
	 * This method sets the instance variable of the left node to the node passed in.
	 */
	public void setLeftNode(AVLNodeRichardson node)
	{
		this.leftNode = node;
	}
	
	/*
	 * This method sets the instance variable of the right node to the node passed in.
	 */
	public void setRightNode(AVLNodeRichardson node)
	{
		this.rightNode = node;
	}
}
