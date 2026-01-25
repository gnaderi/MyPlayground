import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

public class SimpleSignalProtocolE2EEncryption {

    // 1. Generate RSA Key Pair (Recipient side)
    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        return keyPairGen.generateKeyPair();
    }

    // 2. Encrypt the AES Secret Key using Recipient's RSA Public Key (Sender side)
    public static byte[] encryptAESKey(SecretKey aesKey, java.security.PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(aesKey.getEncoded());
    }

    // 3. Encrypt the Message using AES (Sender side)
    public static String encryptMessage(String message, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 4. Decrypt the AES Key using Recipient's RSA Private Key (Recipient side)
    public static SecretKey decryptAESKey(byte[] encryptedAesKey, java.security.PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedKey = cipher.doFinal(encryptedAesKey);
        return new SecretKeySpec(decryptedKey, 0, decryptedKey.length, "AES");
    }

    // 5. Decrypt the Message using the decrypted AES Key (Recipient side)
    public static String decryptMessage(String encryptedMessage, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        // SETUP: Recipient generates their keys
        KeyPair recipientKeyPair = generateRSAKeyPair();

        // STEP 1: Sender generates a one-time symmetric AES key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey sessionKey = keyGen.generateKey();

        // STEP 2: Sender encrypts the actual message with the AES key
        String originalMessage = "Secret WhatsApp Message";
        System.out.println("Original message: " + originalMessage);

        String encryptedMsg = encryptMessage(originalMessage, sessionKey);

        System.out.println("Encrypted message: " + encryptedMsg);

        // STEP 3: Sender encrypts the AES key with Recipient's Public RSA Key
        byte[] encryptedSessionKey = encryptAESKey(sessionKey, recipientKeyPair.getPublic());

        // --- DATA TRANSMITTED OVER INSECURE CHANNEL ---

        // STEP 4: Recipient decrypts the AES session key using their Private RSA Key
        SecretKey decryptedSessionKey = decryptAESKey(encryptedSessionKey, recipientKeyPair.getPrivate());

        // STEP 5: Recipient decrypts the message using the session key
        String finalMessage = decryptMessage(encryptedMsg, decryptedSessionKey);

        System.out.println("Decrypted: " + finalMessage);
    }
}
