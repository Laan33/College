import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class treeIO {

    static String filePath = System.getProperty("user.dir") + "\\";
    
    public void storeTree(BinaryTree<String> tree) {
        System.out.println("File path: " + filePath);
        
        // Serialization
        try {
            System.out.println("Saving tree");
            FileOutputStream file = new FileOutputStream("20QuestionTree.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
    
            out.writeObject(tree);
    
            out.close();
            file.close();
        } catch (IOException ex) {
            System.out.println("IOException caught");
            System.exit(1);
        }
    }
    
    public BinaryTree<String> loadTree() {
        BinaryTree<String> tree = null;
    
        // Deserialization
        try {
            System.out.println("Loading tree from file");
            FileInputStream file = new FileInputStream("20QuestionTree.txt");
            ObjectInputStream in = new ObjectInputStream(file);
    
            tree = (BinaryTree<String>) in.readObject();
            System.out.println("Tree: ");
            tree.inorderTraverse();
    
            in.close();
            file.close();
    
            return tree;
        } catch (IOException ex) {
            System.out.println("IOException caught");
            System.exit(1);
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException caught");
            System.exit(1);
            return null;
        }
    }
}
