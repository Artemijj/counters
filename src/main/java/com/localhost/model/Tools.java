package com.localhost.model;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;

public class Tools {
    public static int parse(String string) {
        int selectedNumber = Integer.MAX_VALUE;
        try {
            selectedNumber = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            System.out.println("Введённый символ не является числом.");
        }
        return selectedNumber;
    }

    public static synchronized <T> int nextId(ArrayList<T> list) {
        ArrayList<Id> newList = (ArrayList<Id>) list;
        return (newList.size() == 0) ? 1 : newList.stream().map(Id::getId).max(Comparator.naturalOrder()).get() + 1;
    }

    public static String getMD5Hash(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes(StandardCharsets.UTF_8));
            byte[] messageDigest = md.digest();
            return DatatypeConverter.printHexBinary(messageDigest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
        return "";
    }
}
