package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.Subscriber;
import main.java.kbtu.chill_guys.university_management_system.model.UserSubscriber;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Post;

import java.time.LocalDate;
import java.util.Scanner;

public class JournalView {
    private final Scanner scanner = new Scanner(System.in);


    public void displayJournalDetails(Journal journal) {
        System.out.println("Journal Details:");
        System.out.println("Name: " + journal.getName());
        System.out.println("ID: " + journal.getId());
        System.out.println("Subscribers: " + journal.getSubscribers().size());
        System.out.println("Posts: " + journal.getPosts().size());
    }
    public void displayAllPosts(Journal journal) {
        System.out.println("All Posts:");
        for (Post post : journal.getPosts()) {
            System.out.println(post);
        }
    }
    public void displaySubscribers(Journal journal) {
        System.out.println("Subscribers:");
        for (UserSubscriber subscriber : journal.getSubscribers()) {
            System.out.println(subscriber);
        }
    }
}
