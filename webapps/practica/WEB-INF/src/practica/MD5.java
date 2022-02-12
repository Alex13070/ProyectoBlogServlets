package practica;

import java.security.MessageDigest;
// import java.util.Arrays;
// import javax.crypto.Cipher;
// import javax.crypto.SecretKey;
// import javax.crypto.spec.SecretKeySpec;
// import org.apache.commons.codec.binary.Base64;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

/**
 * Clase en la que se almacenan las funciones de encriptacion de las password
 */
public class MD5 {

    /**
     * Encripta la password
     * @param input password
     * @return password encriptada
     */
    public static String encriptar(String input) {

        String hashtext = null;

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error en el encriptado de la password");
        }

        return hashtext;
    }

    /*

    No es posible desencriptar
    public static String desencriptar(String textoEncriptado) {

        String secretKey = "qualityinfosolutions"; // llave para desenciptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
            System.err.println("Error al desenciptar");
        }
        return base64EncryptedString;
    }
    */

}
