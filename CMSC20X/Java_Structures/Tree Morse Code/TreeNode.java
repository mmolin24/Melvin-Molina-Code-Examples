/**
 * 
 * @author Melvin m
 * CMSC 204
 * @param <T> data type of TreeNode
 */
public class TreeNode<T> {
	
	protected T data;
	protected TreeNode<T> leftC;
	protected TreeNode<T> rightC;
	/**
	 * Create a new TreeNode with left and right child set to null and data set to the dataNode
	 * @param dataNode the data to be stored in the TreeNode
	 */
	public TreeNode(T dataNode)
	{
		this.data = dataNode;
		this.leftC = null;
		this.rightC = null;
	}
	/**
	 * used for making deep copies
	 * @param node node to make copy of
	 */
	public TreeNode(TreeNode<T> node)
	{
		new TreeNode<T>(node);
	}
	/**
	 * Return the data within this TreeNode
	 * @return the data within this TreeNode
	 */
	public T getData()
	{
		return data;
	}

}
