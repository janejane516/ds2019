
public interface BinaryTree
{
    public boolean isEmpty();
    public Object root();
    public BinaryTree removeLeftSubtree();
    public BinaryTree removeRightSubtree();


    public String preOrder();
    public String inOrder();
    public String postOrder();
    public int size();
    public int height();

}
