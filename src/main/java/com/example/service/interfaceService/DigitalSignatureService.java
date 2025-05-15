package com.example.service.interfaceService;

import java.security.NoSuchAlgorithmException;

public interface DigitalSignatureService {
    void signDocument(String filePath, String destinationPath,
                      String signer, String reason,
                      String location, String contact) throws NoSuchAlgorithmException;
}
