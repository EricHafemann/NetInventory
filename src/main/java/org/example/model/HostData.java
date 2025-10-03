package org.example.model;

// Classe simples para armazenar os dados coletados de cada host
public class HostData {
    private final String host;
    private final String ip;
    private final String mac;
    private final String tipoIP;

    public HostData(String host, String ip, String mac, String tipoIP) {
        this.host = host;
        this.ip = ip;
        this.mac = mac;
        this.tipoIP = tipoIP;
    }

    // Getters

    public String getHost() { return host; }
    public String getIp() { return ip; }
    public String getMac() { return mac; }
    public String getTipoIP() { return tipoIP; }

}