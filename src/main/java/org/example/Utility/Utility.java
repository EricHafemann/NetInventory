package org.example.Utility;

import java.util.Scanner;

public class Utility {
    private static final Scanner scanner = new Scanner(System.in);

    // Método para ler uma String do console
    public static String lerString() {
        return scanner.nextLine();
    }

    // Método para ler um inteiro do console
    public static int lerInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Digite um número inteiro: ");
            scanner.nextLine(); // Consome a linha inválida
        }
        int num = scanner.nextInt();
        scanner.nextLine(); // Consome o resto da linha
        return num;
    }
}