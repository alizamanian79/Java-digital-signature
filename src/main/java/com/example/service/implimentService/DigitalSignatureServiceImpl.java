package com.example.service.implimentService;

import com.example.service.interfaceService.DigitalSignatureService;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.StampingProperties;
import com.itextpdf.signatures.*;

import java.io.File;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import org.bouncycastle.jce.provider.BouncyCastleProvider;



public class DigitalSignatureServiceImpl implements DigitalSignatureService {

    static {

        Security.addProvider(new BouncyCastleProvider());
    }

    @Override
    public void signDocument(String filePath, String destinationPath,
                             String signer, String reason,
                             String location, String contact) {
        try {

            File outputFile = new File(destinationPath);
            if (outputFile.exists()) {
                if (!outputFile.delete()) {
                    return;
                }
            }

            KeyPairService keyPairService = new KeyPairService();
            CertificateService certificateService = new CertificateService();

            var keyPair = keyPairService.generateKeyPair();
            var cert = certificateService.generateCertificate(keyPair, signer);
            Certificate[] chain = new Certificate[]{cert};
            PrivateKey privateKey = keyPair.getPrivate();

            PdfSigner signerPdf = new PdfSigner(
                    new PdfReader(filePath),
                    new FileOutputStream(destinationPath),
                    new StampingProperties()
            );

            signerPdf.getSignatureAppearance()
                    .setReason(reason)
                    .setLocation(location)
                    .setContact(contact)
                    .setReuseAppearance(false);

            IExternalSignature pks = new PrivateKeySignature(privateKey, DigestAlgorithms.SHA256, "BC");
            IExternalDigest digest = new BouncyCastleDigest();

            signerPdf.signDetached(digest, pks, chain, null, null, null, 0, PdfSigner.CryptoStandard.CADES);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
