package a2.String;

public class StringReverse {
    public static void main(String[] args) {
        String str = "a!b@c";
        str = "a";
        str = "ab";
        char[] charArray = str.toCharArray();
        System.out.println("Input string: " + str);
        System.out.println("Reversed string: " + reverseString(charArray));
    }

    static boolean isAlphabet(char x) {
        return ((x >= 'A' && x <= 'Z') || (x >= 'a' && x <= 'z'));
    }

    static String reverseString(char[] strArray) {
        int r = strArray.length - 1;
        int l = 0;

        while (l < r) {
            if (!isAlphabet(strArray[l])) {
                l++;
            } else if (!isAlphabet(strArray[r])) {
                r--;
            } else {
                // swapping the characters
                char temp = strArray[l];
                strArray[l] = strArray[r];
                strArray[r] = temp;
                l++;
                r--;
            }
        }
        return new String(strArray);
    }
}

