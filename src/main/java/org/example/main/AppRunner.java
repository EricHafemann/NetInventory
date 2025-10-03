package org.example.main;

// ... (imports)

import org.example.data.HostFileReader;
import org.example.model.HostData;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class AppRunner {

    public static void iniciar(String caminhoArquivo) throws IOException {

        String arquivoSaida = "Relatorio_Hosts_IP_MAC_Novo.xlsx";

        //Leitura do arquivo
        Set<String> hostsUnicos = HostFileReader.lerHosts(caminhoArquivo);

        System.out.println("--- Iniciando Busca de IPs e MACs ---");
        System.out.println("Processando " + hostsUnicos.size() + " hosts...");

        // Verifica se existe hosts para processar
        if (hostsUnicos.isEmpty()) {
            System.out.println("AVISO: Nenhum host encontrado. Exportação do Excel cancelada.");
            return; // Sai se não existe
        }

        // Processamento das informações
        org.example.service.HostService hostService = new org.example.service.HostService();
        List<HostData> resultados = hostService.processarHosts(hostsUnicos);


        // Este bloco try-catch é apenas para erros do POI, não de leitura de arquivo.
        try {
            ExcelExporter.exportar(resultados, arquivoSaida);
        } catch (IOException e) {
            System.err.println("ERRO FATAL: Falha ao exportar o arquivo Excel.");
            e.printStackTrace();
        }
    }
}