package org.example.main;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.HostData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

// Lógica de Apresentação: Cria e Salva o Excel
public class ExcelExporter {

    public static void exportar(List<HostData> dados, String nomeArquivo) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Inventário de Hosts");
            int numeroLinha = 0;

            CellStyle headerStyle = criarEstiloCabecalho(workbook);

            // Cria o cabeçalho
            Row headerRow = sheet.createRow(numeroLinha++);
            String[] headers = {"Host", "IP (IPv4)", "MAC"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Preenche os dados
            for (HostData data : dados) {
                Row dataRow = sheet.createRow(numeroLinha++);
                dataRow.createCell(0).setCellValue(data.getHost());
                dataRow.createCell(1).setCellValue(data.getIp());
                dataRow.createCell(2).setCellValue(data.getMac());
            }

            // Ajusta a largura das colunas
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);

            salvarWorkbook(workbook, nomeArquivo);
        }
    }

    private static CellStyle criarEstiloCabecalho(Workbook workbook) {
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        return headerStyle;
    }

    private static void salvarWorkbook(Workbook workbook, String nomeArquivo) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(nomeArquivo)) {
            workbook.write(fileOut);
            System.out.println("\n--- SUCESSO! Relatório salvo em: " + nomeArquivo + " ---");
        }
    }
}