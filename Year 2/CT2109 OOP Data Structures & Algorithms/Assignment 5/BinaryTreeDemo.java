import java.util.Scanner;

/**
 * CT2109 NUI Galway:
 * Class to demonstrate the use of BinaryTree code.
 * Based on code by Carrano & Savitch.
 * 
 * @author Michael Madden.
 */

public class BinaryTreeDemo {
    public static void main(String[] args) {
        // Create a tree
        System.out.println("Constructing a test tree ...");
        BinaryTree<String> testTree = new BinaryTree<String>();

        createTree2(testTree);
        // Display some statistics about it
        System.out.println("\nSome statistics about the test tree ...");
        displayStats(testTree);

        askQuestions(testTree);

        // Perform in-order traversal
        System.out.println("\nIn-order traversal of the test tree, printing each node when visiting it ...");
        testTree.inorderTraverse();

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

    public static void createTree2(BinaryTree<String> tree) {
        //

        // To create a tree, build it up from the bottom:
        // create subtree for each leaf, then create subtrees linking them,
        // until we reach the root.

        // First the leaves
        BinaryTree<String> hTree = new BinaryTree<>("Is it an android phone?");
        BinaryTree<String> iTree = new BinaryTree<>("Is it a desktop?");
        BinaryTree<String> jTree = new BinaryTree<>("Is it a drill?");
        BinaryTree<String> kTree = new BinaryTree<>("Is it a chisel?");
        BinaryTree<String> lTree = new BinaryTree<>("Is it a penguin?");
        BinaryTree<String> mTree = new BinaryTree<>("Is it a camel?");
        BinaryTree<String> nTree = new BinaryTree<>("Is it a goldfish?");
        BinaryTree<String> oTree = new BinaryTree<>("Is it a dog?");

        // Now the subtrees joining leaves:
        BinaryTree<String> dTree = new BinaryTree<>("Is it a bird?", lTree, mTree);
        BinaryTree<String> eTree = new BinaryTree<>("Is it a fish?", nTree, oTree);
        BinaryTree<String> fTree = new BinaryTree<>("Does it have a touch screen?", hTree, iTree);
        BinaryTree<String> gTree = new BinaryTree<>("Is it a tool?", jTree, kTree);

        // Now the subtrees joining leaves:
        BinaryTree<String> bTree = new BinaryTree<>("Is it a mammal?", dTree, eTree);
        BinaryTree<String> cTree = new BinaryTree<>("Is it a computer?", fTree, gTree);

        // Now the root
        tree.setTree("Are you thinking of an animal?", bTree, cTree);
        // end createTree1

    }

    public static void askQuestions(BinaryTree<String> tree) {
        BinaryNodeInterface<String> currentNode = tree.getRootNode();
        Scanner scanner = new Scanner(System.in);

        String input;
        // until leaf node is reached
        while (!currentNode.isLeaf()) {
            System.out.println(currentNode.getData() + " (y/n)");
            input = scanner.nextLine().toLowerCase();

            if (input.equals("y")) {
                currentNode = currentNode.getLeftChild();
            } else if (input.equals("n")) {
                currentNode = currentNode.getRightChild();
            } else {
                System.out.println("Invalid input, please enter y or n.");
            }
        }

        // Guess the answer
        System.out.println("My guess is: " + currentNode.getData() + ". Am I right? (y/n)");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("n")) {
            // Expand the tree to include the new item
            expandTree(tree, currentNode, scanner);
            giveChoice(tree);
        } else {
            System.out.println("Great, I got it right!");
            giveChoice(tree);
        }
    }


