package cn.com.nightfield.string;

import java.util.Random;

/**
 * @author: zhochi
 * @create: 2020/11/23
 **/
public class PasswordGenerator {
    Random random = new Random();
    public static void main(String[] args) {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        for (int i = 0; i < 10; i++) {
            System.out.println(passwordGenerator.generatePassword());
        }
    }

    public String generatePassword() {
        StringBuilder builder = new StringBuilder();
        // 2 upper cases
        builder.append(generateAsciiString(2, 26, 'A'));
        // 2 numbers
        builder.append(generateAsciiString(2, 10, '0'));
        // 2 lower cases
        builder.append(generateAsciiString(2, 26, 'a'));
        // 2 special characters "( ) * + , - ."  other characters may not allowed by policy
        builder.append(generateAsciiString(2, 6, '('));
        // 4 random characters(letter/number/special character)
        for (int i = 0; i < 4; i++) {
            switch (random.nextInt(4)) {
                case 0:
                    builder.append(generateAsciiString(1, 26, 'A'));
                    break;
                case 1:
                    builder.append(generateAsciiString(1, 10, '0'));
                    break;
                case 2:
                    builder.append(generateAsciiString(1, 26, 'a'));
                    break;
                case 3:
                    builder.append(generateAsciiString(1, 6, '('));
                    break;
            }
        }
        return builder.toString();
    }

    // http://www.asciitable.com/
    private String generateAsciiString(int length, int asciiGap, int asciiStart) {
        if (length <= 0) {
            return "";
        }
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = (char) (random.nextInt(asciiGap) + asciiStart);
        }
        return String.valueOf(chars);
    };
}
