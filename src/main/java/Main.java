package main.java;

public class Main {
    public static void main(String args[]) {

        String resultOne = new ComparisonCompactor(1, "abc12", "abd12").compact();
        System.out.println(resultOne);

        String resultTwo = new ComparisonCompactor(2, "abzcc1234", "abzdd1234").compact();
        System.out.println(resultTwo);

    }
}
