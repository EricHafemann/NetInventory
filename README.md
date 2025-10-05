# 🕵️ Verificador de Inventário de Rede (Host e IP/MAC)

Este projeto Java funciona como um verdadeiro detetive de rede, automatizando a coleta de informações cruciais de hosts, IPs e endereços MAC, exibindo tudo diretamente no console.

---

## 🎯 Funcionalidades

O programa lê uma lista de hosts/IPs de um arquivo de texto e tenta localizar suas informações de rede:

| Status             | Exemplo de Saída                                                               | Significado                                                      |
| ------------------ | ------------------------------------------------------------------------------ | ---------------------------------------------------------------- |
| Sucesso (Local)    | OK -> Host: localhost IP (IPv4 LOCAL): 127.0.0.1                               | Host local encontrado com sucesso                                |
| Sucesso (Remoto)   | OK -> Host: servidor-01 IP (IPv4 Remoto): 192.168.1.5                          | Host remoto encontrado com sucesso                               |
| MAC Não Encontrado | OK -> Host: roteador-externo IP (IPv4 Remoto): 192.168.20.1 MAC Não Encontrado | MAC não encontrado (normal para dispositivos fora da rede local) |
| Falha Total        | ERRO -> Host: host-invalido Falha no Processamento: Unknown host               | Falha total no processamento do host                             |

---

## 🚀 Guia Rápido de Uso

### 1. Preparação

Crie um arquivo de texto (.txt) com os hosts ou IPs que deseja verificar, um por linha.

### 2. Execução do Programa

#### Pelo Terminal ou Prompt de Comando

1. Abra o terminal na pasta onde está o `.jar` do programa.
2. Execute o comando:

```bash
java -jar NomeDoPrograma.jar
```

> Substitua `NomeDoPrograma.jar` pelo nome real do arquivo.

3. Informe o caminho do arquivo de hosts quando solicitado:

```
Digite o caminho do arquivo de hosts: C:\Users\Eric\hosts.txt
```

4. O programa processará cada host/IP e exibirá os resultados no console.

#### Dicas Extras

* Arraste o arquivo `.txt` para o terminal em sistemas que suportam, preenchendo o caminho automaticamente.
* Certifique-se de que o Java esteja instalado e configurado no PATH (`java -version` para conferir).
