import java.util.Scanner;

public class DNAPalindrome {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a DNA sequence:");
        String dnaSequence = scanner.nextLine();

        try {
            if (isValidDNASequence(dnaSequence)) {
                boolean isPalindrome = isWatsonCrickComplementedPalindrome(dnaSequence);
                if (isPalindrome) {
                    System.out.println("It is Watson-Crick complemented palindrome!");
                } else {
                    System.out.println("It is not Watson-Crick complemented palindrome.");
                }
            } else {
                System.err.println("Invalid DNA sequence");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    public static boolean isWatsonCrickComplementedPalindrome(String dnaSequence) {
        StringDoubleEndedQueueImpl<String> queue = new StringDoubleEndedQueueImpl<>();

        // Enqueue the characters from the DNA sequence
        for (int i = 0; i < dnaSequence.length(); i++) {
            String nucleotide = String.valueOf(dnaSequence.charAt(i));
            if (isNucleotide(nucleotide)) {
                queue.addLast(nucleotide);
            } else {
                throw new IllegalArgumentException("Invalid nucleotide: " + nucleotide);
            }
        }

        // Check for Watson-Crick complemented palindrome
        while (queue.size() > 1) {
            if (dnaSequence.length() % 2 == 0) {
                String frontNucleotide = queue.removeFirst();
                String rearNucleotide = queue.removeLast();

                if (!areComplementary(frontNucleotide, rearNucleotide)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        // If the queue is empty or has one element, it's a palindrome
        return true;
    }

    private static boolean areComplementary(String nucleotide1, String nucleotide2) {
        return (nucleotide1.equals("A") && nucleotide2.equals("T")) ||
               (nucleotide1.equals("T") && nucleotide2.equals("A")) ||
               (nucleotide1.equals("C") && nucleotide2.equals("G")) ||
               (nucleotide1.equals("G") && nucleotide2.equals("C"));
    }

    private static boolean isNucleotide(String symbol) {
        return symbol.equals("A") || symbol.equals("T") || symbol.equals("C") || symbol.equals("G");
    }

    private static boolean isValidDNASequence(String dnaSequence) {
        return dnaSequence.matches("[ATCG]+");
    }
}
