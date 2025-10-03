# 💻 NetInventory: Host Scanner & Report Generator

Ferramenta Java desenvolvida para realizar varredura de hosts em rede, obter endereços IP e MAC, e gerar um inventário organizado em um arquivo Excel.

---

## 🎯 Objetivo

Gerar relatórios de inventário de rede de forma rápida e automatizada, resolvendo nomes de host e buscando endereços MAC (utilizando `ping` seguido de `arp -a`) para garantir a acuracidade dos dados.

---

## ✨ Funcionalidades

- **📄 Leitura de Hosts**  
  Lê a lista de hosts/IPs de um arquivo de texto (`.txt`) fornecido pelo usuário via menu.

- **🔍 Coleta de Dados**  
  Resolve IPs e utiliza comandos do sistema (`ping` e `arp`) para obter o endereço MAC de cada host.

- **📊 Relatório Final**  
  Exporta todos os dados coletados para um arquivo Excel (`.xlsx`) com formatação e estilos aplicados.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java (JDK 8 ou superior)
- **Geração de Relatórios:** [Apache POI](https://poi.apache.org/) (manipulação de arquivos Excel)

---

## 📂 Estrutura do Projeto

O código está organizado em pacotes, seguindo uma arquitetura em camadas para facilitar manutenção e legibilidade:

| Pacote     | Responsabilidade                                                                 |
|------------|-----------------------------------------------------------------------------------|
| `view`     | Contém o menu interativo (`menuSistema.java`)                                    |
| `main`     | Coordenação da execução (`AppRunner`) e exportação para Excel (`ExcelExporter`)  |
| `service`  | Lógica de negócios (`HostService`) e processamento de dados                      |
| `data`     | Operações de baixo nível (I/O, leitura de `.txt`, execução de comandos ARP/Ping) |
| `model`    | Modelagem dos dados (`HostData`)                                                  |

---

## 🚀 Como Usar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/NetInventory.git
   cd NetInventory
