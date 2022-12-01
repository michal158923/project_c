package com.example.project_c.model.quiz.random_code;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class RandomCode {
    private int minQtyLowercase;
    private int minQtyUppercase;
    private int minQtyNumber;
    private int minQtySpecial;
    private int codeLength;
    private final char[] tableOfLowercaseLetter = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private final char[] tableOfUppercaseLetter = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final char[] tableOfNumber = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final char[] tableOfSpecial = {
            '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '-', '=', '+', '*', '?', '[', ']', '{', '}'};

    private static class Family {
        private final int qty;
        private final char[] tableOfChar;
        private final char[] tableOfResult; // inteliJ podpowiada żeby wszedzie dac final i dałem chociaz mam wątpliwości

        public Family(int qty, char[] tableOfChar) {
            this.qty = qty;
            this.tableOfChar = tableOfChar;
            this.tableOfResult = setTableOfResult(qty, tableOfChar);
        }

        private char[] setTableOfResult(int qty, char[] tableOfChar) {
            char[] tableOfResult = new char[qty];
            for (int i = 0; i < qty; i++) {
                tableOfResult[i] = tableOfChar[(int) (Math.random() * tableOfChar.length)];
            }
            return tableOfResult;
        }

        public char[] getTableOfResult() {
            return tableOfResult;
        }

        @Override
        public String toString() {
            return "Family{" +
                    "qty=" + qty +
                    ", tableOfChar=" + Arrays.toString(tableOfChar) +
                    ", tableOfResult=" + Arrays.toString(tableOfResult) +
                    '}';
        }
    }

    public RandomCode(int minQtyLowercase, int minQtyUppercase, int minQtyNumber, int minQtySpecial, int codeLength) {
        this.minQtyLowercase = minQtyLowercase;
        this.minQtyUppercase = minQtyUppercase;
        this.minQtyNumber = minQtyNumber;
        this.minQtySpecial = minQtySpecial;
        this.codeLength = codeLength;
    }

    void modifyMinQty(/*int minQtyLowercase, int minQtyUppercase, int minQtyNumber, int minQtySpecial, int codeLength*/) {
//        log.info("Qty before modification: {}, {}, {}, {}", minQtyLowercase, minQtyUppercase, minQtyNumber, minQtySpecial);
        int difference = codeLength - (minQtyLowercase + minQtyUppercase + minQtyNumber + minQtySpecial);
        while (difference > 0) {
            switch ((int) (Math.random() * 4)) {
                case 0:
                    if (minQtyLowercase != 0) {
                        minQtyLowercase++;
                        difference--;
                    }
                    break;
                case 1:
                    if (minQtyUppercase != 0) {
                        minQtyUppercase++;
                        difference--;
                    }

                    break;
                case 2:
                    if (minQtyNumber != 0) {
                        minQtyNumber++;
                        difference--;
                    }
                    break;
                case 3:
                    if (minQtySpecial != 0) {
                        minQtySpecial++;
                        difference--;
                    }
                    break;
            }

        }
//        log.info("Qty after modification: {}, {}, {}, {}", minQtyLowercase, minQtyUppercase, minQtyNumber, minQtySpecial);
    }

    Family[] createInstances() {
        return new Family[]{
                new Family(minQtyLowercase, tableOfLowercaseLetter),
                new Family(minQtyUppercase, tableOfUppercaseLetter),
                new Family(minQtyNumber, tableOfNumber),
                new Family(minQtySpecial, tableOfSpecial)};

    }

    char[] addCharToCollection(char element, char[] table) {
        if (table == null) return new char[]{element};
        int collectionLength = table.length;
        int newCollectionLength = collectionLength + 1;
        char[] newCollection = new char[newCollectionLength];
        System.arraycopy(table, 0, newCollection, 0, collectionLength);
        newCollection[newCollectionLength - 1] = element;
        return newCollection;
    }

    char[] getTablesOfResults(Family[] families, int codeLength) {
        char[] tablesOfResults = new char[0];
        for (Family f : families) {
            for (char r : f.getTableOfResult()) {
                tablesOfResults = addCharToCollection(r, tablesOfResults);
            }
        }
//        log.info("tablesOfResults: {}", tablesOfResults);
        return tablesOfResults;
    }

    public static int[] randomOrder(int length) {
        int[] table = new int[length];
        int[] newTable = new int[length];
        for (int i = 0; i < length; i++) table[i] = i;
        for (int i = 0; i < length; i++) {
            int rangeOfRandom = length - i;
            int r = (int) (Math.random() * rangeOfRandom);
            int randomNumber = table[r];
            newTable[i] = randomNumber;
            for (int j = r; j < (length); j++) {
                if (j == (length - 1)) table[j]++;
                else table[j] = table[j + 1];
            }
        }
//        log.info("randomOrder: {}", Arrays.toString(newTable));
        return newTable;
    }

    public static int[] randomNumberByRange(int Range, int size) {
        int[] variable = randomOrder(Range);
        int[] result = new int[size];
        System.arraycopy(variable, 0, result, 0, size);
        return result;
    }

    public String generate() {
        modifyMinQty();
        char[] tablesOfResult = getTablesOfResults(createInstances(), codeLength);
        int[] randomOrder = randomOrder(codeLength);
        StringBuilder resultCode = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            resultCode.append(tablesOfResult[randomOrder[i]]);
        }
        return resultCode.toString();
    }
}
