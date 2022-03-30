
### ICS 254 Project
# RSA-Encryption

This code has been written on Java, using only one class and no external libraries.
To use it, please follow these next steps.

## Encryption
#### Pre-conditions
 - You should have a valid text file with a proper encryption key, public key, and a proper text to encrypt.
--  The accepted text language is any character of [this string](https://github.com/Kazero21/RSA-Encryption/blob/3dbeaa40acd2ed3f14194485f72baf71dc36d611/src/MainClass.java#L9).
 - In the message file, you need to write the encryption key (**e**) first, then the public key (**n**) second, followed by the message starting in the **next line**.
 - Your message file should be named message.txt and be placed in the working folder.
-- You could change this behavior in the code, go to line [**41**](https://github.com/Kazero21/RSA-Encryption/blob/3dbeaa40acd2ed3f14194485f72baf71dc36d611/src/MainClass.java#L41) and write the file path that you want.
#### Post-condition
 - The output file will be a text file named message.rsa placed in the working folder.
-- You could change this behavior in the code, go to line [**81**](https://github.com/Kazero21/RSA-Encryption/blob/3dbeaa40acd2ed3f14194485f72baf71dc36d611/src/MainClass.java#L81) and write the file path that you want.

## Decryption
#### Pre-conditions
- You should have a valid text file with a proper private key, public key, and a proper encrypted .rsa file that has been encrypted with exactly the same language with the same order specified in the Encryption's pre-condition.
- Your message file should be named message.rsa and be placed in the working folder.
-- You could change this behavior in the code, go to line [**93**](https://github.com/Kazero21/RSA-Encryption/blob/3dbeaa40acd2ed3f14194485f72baf71dc36d611/src/MainClass.java#L93) and write the file path that you want.
### Post-condition
 - The output file will be a text file named message.dec placed in the working folder.
-- You could change this behavior in the code, go to line [**141**](https://github.com/Kazero21/RSA-Encryption/blob/3dbeaa40acd2ed3f14194485f72baf71dc36d611/src/MainClass.java#L141) and write the file path that you want.
  - a prompt message will appear to tell the user that the decrypted file has been crated.

## To Compile and Run
- This code has been written in JavaSE-15, it is suggested to compile it with that version to ensure minimal bugs and defects.
- Run the code and follow the provided instructions inside the code to encrypt/decrypt.
