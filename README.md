📄 Digital Signature for PDF with Java
This project uses iText and BouncyCastle libraries to digitally sign PDF documents.

💡 Implementation Overview
The project follows the Interface/Implementation pattern to separate the digital signature logic.

The DigitalSignatureService interface is implemented by the DigitalSignatureServiceImpl class.

Key pair generation and X.509 certificate creation are separated into dedicated services: KeyPairService and CertificateService.

If the output file (e.g., signed_output.pdf) already exists, it will be deleted before creating the newly signed file to avoid conflicts.

🛠 Technologies Used
Java 8+

iText 7

BouncyCastle



How to Set Up and Run the Java Project:
Install the project dependencies.
Run the generateKeys.bat file to generate the private-key.pem file. (Note: The certificate is not necessary because the app generates it dynamically.)
If you want Acrobat Reader to trust the user, rename cert.pem to cert.crt and import it into Acrobat Reader.
Finally, run the application.
