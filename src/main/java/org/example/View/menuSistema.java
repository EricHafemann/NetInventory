package org.example.View;

import org.example.main.AppRunner;
import org.example.Utility.Utility;

import java.io.FileNotFoundException;
import java.io.IOException;

public class menuSistema {

    // Caminho padrão que será usado se o usuário escolher a opção 2
    private static final String CAMINHO_PADRAO = "C:/Users/eric_hafemann/Documents/Teste.txt";

    public static void main(String[] args) {

        boolean fim = false;

        do {
            System.out.println("\n- - - - - - - - - - - -");
            System.out.println(" Menu Verificação HOST ");
            System.out.println("- - - - - - - - - - - -");
            System.out.println("[1] - Usar caminho próprio ");
            System.out.println("[2] - Usar caminho padrão (Atual: " + CAMINHO_PADRAO + ")");
            System.out.println("[3] - Sair do Sistema");

            System.out.print("Escolha uma opção: ");
            int opMenu = Utility.lerInt();

            String caminhoArquivo = null;

            switch (opMenu) {
                case 1:
                    System.out.println("\nDigite o caminho COMPLETO do TXT:");
                    System.out.println("Exemplo: C:/Users/[Seu nome]/Documents/[Nome do Arquivo].txt");
                    System.out.print("Caminho: ");
                    caminhoArquivo = Utility.lerString();
                    break;
                case 2:
                    caminhoArquivo = CAMINHO_PADRAO;
                    break;
                case 3:
                    fim = true;
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            // Try Catch para validar os erros
            if (caminhoArquivo != null)
            {
                try {
                    // Chama a execução
                    AppRunner.iniciar(caminhoArquivo);

                } catch (FileNotFoundException e) {
                    // Quando arquivo não existe
                    System.err.println("\n[ERRO FATAL DE CAMINHO] O caminho fornecido é inválido ou o arquivo não foi encontrado!");
                    System.err.println("-> Caminho inválido: " + caminhoArquivo);
                    System.err.println("Por favor, verifique o nome do arquivo e o caminho.");
                } catch (IOException e) {
                    // Erro de permissão ou ao acessar o arquivo
                    System.err.println("\n[ERRO DE SISTEMA] Ocorreu um erro inesperado ao processar o arquivo.");
                    System.err.println("Detalhes: " + e.getMessage());
                }
            }

        } while (!fim);
    }
}