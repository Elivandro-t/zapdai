package com.dr7.dr7.application.services.PlanosServices;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
@Service
public class PlanoMerc {
    @Value("${mercado_pago_sample_access_token}")
    String token;
    public String createPlan(String reason, BigDecimal amount) {
        try {
            URL url = new URL("https://api.mercadopago.com/preapproval_plan");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json = """
            {
              "reason": "%s",
              "auto_recurring": {
                "frequency": 1,
                "frequency_type": "months",
                "transaction_amount": %s,
                "currency_id": "BRL"
              },
              "status": "active"
            }
            """.formatted(reason, amount);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();

            try (Scanner scanner = new Scanner(
                    responseCode >= 200 && responseCode < 300 ? conn.getInputStream() : conn.getErrorStream())) {
                String response = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                if (responseCode >= 200 && responseCode < 300) {
                    JSONObject jsonObject = new JSONObject(response);
                    String initPoint = jsonObject.getString("id");
                    System.out.println("Plano criado com ID: " + initPoint);
                    return response;
                } else {
                    System.err.println("Erro na criação do plano: " + response);
                    throw new RuntimeException("Erro na criação do plano: " + response);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar plano: " + e.getMessage(), e);
        }
    }

}
