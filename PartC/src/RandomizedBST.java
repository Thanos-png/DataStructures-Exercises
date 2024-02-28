// ATHANASIOS PANAGIOTIDIS
// p3220143

// import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RandomizedBST implements TaxEvasionInterface {

    private TreeNode root;
    private Random customRandom = new Random();

    public RandomizedBST() {
        this.root = null;
    }

    private class TreeNode {
        LargeDepositor item;
        TreeNode left;  // pointer to left subtree
        TreeNode right;  // pointer to right subtree
        TreeNode parent;  // pointer to parent node
        int N;  // number of nodes in the subtree rooted at this TreeNode

        TreeNode(LargeDepositor item) {
            this.item = item;
            this.N = 1;
        }

        public void setData(LargeDepositor item) {
            this.item = item;
        }

        public LargeDepositor getData() {
            return item;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

        public TreeNode getParent() {
            return parent;
        }
    }

    public static void main(String[] args) {
        RandomizedBST tree = new RandomizedBST();

        Scanner scanner = new Scanner(System.in);
        String userInput;
        String AFMInput;
        String firstInput;
        String lastInput;
        String savingsInput;
        String taxedIncomeInput;
        String fileNameInput;
        String kInput;

        do {
            // Main Loop

            // It isn't key sensitive
            System.out.println("-------------------------------------------");
            System.out.println("1.Insert");
            System.out.println("2.Remove");
            System.out.println("3.Search by AFM");
            System.out.println("4.Search by Last Name");
            System.out.println("5.Load text file");
            System.out.println("6.Update savings");
            System.out.println("7.Get mean savings");
            System.out.println("8.Print by AMF");
            System.out.println("9.Print the k largest depositors");
            System.out.println("0.Exit");
            System.out.println("-------------------------------------------");
            System.out.print("Choose what operation you want to perform: ");
            userInput = scanner.nextLine();

            if (userInput.equals("1")) {
                // Insert

                // AFM
                System.out.print("Enter depositor's AFM: ");
                AFMInput = scanner.nextLine();
                while (!AFMInput.matches("\\d+")) {
                    System.out.println("Error: not valid AFM, try again.");
                    System.out.print("Enter depositor's AFM: ");
                    AFMInput = scanner.nextLine();
                }

                // First Name
                System.out.print("Enter depositor's Fist Name: ");
                firstInput = scanner.nextLine();

                // Last Name
                System.out.print("Enter depositor's Last Name: ");
                lastInput = scanner.nextLine();

                // Savings
                System.out.print("Enter depositor's savings: ");
                savingsInput = scanner.nextLine();
                while (!savingsInput.matches("-?\\d+(\\.\\d+)?")) {
                    System.out.println("Error: not valid savings, try again.");
                    System.out.print("Enter depositor's savings: ");
                    savingsInput = scanner.nextLine();
                }

                // Taxed Income
                System.out.print("Enter depositor's taxed income: ");
                taxedIncomeInput = scanner.nextLine();
                while (!taxedIncomeInput.matches("-?\\d+(\\.\\d+)?")) {
                    System.out.println("Error: not valid taxed income, try again.");
                    System.out.print("Enter depositor's taxed income: ");
                    taxedIncomeInput = scanner.nextLine();
                }

                tree.insert(new LargeDepositor(Integer.parseInt(AFMInput), firstInput, lastInput, Double.parseDouble(savingsInput), Double.parseDouble(taxedIncomeInput)));
            } else if (userInput.equals("2")) {
                // Remove

                // AFM
                System.out.print("Enter depositor's AFM: ");
                AFMInput = scanner.nextLine();
                while (!AFMInput.matches("\\d+")) {
                    System.out.println("Error: not valid AFM, try again.");
                    System.out.print("Enter depositor's AFM: ");
                    AFMInput = scanner.nextLine();
                }

                tree.remove(Integer.parseInt(AFMInput));
            } else if (userInput.equals("3")) {
                // Search by AFM

                // AFM
                System.out.print("Enter depositor's AFM: ");
                AFMInput = scanner.nextLine();
                while (!AFMInput.matches("\\d+")) {
                    System.out.println("Error: not valid AFM, try again.");
                    System.out.print("Enter depositor's AFM: ");
                    AFMInput = scanner.nextLine();
                }

                System.out.println();
                System.out.println(tree.searchByAFM(Integer.parseInt(AFMInput)));
            } else if (userInput.equals("4")) {
                // Search by Last Name

                // Last Name
                System.out.print("Enter depositor's Last Name: ");
                lastInput = scanner.nextLine();

                System.out.println();
                // System.out.println(tree.searchByLastName(lastInput.toLowerCase()));
                tree.searchByLastName(lastInput.toLowerCase());
            } else if (userInput.equals("5")) {
                // Load

                // File Name
                System.out.print("Enter the file name(fileName.txt): ");
                fileNameInput = scanner.nextLine();

                tree.load(fileNameInput);
            } else if (userInput.equals("6")) {
                // Update Savings

                // AFM
                System.out.print("Enter depositor's AFM: ");
                AFMInput = scanner.nextLine();
                while (!AFMInput.matches("\\d+")) {
                    System.out.println("Error: not valid AFM, try again.");
                    System.out.print("Enter depositor's AFM: ");
                    AFMInput = scanner.nextLine();
                }

                // Savings
                System.out.print("Enter depositor's savings: ");
                savingsInput = scanner.nextLine();
                while (!savingsInput.matches("-?\\d+(\\.\\d+)?")) {
                    System.out.println("Error: not valid savings, try again.");
                    System.out.print("Enter depositor's savings: ");
                    savingsInput = scanner.nextLine();
                }

                tree.updateSavings(Integer.parseInt(AFMInput), Double.parseDouble(savingsInput));
            } else if (userInput.equals("7")) {
                // Get Mean Savings

                System.out.println("The mean savings are: " + tree.getMeanSavings());
            } else if (userInput.equals("8")) {
                // Print by AMF

                System.out.println();
                tree.printByAFM();
            } else if (userInput.equals("9")) {
                // Print Top Large Depositors

                // k
                System.out.print("Enter k: ");
                kInput = scanner.nextLine();
                while (!kInput.matches("\\d+")) {
                    System.out.println("Error: not valid k number, try again.");
                    System.out.print("Enter k: ");
                    kInput = scanner.nextLine();
                }

                System.out.println();
                tree.printTopLargeDepositors(Integer.parseInt(kInput));
            } else {
                if (!userInput.equals("0")) {
                    // Not Valid Operator Error
                    System.out.println("Error: " + userInput + " isn't a valid operator.");
                }
            }
            System.out.println();
        } while (!userInput.equals("0"));

        // Exit Programme
        System.out.println("Exiting the program...");
        System.exit(0);
    }

    @Override
    public void insert(LargeDepositor item) {
        root = insert(root, item);
    }

    private TreeNode insert(TreeNode node, LargeDepositor item) {
        if (node == null) {
            return new TreeNode(item);
        }

        if (item.key() == node.item.key()) {
            System.err.println("Error: Duplicate key found. Insertion aborted.");
            System.exit(1);
        }

        if (customRandom.nextInt(node.N + 1) == 0) {
            TreeNode parent = node.getParent();
            return insertAsRoot(item, node, parent);
        }

        if (item.key() < node.item.key()) {
            node.left = insert(node.left, item);
        } else {
            node.right = insert(node.right, item);
        }

        updateSize(node);
        return node;
    }

/*
    private TreeNode insertAsRoot(LargeDepositor item, TreeNode h) {
        if (h == null) {
            return new TreeNode(item);
        }

        if (item.key() < h.item.key()) {
            h.left = insertAsRoot(item, h.left);
            h = rotateRight(h);
        } else {
            h.right = insertAsRoot(item, h.right);
            h = rotateLeft(h);
        }

        updateSize(h);
        return h;
    }
*/

    private TreeNode insertAsRoot(LargeDepositor item, TreeNode h, TreeNode parent) {
        if (h == null) {
            TreeNode node = new TreeNode(item);
            node.setParent(parent);
            updateSize(parent);
            return node;
        }

        if (item.key() == h.item.key()) {
            return h;
        } else if (h.item.key() > item.key()) {
            TreeNode leftSubtreeRoot = this.insertAsRoot(item, h.getLeft(), h);
            h.setLeft(leftSubtreeRoot);
            h = this.rotateRight(h);
        } else {
            TreeNode rightSubtreeRoot = this.insertAsRoot(item, h.getRight(), h);
            h.setRight(rightSubtreeRoot);
            h = this.rotateLeft(h);
        }

        return h;
    }

    private TreeNode rotateRight(TreeNode pivot) {
        TreeNode parent = pivot.getParent();
        TreeNode child = pivot.getLeft();

        if (parent == null) {
            root = child;
        } else if (parent.getLeft() == pivot) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }

        child.setParent(pivot.getParent());
        pivot.setParent(child);
        pivot.setLeft(child.getRight());
        if (child.getRight() != null) {
        	child.getRight().setParent(pivot);
        }
        child.setRight(pivot);

        // Update N values
        updateSize(pivot);
        if (child != null) {
            updateSize(child);
        }
        if (parent != null) {
            updateSize(parent);
        }

        return child;
    }

    private TreeNode rotateLeft(TreeNode pivot) {
        TreeNode parent = pivot.getParent();
        TreeNode child = pivot.getRight();

        if (parent == null) {
            root = child;
        } else if (parent.getLeft() == pivot) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }

        child.setParent(pivot.getParent());
        pivot.setParent(child);
        pivot.setRight(child.getLeft());
        if (child.getLeft() != null) {
        	child.getLeft().setParent(pivot);
        }
        child.setLeft(pivot);

        // Update N values
        updateSize(pivot);
        if (child != null) {
            updateSize(child);
        }
        if (parent != null) {
            updateSize(parent);
        }

        return child;
    }

    @Override
    public void load(String filename) {
        // File file = new File("..", filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                if (tokens.length == 5) {
                    int AFM = Integer.parseInt(tokens[0]);
                    String firstName = tokens[1];
                    String lastName = tokens[2];
                    double savings = Double.parseDouble(tokens[3]);
                    double taxedIncome = Double.parseDouble(tokens[4]);
                    LargeDepositor depositor = new LargeDepositor(AFM, firstName, lastName, savings, taxedIncome);
                    insert(depositor);
                }
            }
        } catch (IOException e) {
            // System.out.println("Error: File name not found. Operation aborted.");
            e.printStackTrace();
        }
    }

    private void updateSize(TreeNode node) {
        if (node != null) {
            if (node.left != null) {
                updateSize(node.left);
            }
            if (node.right != null) {
                updateSize(node.right);
            }
            if (node.left == null && node.right == null) {
                node.N = 1;
            } else if (node.left == null) {
                node.N = 1 + size(node.right);
            } else if (node.right == null) {
                node.N = 1 + size(node.left);
            } else {
                node.N = 1 + size(node.left) + size(node.right);
            }
        }
    }

    private int size(TreeNode node) {
        return (node == null) ? 0 : node.N;
    }

    @Override
    public void updateSavings(int AFM, double savings) {
        root = updateSavings(root, AFM, savings);
    }

    private TreeNode updateSavings(TreeNode node, int AFM, double savings) {
        if (node == null) {
            System.err.println("Error: AFM not found. Update aborted.");
            System.exit(1);
        }

        if (AFM == node.item.key()) {
            node.item.setSavings(savings);
        } else if (AFM < node.item.key()) {
            node.left = updateSavings(node.left, AFM, savings);
        } else {
            node.right = updateSavings(node.right, AFM, savings);
        }

        return node;
    }

    @Override
    public LargeDepositor searchByAFM(int AFM) {
        return searchByAFM(root, AFM);
    }

    private LargeDepositor searchByAFM(TreeNode node, int AFM) {
        if (node == null) {
            System.out.println("The given AFM is not found");
            return null;
        }
        if (node.item.key() == AFM) {
            return node.item;
        } else if (node.item.key() < AFM) {
            return searchByAFM(node.right, AFM);
        } else {
            return searchByAFM(node.left, AFM);
        }
    }

    @Override
    public List searchByLastName(String last_name) {
        SimpleLinkedList resultList = new SimpleLinkedList();
        searchByLastName(root, last_name, resultList);
        // Print details if there are at most 5 suspects
        if (resultList.size() <= 5) {
            resultList.printList();
        }
        if (resultList.size() != 0) {
            return resultList.getList();
        } else {
            return null;
        }
    }

    private void searchByLastName(TreeNode node, String last_name, SimpleLinkedList resultList) {
        if (node != null) {
            searchByLastName(node.left, last_name, resultList);
            if (node.item.getLastName().equals(last_name)) {
                resultList.add(node.item);
            }
            searchByLastName(node.right, last_name, resultList);
        }
    }

    @Override
    public void remove(int AFM) {
        // find node to delete and its parent
        TreeNode current = root;
        TreeNode parent = null;

        while (true) {
            if (current == null) {
                System.err.println("Error: AFM not found. Removal aborted.");
                System.exit(1);
            }
            if (current.getData().key() == AFM) {
                break;
            }
            parent = current;
            if (current.getData().key() < AFM) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }

        // node to replace with
        TreeNode replace = null;

        // only right
        if (current.getLeft() == null) {
            replace = current.getRight();
        } else if (current.getRight() == null) {
            replace = current.getLeft();
        } else {
            // find left most child of current right subtree
            TreeNode findCurrent = current.getRight();

            while (true) {
                if (findCurrent.getLeft() != null) {
                    findCurrent = findCurrent.getLeft();
                } else {
                    break;
                }
            }

            // only has zero or one child, there is no left child
            remove(findCurrent.getData().key());

            findCurrent.setLeft(current.getLeft());
            findCurrent.setRight(current.getRight());

            replace = findCurrent;
        }

        // replace parents reference
        if (parent == null) {
            root = replace;
        } else {
            if (parent.getLeft() == current) {
                parent.setLeft(replace);
            }
            if (parent.getRight() == current) {
                parent.setRight(replace);
            }
        }

        // Ensure replace is not null before setting its parent references
        if (replace != null) {
            replace.setParent(parent);
        }

        // Update N values
        updateSize(root);
    }

    @Override
    public double getMeanSavings() {
        int totalDepositors = root != null ? root.N : 0;

        if (totalDepositors == 0) {
            System.out.println("No depositors found. Mean savings is undefined.");
            return 0.0;
        }

        double totalAmount = calculateTotalSavings(root);
        return totalAmount / totalDepositors;
    }

    private double calculateTotalSavings(TreeNode node) {
        if (node == null) {
            return 0.0;
        }
        return node.item.getSavings() + calculateTotalSavings(node.left) + calculateTotalSavings(node.right);
    }

    @Override
    public void printTopLargeDepositors(int k) {
        if (k <= 0) {
            System.err.println("Error: k must be a positive integer.");
            return;
        }

        // Create a SimplePriorityQueue to store depositors based on suspicion score
        SimplePriorityQueue priorityQueue = new SimplePriorityQueue(root.N);

        // Populate the priority queue with depositors from the tree
        populatePriorityQueue(root, priorityQueue);

        // Print the top k most suspected tax evasion large depositors
        System.out.println("Top " + k + " Most Suspected Tax Evasion Large Depositors:");

        for (int i = 0; i < k; i++) {
            LargeDepositor depositor = priorityQueue.removeMax();
            if (depositor != null) {
                System.out.println(depositor);
            } else {
                break; // Priority queue is empty
            }
        }
    }

    private void populatePriorityQueue(TreeNode node, SimplePriorityQueue priorityQueue) {
        if (node != null) {
            populatePriorityQueue(node.left, priorityQueue);
            priorityQueue.insert(node.item);
            populatePriorityQueue(node.right, priorityQueue);
        }
    }

    @Override
    public void printByAFM() {
        System.out.println("Depositors sorted by AFM:");
        printByAFM(root);
    }

    private void printByAFM(TreeNode node) {
        if (node != null) {
            printByAFM(node.left);
            System.out.println(node.item);
            printByAFM(node.right);
        }
    }
}
