package com.karlmarxindustries.grindripsum.dao;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
@Repository
public class DaoImpl {
    private final String PHRASE_LIST = "list.txt";
    String pathToDirectory = "Novels/";

    public List<String> getStringList() {
        List<String> curseList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(PHRASE_LIST)));
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                if (!(currentLine.equals("")) && !(currentLine.equals(" "))) {
                    curseList.add(currentLine);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        return curseList;
    }

    private int wordCount = 0;
    private List<String> STRING_LIST = this.getStringList();
    private static final String[] SEN_TERMS = new String[]{".", ".", ".", ".", ".", ".", ".", ".", "!", "?"};
    Random random = new Random(System.currentTimeMillis());
    static int DEF_SEN_MAX_WORDS = 12;
    static int DEF_SEN_MIN_WORDS = 4;
    private static final String WORD_SEPARATOR = " ";
    private static final String PHRASE_SEPARATOR = ",";


    public String getRandomWord() {
        return STRING_LIST.get(random.nextInt(STRING_LIST.size()));
    }

    public String buildSentence() {
        return buildSentence(random.nextInt(DEF_SEN_MAX_WORDS - DEF_SEN_MIN_WORDS) + DEF_SEN_MIN_WORDS);
    }

    public String buildSentence(int wordNum) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.capitalize(getRandomWord()));
        wordCount++;
        for (int i = 1; i < wordNum; i++) {
            sb.append(WORD_SEPARATOR);
            sb.append(getRandomWord());
            if (i < (wordNum - 2)) {
                if (random.nextInt(8) == 7) {
                    sb.append(PHRASE_SEPARATOR);
                }
            }
            wordCount++;
        }
        sb.append(SEN_TERMS[random.nextInt(SEN_TERMS.length)]);
        sb.append(WORD_SEPARATOR);
        return sb.toString();
    }

    public String buildParagraph() {
        int sentenceNum = random.nextInt(2) + 6;
        StringBuilder sb = new StringBuilder();
        sb.append("\t");
        for (int i = 0; i < sentenceNum; i++) {
            sb.append(buildSentence());
        }
        sb.append("\n");
        return sb.toString();
    }

    public String buildParagraphs(int paragraphNum) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paragraphNum; i++) {
            sb.append(buildParagraph());
        }
        return sb.toString();
    }

    public String generateNovel() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; wordCount < 50000; i++) {
            sb.append(buildParagraph());
        }

        return sb.toString();
    }

    public void writeNovel() throws Exception {

        FileUtils.writeStringToFile(new File(pathToDirectory + File.separator + "novel.text"), generateNovel(), "US-ASCII");

    }
}