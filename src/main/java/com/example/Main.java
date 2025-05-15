package com.example;
import com.example.service.implimentService.DigitalSignatureServiceImpl;
import com.example.service.interfaceService.DigitalSignatureService;
import java.security.NoSuchAlgorithmException;



public class Main {
    public static void main(String[] args){

        try{
            String src = "input.pdf";
            String dest = "output-signed.pdf";

            DigitalSignatureService signatureService = new DigitalSignatureServiceImpl();
            signatureService.signDocument(
                    src,
                    dest,
                    "Ali Zamanian",
                    "قرارداد با موفقیت امضا شد",
                    "تهران",
                    "alizamaniandev@gmail.com"
            );

            System.out.println("Your document signed successfully and save in -> \s" + dest);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
