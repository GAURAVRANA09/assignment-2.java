import java.io.*;
import java.util.*;

class Book {
    int id;
    String title, author;
    boolean issued;

    Book(int id, String t, String a, boolean issued) {
        this.id = id;
        this.title = t;
        this.author = a;
        this.issued = issued;
    }

    public String toString() {
        return id + "," + title + "," + author + "," + issued;
    }
}

class Member {
    int id;
    String name;
    List<Integer> issuedBooks = new ArrayList<>();

    Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return id + "," + name + "," + issuedBooks.toString();
    }
}

public class LibraryApp {

    Map<Integer, Book> books = new HashMap<>();
    Map<Integer, Member> members = new HashMap<>();

    // ---------------- FILE LOAD ----------------
    void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("books.txt"));
            String s;
            while ((s = br.readLine()) != null) {
                String[] p = s.split(",");
                books.put(Integer.parseInt(p[0]),
                        new Book(Integer.parseInt(p[0]), p[1], p[2], Boolean.parseBoolean(p[3])));
            }
            br.close();
        } catch (Exception e) {}

        try {
            BufferedReader br = new BufferedReader(new FileReader("members.txt"));
            String s;
            while ((s = br.readLine()) != null) {
                String[] p = s.split(",");
                members.put(Integer.parseInt(p[0]), new Member(Integer.parseInt(p[0]), p[1]));
            }
            br.close();
        } catch (Exception e) {}
    }

    // ---------------- FILE SAVE ----------------
    void save() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"));
            for (Book b : books.values()) bw.write(b + "\n");
            bw.close();

            bw = new BufferedWriter(new FileWriter("members.txt"));
            for (Member m : members.values()) bw.write(m + "\n");
            bw.close();
        } catch (Exception e) {}
    }

    // ---------------- BASIC OPERATIONS ----------------
    void addBook(Scanner sc) {
        System.out.print("Book ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Title: ");
        String t = sc.nextLine();
        System.out.print("Author: ");
        String a = sc.nextLine();

        books.put(id, new Book(id, t, a, false));
        save();
        System.out.println("Book added!");
    }

    void addMember(Scanner sc) {
        System.out.print("Member ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Name: ");
        String n = sc.nextLine();

        members.put(id, new Member(id, n));
        save();
        System.out.println("Member added!");
    }

    void issueBook(Scanner sc) {
        System.out.print("Book ID: ");
        int b = sc.nextInt();
        System.out.print("Member ID: ");
        int m = sc.nextInt();

        if (!books.containsKey(b) || !members.containsKey(m)) {
            System.out.println("Invalid ID!");
            return;
        }

        Book bk = books.get(b);
        if (bk.issued) {
            System.out.println("Already issued!");
            return;
        }

        bk.issued = true;
        members.get(m).issuedBooks.add(b);
        save();
        System.out.println("Issued!");
    }

    void returnBook(Scanner sc) {
        System.out.print("Book ID: ");
        int b = sc.nextInt();
        System.out.print("Member ID: ");
        int m = sc.nextInt();

        if (!books.containsKey(b) || !members.containsKey(m)) {
            System.out.println("Invalid ID!");
            return;
        }

        books.get(b).issued = false;
        members.get(m).issuedBooks.remove(Integer.valueOf(b));
        save();
        System.out.println("Returned!");
    }

    void search(Scanner sc) {
        System.out.print("Search title: ");
        sc.nextLine();
        String key = sc.nextLine().toLowerCase();

        for (Book b : books.values()) {
            if (b.title.toLowerCase().contains(key))
                System.out.println(b.id + " " + b.title + " by " + b.author);
        }
    }

    // ---------------- MAIN MENU ----------------
    public static void main(String[] args) {
        LibraryApp app = new LibraryApp();
        app.load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Add Book 2.Add Member 3.Issue 4.Return 5.Search 6.Exit");
            int ch = sc.nextInt();

            switch (ch) {
                case 1: app.addBook(sc); break;
                case 2: app.addMember(sc); break;
                case 3: app.issueBook(sc); break;
                case 4: app.returnBook(sc); break;
                case 5: app.search(sc); break;
                case 6: return;
            }
        }
    }
}
