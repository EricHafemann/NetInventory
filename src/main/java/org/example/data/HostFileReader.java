package org.example.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class HostFileReader {


    public static Set<String> lerHosts(String arquivo) throws IOException {
        Set<String> hosts = new HashSet<>();

        // Se o arquivo não for encontrado = FileNotFoundException,
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    hosts.add(linha.trim());
                }
            }
        }
        // NÃO HÁ MAIS BLOCO CATCH AQUI.

        return hosts;
    }
}