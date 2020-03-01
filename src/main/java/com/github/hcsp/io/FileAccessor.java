package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file) {
        List<String> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line =  bufferedReader.readLine())!=null){
                list.add(line);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

   public static List<String> readFile2(File file) {
        List<String> list = new ArrayList<>();
       try {
           list = FileUtils.readLines(file, Charset.defaultCharset());
       } catch (IOException e) {
           e.printStackTrace();
       }
       return list;
   }

   public static List<String> readFile3(File file) {
        List<String> list =new ArrayList<>();
       try {
           FileInputStream fileInputStream = new FileInputStream(file);
           int readInt;
           StringBuilder stringBuilder = new StringBuilder();
           while ((readInt=fileInputStream.read())!=-1){
               if (readInt==10||readInt==13){
                   list.add(stringBuilder.toString());
                   stringBuilder.delete(0,stringBuilder.length());
               } else {
                   stringBuilder.append((char) readInt);
               }
           }
           fileInputStream.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
       return list;
   }

    public static void writeLinesToFile1(List<String> lines, File file) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String line:lines) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void writeLinesToFile2(List<String> lines, File file) {
        try {
            FileUtils.writeLines(file,lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLinesToFile3(List<String> lines, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            for (String line:lines) {
                for (int i = 0; i < line.length(); i++) {
                    fileOutputStream.write(line.charAt(i));
                }
                fileOutputStream.write(10);
            }
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        File testFile = new File(projectDir, "target/test.txt");
        List<String> lines = Arrays.asList("AAA", "BBB", "CCC");
        writeLinesToFile1(lines, testFile);
        writeLinesToFile2(lines, testFile);
        writeLinesToFile3(lines, testFile);

        System.out.println(readFile1(testFile));
        System.out.println(readFile2(testFile));
        System.out.println(readFile3(testFile));
    }
}
