package aaronbearon.chapter4;

public class J104_LectureNote {
    public static void main(String[] args) {
        double result01 = Math.pow(2, 16);
        System.out.println("2^16 = " + (int) result01);
        double result02 = Math.pow(2, 7);
        System.out.println((int) result02);

        System.out.println("\u03b1 \u03b2 \u03b3");

        char ch1 = 'A';
        char ch2 = 'a';
        char ch3 = '0';

        System.out.println("A in ASCII (decimal): " + (int) ch1);  // 65
        System.out.println("a in ASCII (decimal): " + (int) ch2);  // 97
        System.out.println("0 in ASCII (decimal): " + (int) ch3);  // 48

        System.out.println("A in Unicode: \\u" + String.format("%04X", (int) ch1));
        System.out.println("a in Unicode: \\u" + String.format("%04X", (int) ch2));
        System.out.println("0 in Unicode: \\u" + String.format("%04X", (int) ch3));

        // https://www.ssec.wisc.edu/~tomw/java/unicode.html#x0000

        int i = 'a';          // character → number
        char c = 97;          // number → character
        System.out.println("int i = 'a'; i = " + i);   // 97
        System.out.println("char c = 97; c = " + c);   // a


        char ch = 'G';   // test character G
        if (ch >= 'A' && ch <= 'Z')
            System.out.println(ch + " is an uppercase letter");
        else if (ch >= 'a' && ch <= 'z')
            System.out.println(ch + " is a lowercase letter");
        else if (ch >= '0' && ch <= '9')
            System.out.println(ch + " is a numeric character");
        else
            System.out.println(ch + " is some other character");

        int p = 'A';
        int q = 'Z';
        int j = 'G';
        System.out.println(p);
        System.out.println(q);
        System.out.println(j);


        char ch4 = 'A';
        char ch5 = '7';
        char ch6 = 'b';
        System.out.println(Character.isDigit(ch4));       // false
        System.out.println(Character.isDigit(ch5));       // true
        System.out.println(Character.isLetter(ch4));      // true
        System.out.println(Character.isLetterOrDigit(ch5)); // true
        System.out.println(Character.isLowerCase(ch6));   // true
        System.out.println(Character.isUpperCase(ch4));   // true
        System.out.println(Character.toLowerCase(ch4));   // 'a'
        System.out.println(Character.toUpperCase(ch6));   // 'B'

        String s = "  Hello World  ";
        System.out.println("Length: " + s.length());             // 15
        System.out.println("CharAt(1): " + s.charAt(1));         // ' '
        System.out.println("Concat: " + s.concat("!!!"));        // "  Hello World  !!!"
        System.out.println("UpperCase: " + s.toUpperCase());     // "  HELLO WORLD  "
        System.out.println("LowerCase: " + s.toLowerCase());     // "  hello world  "
        System.out.println("Trim: '" + s.trim() + "'");          // "Hello World"
    }
}