    public static void expandTree(BinaryTree<String> tree, BinaryNodeInterface<String> currentNode, Scanner scanner) {
        if (currentNode == null) {
            return;
        }

        // Print the current node and prompt the user for an answer
        System.out.print("\nhere we will be expanding the tree: \n");
        System.out.println("Current node: "+currentNode.getData());

        System.out.print("Contribute yes or no question: ");
        String answer = scanner.nextLine();

        TreeInterface<String> leftNode = new BinaryTree<>(answer);
        TreeInterface<String> rightNode = new BinaryTree<>("Test");

//        currentNode.setRightChild(rightNode);
//        currentNode.setLeftChild(leftNode);

//        BinaryNodeInterface<String> nextRightNode = new BinaryNode<String>(answer);
//        BinaryNodeInterface<String> nextLeftNode = new BinaryNode<String>("new left node test");
//


       // tree.setTree(currentNode.getData(), new BinaryTree<>(answer) ,new BinaryTree<>("test"));

//
//
//
//
//        currentNode.setLeftChild(nextLeftNode);
//        currentNode.setLeftChild(nextRightNode);
//
//        currentNode = currentNode.getLeftChild();
//        System.out.println(currentNode.getData());


//        // If the answer is yes, recursively call the function on the left child
//        if (answer.equalsIgnoreCase("yes")) {
//            expandTree(tree, currentNode.getLeftChild(), scanner);
//        }
//        // If the answer is no and the current node is a leaf node, prompt the user to expand the tree
//        else if (answer.equalsIgnoreCase("no") && currentNode.isLeaf()) {
//            // Create new leaf nodes for the yes and no objects
//            BinaryNodeInterface<String> yesNode = new BinaryNode<String>(""); // Create an empty node for the yes object
//            BinaryNodeInterface<String> noNode = new BinaryNode<String>(""); // Create an empty node for the no object
//
//            // Set the left and right children of the current node to the yes and no nodes, respectively
//            currentNode.setLeftChild(yesNode);
//            currentNode.setRightChild(noNode);
//
//            // Prompt the user for a yes or no question
//            System.out.print("Enter a yes or no question: ");
//            String question = scanner.nextLine();
//
//            // Determine which child to assign the new question and object to
//            System.out.print("Is the answer to the question yes? (y/n): ");
//            String answerToQuestion = scanner.nextLine();
//
//            if (answerToQuestion.equalsIgnoreCase("y")) {
//                yesNode.setData(question);
//                noNode.setData(currentNode.getData());
//            } else {
//                yesNode.setData(currentNode.getData());
//                noNode.setData(question);
//            }
//        }
//        // If the answer is no and the current node is not a leaf node, recursively call the function on the right child
//        else if (answer.equalsIgnoreCase("no")) {
//            expandTree(tree, currentNode.getRightChild(), scanner);
//        }
//

    }

    public static void giveChoice(BinaryTree<String> tree){
        BinaryNodeInterface<String> currentNode = tree.getRootNode();
        String guideChoice = "\nChose from options:" +
                "\n1. Play again:" +
                "\n2. Store the tree"+
                "\n3. Load a stored tree"+
                "\n4. Quit";
        System.out.println(guideChoice);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        boolean moveOn = false;
        while(moveOn == false){
            if(choice.equals("1")){
                // play game again with expanded tree
                currentNode = tree.getRootNode();
                //askQuestions(currentNode);
                moveOn = true;
            } else if (choice.equals("2")) {
                // store the tree as a save file with a method
                moveOn = true;
            } else if (choice.equals("3")) {
                // Load an existing file that contains the saved tree data
                moveOn = true;
            } else if (choice.equals("4")) {
                // quit the game
                moveOn = true;
            }else {
                System.out.println(guideChoice);
                System.out.println("input must be '1', '2', '3', '4'");
                choice = scanner.nextLine();
            }
        }
    }

    public static void expandTree2(BinaryTree<String> tree, BinaryNodeInterface<String> currentNode, Scanner scanner) {
        if (currentNode == null) {
            return;
        }

        // Ask the user for a new question
        System.out.print("What is the object you were thinking of? ");
        String objectName = scanner.nextLine();
        System.out.print("Enter a yes or no question that distinguishes " + objectName + " from " + currentNode.getData() + ": ");
        String newQuestion = scanner.nextLine();

        // Create a new internal node with the new question
        BinaryNodeInterface<String> newQuestionNode = new BinaryNode<String>(newQuestion);

        // Determine which child to assign the new object and the existing object
        System.out.print("Is the answer to the question 'yes' for " + objectName + "? (y/n): ");
        String answerToQuestion = scanner.nextLine();

        // Create new leaf nodes for the new object and the existing object
        BinaryNodeInterface<String> newObjectNode = new BinaryNode<String>(objectName);
        BinaryNodeInterface<String> existingObjectNode = new BinaryNode<String>(currentNode.getData());

        if (answerToQuestion.equalsIgnoreCase("y")) {
            newQuestionNode.setLeftChild(newObjectNode);
            newQuestionNode.setRightChild(existingObjectNode);
        } else {
            newQuestionNode.setLeftChild(existingObjectNode);
            newQuestionNode.setRightChild(newObjectNode);
        }

        // Replace the current node with the new question node
        replaceNodeInTree(tree, currentNode, newQuestionNode);
    }

    public static void replaceNodeInTree(BinaryTree<String> tree, BinaryNodeInterface<String> currentNode, BinaryNodeInterface<String> newNode) {
        if (tree.getRootNode() == currentNode) {
            tree.setRootNode(newNode);
        } else {
            replaceNodeInSubtree(tree.getRootNode(), currentNode, newNode);
        }
    }

    public static void replaceNodeInSubtree(BinaryNodeInterface<String> subRoot, BinaryNodeInterface<String> currentNode, BinaryNodeInterface<String> newNode) {
        if (subRoot != null) {
            if (subRoot.getLeftChild() == currentNode) {
                subRoot.setLeftChild(newNode);
            } else if (subRoot.getRightChild() == currentNode) {
                subRoot.setRightChild(newNode);
            } else {
                replaceNodeInSubtree(subRoot.getLeftChild(), currentNode, newNode);
                replaceNodeInSubtree(subRoot.getRightChild(), currentNode, newNode);
            }
        }
    }


}
