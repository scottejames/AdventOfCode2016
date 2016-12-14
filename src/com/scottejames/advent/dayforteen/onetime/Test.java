package com.scottejames.advent.dayforteen.onetime;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author /u/Philboyd_Studge on 12/13/2016.
 */
public class Test {

    static Pattern p = Pattern.compile("([a-zA-Z\\d])\\1\\1");

    static String has3(String s) {
        Matcher m = p.matcher(s);
        if (m.find()) return m.group(1);
        return "";
    }

    static boolean has5(String s, String t) {
        String five = "";
        for (int i = 0; i < 5; i++) {
            five += s;
        }
        return t.contains(five);
    }

    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public static void main(String[] args) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");

        boolean part2 = false;
        String salt = "abc";

        List<Integer> keys = new ArrayList<>();
        Map<Integer, String> map = new HashMap<>();

        int index = 0;

        while (keys.size() < 64) {
            String temp = salt + index;
            String hex;
            if (map.containsKey(index)) {
                hex = map.get(index);
            } else {
                md.update(temp.getBytes());
                byte[] hash = md.digest();
                hex = bytesToHex(hash);
                if (part2) {
                    for (int i = 0; i < 2016; i++) {
                        md.update(hex.getBytes());
                        hex = bytesToHex(md.digest());
                    }
                }
                map.putIfAbsent(index, hex);
            }

            String c = has3(hex);

            if (!c.equals("")) {
                String hex2;
                for (int i = index + 1; i < index + 1000; i++) {
                    if (map.containsKey(i)) {
                        hex2 = map.get(i);
                    } else {
                        temp = salt + i;
                        md.update(temp.getBytes());
                        byte[] hash = md.digest();
                        hex2 = bytesToHex(hash);
                        if (part2) {
                            for (int j = 0; j < 2016; j++) {
                                md.update(hex2.getBytes());
                                hex2 = bytesToHex(md.digest());
                            }
                        }
                        map.putIfAbsent(i, hex2);
                    }
                    if (has5(c, hex2)) {
                        System.out.println("index: " + index + " : i: " + i + " : c: " + c);
                        keys.add(index);
                        break;
                    }

                }
            }
            index++;
        }
        System.out.println(keys.get(keys.size() - 1));
    }
}