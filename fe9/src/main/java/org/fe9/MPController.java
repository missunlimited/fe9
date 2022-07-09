package org.fe9;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MPController {

    /**
     * 文字列で与えられた多倍長整数strを変換して、MP型の構造体numに格納する。
     * numのメンバdataは、返還後の数値を格納するのに十分な要素数が確保されているものとする。
     * @param num
     * @param str
     */
    public void set(MP num, String str) {
        int begin = 0;
        int end = 9;

//        System.out.println(str.length());  // 文字列長確認用

        StringBuilder stringBuilder = new StringBuilder(str);
        str = stringBuilder.reverse().toString();  // 逆転しString型に変換

        while ((str.length() % 9) != 0) { // 9文字区切りの余り部分を取り出すため、0で足りない桁数を補填
            str += "0";
        }

        // String型のバッファに格納
        ArrayList<String> stringBuffer = new ArrayList<>();
        for (int i = 0; i < (str.length() / 9); i++){
            stringBuffer.add(str.substring(begin, end));   // 先頭から9文字抜き出す
            begin += 9;    // 次の9文字を取り出すためにシフト
            end += 9;
        }

//        System.out.println(stringBuffer);   // 逆転した9文字ずつのString型要素が格納された配列

        // 配列要素の逆転処理
        int indexBuffer = 0;
        for (String stringData : stringBuffer) {
            StringBuilder reverseStringData = new StringBuilder(stringData);
            stringData = String.valueOf(reverseStringData.reverse());
            num.setData(indexBuffer, Long.parseLong(stringData));   // long型に変換してdataに格納
//            System.out.print(num.getData()[indexBuffer] + ", ");    // data内要素確認用
            indexBuffer++;
        }
    }

    /**
     * 多倍長整数を出力する。
     * @param num
     */
    public void print(MP num) {
        int counter = 0;
        System.out.println("\n多倍長整数の出力(要素番号は左から昇順)");
        for (long printData : num.getData()) {
            if (printData != 0) {
                System.out.print(printData + "  ");
                counter++;  // 0でない要素の時だけインクリメント
            }
        }

        num.setLength(counter);

        System.out.println("\n配列のサイズ : " + num.getLength());
    }

    /**
     * 二つの多倍長整数a, bの和を多倍長整数cに格納する。
     * cのメンバdataは、加算処理を行うのに十分な要素数が確保されているものとする。
     * @param a
     * @param b
     * @param c
     */
    public void add(MP a, MP b, MP c) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1つ目の多倍長整数を入力してください。");
        String strA = scanner.next();

        // strAで数字以外が入力された時の例外処理
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcherA = pattern.matcher(strA);
        if (matcherA.find()) {
            this.set(a, strA);
        } else {
            System.out.println("数字を入力してください。");
            return;
        }

        System.out.println("2つ目の多倍長整数を入力してください。");
        String strB = scanner.next();

        // strBで数字以外が入力された時の例外処理
        Matcher matcherB = pattern.matcher(strB);
        if (matcherB.find()) {
            this.set(b, strB);
        } else {
            System.out.println("数字を入力してください。");
            return;
        }

        // 長さを取得するため配列内の0要素を削除してlong型のバッファに格納
        ArrayList<Long> longBufferA = new ArrayList<>();
        int bufferCounterA = 0;
        for (long longBufferDataA : a.getData()) {
            if (longBufferDataA != 0) {
                longBufferA.add(longBufferDataA);
                bufferCounterA++;
            }
        }
        a.setLength(bufferCounterA); // aの配列長さを取得しセット

        // 配列を反転しaのメンバdataにセット
        int setCounterA = 0;
        for (int i = bufferCounterA; i > 0; i--) {
            a.setData(i, longBufferA.get(i - 1));
            setCounterA++;
        }

        // String型に変換し各要素を連結
        String couplingA = "";
        for (long stringDataA : a.getData()) {
            if (stringDataA != 0) {
                String.valueOf(stringDataA);
                couplingA += stringDataA;
            }
        }

        // long型に変換
        long multiCharNumA = Long.parseLong(couplingA);

        // bについても同様の処理を行う
        // 長さを取得するため配列内の0要素を削除してlong型のバッファに格納
        ArrayList<Long> longBufferB = new ArrayList<>();
        int bufferCounterB = 0;
        for (long longBufferDataB : b.getData()) {
            if (longBufferDataB != 0) {
                longBufferB.add(longBufferDataB);
                bufferCounterB++;
            }
        }
        b.setLength(bufferCounterB); // aの配列長さを取得しセット

        // 配列を反転しaのメンバdataにセット
        int setCounterB = 0;
        for (int i = bufferCounterB; i > 0; i--) {
            b.setData(i, longBufferB.get(i - 1));
            setCounterB++;
        }

        // String型に変換し各要素を連結
        String couplingB = "";
        for (long stringDataB : b.getData()) {
            if (stringDataB != 0) {
                String.valueOf(stringDataB);
                couplingB += stringDataB;
            }
        }

        // long型に変換
        long multiCharNumB = Long.parseLong(couplingB);

        // 加算処理の結果をcのメンバdataに格納する
        this.set(c, String.valueOf(multiCharNumA + multiCharNumB));
    }
}
