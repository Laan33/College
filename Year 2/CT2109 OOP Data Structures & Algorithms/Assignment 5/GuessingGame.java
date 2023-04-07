
//import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class GuessingGame {
    static GuessingGame userSession = new GuessingGame();
    static treeIO treeIO = new treeIO();
    String treeStr = "";

    public static void main(String[] args) {
        System.out.println("Creating a base tree ...");
        BinaryTree<String> testTree = new BinaryTree<String>();

        createTree2(testTree);
        BinaryNodeInterface<String> currentNode = testTree.getRootNode();

        // Display some statistics about it
        System.out.println("\nSome statistics about the test tree ...");
        displayStats(testTree);

        // Perform in-order traversal
        System.out.println("\nIn-order traversal of the tree, printing each node when visiting it ...");

        System.out.println("\n Tree represenation in text: ");
        displayTree(testTree);

        userSession.menuChoice(testTree, currentNode);

    }

    public static void createTree2(BinaryTree<String> tree) {
        // To create a tree, build it up from the bottom:
        // create subtree for each leaf, then create subtrees linking them,
        // until we reach the root.


        BinaryTree<String> oTree = new BinaryTree<String>("Is it Prema Racing in Formula 2");
        BinaryTree<String> nTree = new BinaryTree<String>("Is it Ferrari");
        BinaryTree<String> mTree = new BinaryTree<String>("Is it Ayrton Senna");
        BinaryTree<String> lTree = new BinaryTree<String>("Is it Michael Schumacher");
        BinaryTree<String> kTree = new BinaryTree<String>("Is it Christ the Redeemer in Brazil");
        BinaryTree<String> jTree = new BinaryTree<String>("Is it the Taipei 101");
        BinaryTree<String> iTree = new BinaryTree<String>("Is it the Statue of Liberty");
        BinaryTree<String> hTree = new BinaryTree<String>("Is it the leaning tower of Pisa");

        // Now the subtrees joining leaves:
        BinaryTree<String> gTree = new BinaryTree<String>("Are you thinking of an F1 team", nTree, oTree);
        BinaryTree<String> fTree = new BinaryTree<String>("Are they from Europe", lTree, mTree);
        BinaryTree<String> eTree = new BinaryTree<String>("Is it in Asia", jTree, kTree);
        BinaryTree<String> dTree = new BinaryTree<String>("Is it in Europe", hTree, iTree);

        BinaryTree<String> cTree = new BinaryTree<String>("Are you thinking of a famous F1 driver", fTree, gTree);
        BinaryTree<String> bTree = new BinaryTree<String>("Is it in the northern hemisphere", dTree, eTree);

        // Now the root
        tree.setTree("Are you thinking of a famous monument or building", bTree, cTree);
    } // end createTree1

    public void interactiveQuestions(BinaryTree<String> tree, BinaryNodeInterface<String> currentNode) {
        // Continues loop until system.exit condition or correct leaf is chosen by user
        System.out.println("Root node: " + tree.getRootNode() + " root data: " + tree.getRootData()); 
        currentNode = tree.getRootNode(); // Set current node to root node

        try (Scanner scanner = new Scanner(System.in)) { 
            String strInput, prompt; 

            while (true) { 
                while (!currentNode.isLeaf()) { // While current node is not a leaf - aka quesiton
                    prompt = currentNode.getData() + "? (Y/N)"; // Ask the question
                    strInput = userSession.getUserInput(prompt); 
                    strInput = strInput.toUpperCase();

                    switch (strInput) { // Update current node based on answer
                        case "Y":
                            currentNode = currentNode.getLeftChild();
                            break;
                        case "N":
                            currentNode = currentNode.getRightChild();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Input is invalid, enter either Y or N");
                            break;
                    }
                }
                leafGuess(tree, currentNode); // We have reached a leaf, make the guess
            }
        }
    }

    public static void leafGuess(BinaryTree<String> tree, BinaryNodeInterface<String> currentNode) {
        System.out.println("Please use pop-up window to answer the question\n");
        String question = currentNode.getData() + "? (Y/N)"; // Make guess

        String strInput = userSession.getUserInput(question);
        strInput = strInput.toUpperCase();

        switch (strInput) { // Decide if we won, user makes new question & node or invalid input
            case "Y":
                System.out.println("I win!, bringing you to the menu choices\n");
                JOptionPane.showMessageDialog(null, "I win!");
                userSession.menuChoice(tree, currentNode);
                break;
            case "N":
                System.out.println("I lose, please follow the instructions in the pop-up window\n");
                newUserGenNode(tree, currentNode);
                break;
            default:
                System.out.println("Input is invalid, enter either Y or N");
                leafGuess(tree, currentNode);
                break;
        }
    }

    // Menu method for user 
    public void menuChoice(BinaryTree<String> tree, BinaryNodeInterface<String> currentNode) {
        String question = "Please select an option from the menu below \n1. Play (again) \n2. Store the tree \n3. Load a stored tree \n4. Quit";
        String strInput = userSession.getUserInput(question);

        switch (strInput) {
            case "1": // Play again
                userSession.interactiveQuestions(tree, currentNode);
                break;
            case "2": // Store the tree
                treeIO.storeTree(tree);
                currentNode = tree.getRootNode();
                JOptionPane.showMessageDialog(null, "Tree stored");
                menuChoice(tree, currentNode);
                break;
            case "3": // Load a stored tree
                treeIO.loadTree();
                currentNode = tree.getRootNode();
                JOptionPane.showMessageDialog(null, "Tree loaded");
                menuChoice(tree, currentNode);
                break;
            case "4": // Quit
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid input, please enter a number between 1 and 4");
                menuChoice(tree, currentNode);
                break;
        }

    }

    // if we hit a wrong guess we make new node and question
    public static void newUserGenNode(BinaryTree<String> tree, BinaryNodeInterface<String> currentNode) {
        if (currentNode == null) {
            return;
        }
        String currentNodeAns = currentNode.getData(); // Get the current node data

        String prompt = "What is it that came to mind?";
        String nodeAnswer = userSession.getUserInput(prompt);
        nodeAnswer = "Is it " + nodeAnswer; 

        prompt = "Type a question to differentiate between " + nodeAnswer + " and " + currentNodeAns + "?";
        String nodeQuesiton = userSession.getUserInput(prompt);
        currentNode.setData(nodeQuesiton); // Put the question node in place of the current node

        // Checking whether the answer is yes or no to the new question
        prompt = "Is the answer to the new question yes or no for " + nodeAnswer + " ? (Y/N)";
        String inputStr = userSession.getUserInput(prompt);
        inputStr = inputStr.toUpperCase();

        // Setting the left and right child nodes
        // Current node data being moved down to either left or right child
        switch (inputStr) { 
            case "Y":
                System.out.println("The left node: " + nodeAnswer + " right node: " + currentNodeAns);
                currentNode.setLeftChild(new BinaryNode<String>(nodeAnswer));
                currentNode.setRightChild(new BinaryNode<String>(currentNodeAns)); // Moving current node to the new
                                                                                   // question node
                break;
            case "N":
                System.out.println("The left node: " + currentNodeAns + " right node: " + nodeAnswer);
                currentNode.setLeftChild(new BinaryNode<String>(currentNodeAns));
                currentNode.setRightChild(new BinaryNode<String>(nodeAnswer));
                break;
            default:
                System.out.println("Input is invalid, enter either Y or N");
                break;
        }
        currentNode = tree.getRootNode(); // Resetting the current node to the root node

        userSession.menuChoice(tree, currentNode); // Returning to the menu choice
    }

    public static void displayTree(BinaryTree<String> tree) { // Displaying the tree
        int count = 1; // Counting the nodes
        Queue<BinaryNodeInterface<String>> queue = new LinkedList<>(); // Using a queue to display the tree
        queue.add(tree.getRootNode());

        while (!queue.isEmpty()) {  // While the queue is not empty
            BinaryNodeInterface<String> node = queue.remove();
            System.out.println("Node " + count + ": " + node.getData()); // Displaying the node
            count++;

            BinaryNodeInterface<String> left = node.getLeftChild();
            BinaryNodeInterface<String> right = node.getRightChild();

            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }
        for (queue.size(); queue.size() > 0; queue.size()) { 
            System.out.println(queue.remove()); // Displaying the tree
        }
    }

    public String getUserInput(String question) { //input validation
        String inputStr = JOptionPane.showInputDialog(null, question);

        if (inputStr == null) {
            System.exit(0);
        } else if (inputStr.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter a valid input");
            return getUserInput(question);
        } else {
            return inputStr;
        }
        return inputStr;
    }    

    public BinaryTree<String> loadTree() {
        BinaryTree<String> tree = new BinaryTree<String>();
        return tree;
    }

    public static void displayStats(BinaryTree<String> tree) {
        if (tree.isEmpty())
            System.out.println("The tree is empty");
        else
            System.out.println("The tree is not empty");

        System.out.println("Root of tree is " + tree.getRootData());
        System.out.println("Height of tree is " + tree.getHeight());
        System.out.println("No. of nodes in tree is " + tree.getNumberOfNodes());
    } // end displayStats

}
