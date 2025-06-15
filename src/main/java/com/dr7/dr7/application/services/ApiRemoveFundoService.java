//package com.dr7.dr7.application.services;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.entity.ContentType;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//
//@Service
//public class ApiRemoveFundoService {
//    public String  main(String[] args) throws IOException {
//        String apiKey = "SUA_CHAVE_AQUI"; // 🔒 Substitua pela sua chave do remove.bg
//        File inputImage = new File("caminho/da/imagem.jpg");
//        File outputImage = new File("sem-fundo.png");
//
//        HttpEntity entity = MultipartEntityBuilder.create()
//                .addBinaryBody("image_file", inputImage, ContentType.DEFAULT_BINARY, inputImage.getName())
//                .addTextBody("size", "auto")
//                .build();
//
//        Request.Post("https://api.remove.bg/v1.0/removebg")
//                .addHeader("X-Api-Key", apiKey)
//                .body(entity)
//                .execute()
//                .saveContent(outputImage);
//
//        System.out.println("Imagem salva sem fundo: " + outputImage.getAbsolutePath());
//    }
//}
