import java.util.Scanner;

class TrieNode {
    char data;
    boolean isWordEnd;
    String definition;
    TrieNode[] children;

    public TrieNode(char data) {
        this.data = data;
        isWordEnd = false;
        definition = "";
        children = new TrieNode[26]; // Assuming only lowercase English letters
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode('\0');
    }

    public void insert(String word, String definition) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode(ch);
            }
            current = current.children[index];
        }
        current.isWordEnd = true;
        current.definition = definition;
    }

    public String search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (current.children[index] == null) {
                return "Word not found";
            }
            current = current.children[index];
        }
        if (current.isWordEnd) {
            return current.definition;
        } else {
            return "Word not found";
        }
    }
}

public class EDictionary {
    public static void main(String[] args) {
        Trie dictionary = new Trie();
        Scanner scanner = new Scanner(System.in);

        // Insert some words and definitions
        dictionary.insert("apple", "A sweet, red fruit");
        dictionary.insert("banana", "A yellow, curved fruit");
        dictionary.insert("cat", "A small, furry domesticated animal");

        while (true) {
            System.out.println("1. Insert word");
            System.out.println("2. Search word");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter word: ");
                    String word = scanner.nextLine();
                    System.out.print("Enter definition: ");
                    String definition = scanner.nextLine();
                    dictionary.insert(word, definition);
                    System.out.println("Word inserted successfully.");
                    break;
                case 2:
                    System.out.print("Enter word to search: ");
                    String searchWord = scanner.nextLine();
                    String result = dictionary.search(searchWord);
                    System.out.println(result);
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}