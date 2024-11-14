package org.prototype.Views;

import java.util.Scanner;

/**
 * Classe abstraite qui contient les éléments qu'on retrouvera dans toutes les vues.
 */
public abstract class View {
    // Reader qui sera utilisé dans toutes les vues
    protected static Scanner reader = new Scanner(System.in);


    // Je ne veux juste pas réécrire System.out à chaque fois
    protected static void println(String msg) {
        System.out.println(msg);
    }
    protected static void print(String msg) {
        System.out.print(msg);
    }

    /**
     * Clear la console, l'idée est d'avoir un comportement similaire à cls().
     */
    protected static void clearConsole() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
