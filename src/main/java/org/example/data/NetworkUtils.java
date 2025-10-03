package org.example.data;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Contém métodos utilitários de baixo nível para operações de rede
public class NetworkUtils {

    public static final Set<String> IPS_LOCAIS = new HashSet<>();

    static {
        popularIpsLocais();
    }

    private static void popularIpsLocais() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                        IPS_LOCAIS.add(addr.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            System.err.println("Erro ao obter IPs locais: " + e.getMessage());
        }
    }

    public static void pingHost(String ip) {
        // Busca o valor de cada IP
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;

            if (osName.contains("win")) {
                pb = new ProcessBuilder("ping", "-n", "1", ip);
            } else {
                pb = new ProcessBuilder("ping", "-c", "1", ip);
            }

            pb.redirectErrorStream(true);
            Process p = pb.start();
            p.waitFor();

        } catch (IOException | InterruptedException e) {
            System.err.println("Aviso: Falha ao tentar 'ping' em " + ip);
        }
    }

    public static String pegarMacLocal() {
        // Busca os valores do Mac
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);

            if (ni == null) {
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    NetworkInterface iface = interfaces.nextElement();
                    if (!iface.isLoopback() && iface.isUp()) {
                        ni = iface;
                        break;
                    }
                }
            }

            if (ni != null) {
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            return "Falha ao obter MAC local";
        }
        return "Não encontrado (Local)";
    }

    public static String pegarMac(String ip) {
        String mac = "Não encontrado";
        try {
            ProcessBuilder pb = new ProcessBuilder("arp", "-a", ip);
            pb.redirectErrorStream(true);
            Process p = pb.start();
            p.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linha;
            Pattern pattern = Pattern.compile("([0-9A-Fa-f]{2}([-:])){5}[0-9A-Fa-f]{2}");
            while ((linha = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(linha);
                if (matcher.find()) {
                    mac = matcher.group();
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            mac = "Erro no ARP";
        }
        return mac;
    }
}