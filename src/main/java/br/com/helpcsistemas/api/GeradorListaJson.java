package br.com.helpcsistemas.api;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GeradorListaJson {

    public static void main(String[] args) {
        int quantidadeElementos = 5000;
        String json = gerarListaJson(quantidadeElementos);

        // Especifique o caminho do arquivo onde deseja salvar o JSON
        String caminhoArquivo = "recebiveis.json";

        try {
            salvarJsonEmArquivo(json, caminhoArquivo);
            System.out.println("JSON salvo com sucesso em " + caminhoArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o JSON em arquivo: " + e.getMessage());
        }
    }

    public static String gerarListaJson(int quantidadeElementos) {
        List<String> listaJson = new ArrayList<>();
        Random random = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        BigDecimal valorAcumulado = BigDecimal.ZERO;

        for (int i = 0; i < quantidadeElementos; i++) {
            String chaveAcesso = gerarChaveAcesso();
            String numeroNotaFiscal = gerarNumeroNotaFiscal();
            BigDecimal valorRecebivel = gerarValorRecebivel();
            valorAcumulado = valorAcumulado.add(valorRecebivel); // Acumula o valor

            String dataVencimento = gerarDataVencimento();

            String json = String.format(
                    "{\"chave_acesso\": \"%s\", \"numero_nota_fiscal\": \"%s\", \"valor_recebivel\": %s, \"data_vencimento\": \"%s\", \"valor_antecipar_editado\": %s}",
                    chaveAcesso, numeroNotaFiscal, df.format(valorRecebivel), dataVencimento, df.format(valorAcumulado)
            );

            listaJson.add(json);
        }

        StringBuilder finalJson = new StringBuilder();
        finalJson.append("{\"recebiveis\": [");
        finalJson.append(String.join(",", listaJson));
        finalJson.append("]}");

        return finalJson.toString();
    }

    private static String gerarChaveAcesso() {
        Random random = new Random();
        StringBuilder chave = new StringBuilder();
        for (int i = 0; i < 44; i++) {
            chave.append(random.nextInt(10));
        }
        return chave.toString();
    }

    private static String gerarNumeroNotaFiscal() {
        Random random = new Random();
        int numero = random.nextInt(1000000000); // 9 dígitos
        return String.format("%09d", numero);
    }

    private static BigDecimal gerarValorRecebivel() {
        Random random = new Random();
        double valor = random.nextDouble() * 1000.0; // Valor aleatório com 2 dígitos de precisão
        return BigDecimal.valueOf(valor);
    }

    private static String gerarDataVencimento() {
        Random random = new Random();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, random.nextInt(243) + 1); // 1 a 243 dias no futuro
        Date data = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(data);
    }

    public static void salvarJsonEmArquivo(String json, String caminhoArquivo) throws IOException {
        FileWriter writer = new FileWriter(caminhoArquivo);
        writer.write(json);
        writer.close();
    }
}
