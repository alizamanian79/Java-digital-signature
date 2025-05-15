package com.example.service.implimentService;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Date;

public class CertificateService {
    public X509Certificate generateCertificate(KeyPair keyPair, String userName) throws Exception {
        long now = System.currentTimeMillis();
        Date startDate = new Date(now);
        Date expiryDate = new Date(now + 365L * 24 * 60 * 60 * 1000);

        BigInteger serialNumber = BigInteger.valueOf(new SecureRandom().nextInt() & 0x7fffffff);
        String dnName = "CN=" + userName;

        X500Name issuer = new X500Name(dnName);
        X500Name subject = new X500Name(dnName);

        ContentSigner contentSigner = new JcaContentSignerBuilder("SHA256WithRSA")
                .build(keyPair.getPrivate());

        var certBuilder = new JcaX509v3CertificateBuilder(
                issuer,
                serialNumber,
                startDate,
                expiryDate,
                subject,
                keyPair.getPublic()
        );

        return new JcaX509CertificateConverter().setProvider("BC").getCertificate(certBuilder.build(contentSigner));
    }
}
