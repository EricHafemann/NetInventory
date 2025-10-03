package org.example.service;

import org.example.data.NetworkUtils;
import org.example.model.HostData;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HostService {

    public List<HostData> processarHosts(Set<String> hosts) {
        List<HostData> resultados = new ArrayList<>();

        for (String host : hosts) {
            try {
                InetAddress[] enderecos = InetAddress.getAllByName(host);
                boolean ipv4Encontrado = false;

                for (InetAddress inet : enderecos) {
                    if (inet instanceof Inet4Address) {
                        String ip = inet.getHostAddress();
                        ipv4Encontrado = true;
                        String mac;
                        String tipoIP;

                        if (NetworkUtils.IPS_LOCAIS.contains(ip)) {
                            mac = NetworkUtils.pegarMacLocal();
                            tipoIP = "IPv4 LOCAL";
                        } else {
                            NetworkUtils.pingHost(ip);
                            mac = NetworkUtils.pegarMac(ip);
                            tipoIP = "IPv4 Remoto";
                        }

                        resultados.add(new HostData(host, ip, mac, tipoIP));

                        System.out.println("OK -> Host: " + host + " | IP (" + tipoIP + "): " + ip + " | MAC: " + mac);
                        break;
                    }
                }

                if (!ipv4Encontrado) {
                    System.out.println("INFO -> Host: " + host + " | Nenhum IPv4 encontrado ou resolvido.");
                }

            } catch (Exception e) {
                System.out.println("ERRO -> Host: " + host + " | Falha no Processamento: " + e.getMessage());
            }
        }
        return resultados;
    }
}