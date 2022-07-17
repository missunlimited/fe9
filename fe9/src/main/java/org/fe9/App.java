package org.fe9;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使わない方をコメントアウトして使用
 */
public class App
{
//    public static void main( String[] args )
//    {
//        Scanner scanner = new Scanner(System.in);
//        MPController mpController = new MPController();
//        Pattern pattern = Pattern.compile("^[0-9]+$");
//
//        System.out.println("多倍長整数を入力してください。");
//        String str = scanner.next();
//        Matcher matcher = pattern.matcher(str);
//        if (matcher.find()) {
//            MP mp = new MP();
//            mpController.set(mp, str);
//            mpController.print(mp);
//        } else {
//            System.out.println("数字を入力してください。");
//            return;
//        }
//
//        System.out.println("==================================================================================" +
//                            "=================================================================================" +
//                            "=============================================================");
//
//        MP mpA = new MP();
//        MP mpB = new MP();
//        MP mpC = new MP();
//        mpController.add(mpA, mpB, mpC);
//        mpController.print(mpC);
//    }

    /**
     * サンプルクラス実行用エントリーポイント
     * 以下使用する数値はテスト値
     */
    public static void main(String[] args) {
        MPControllerSample mpControllerSample = new MPControllerSample();
        MP mp = new MP();
        String str = "9223372036854775807";
        mpControllerSample.set(mp, str);
        mpControllerSample.print(mp);

        MP mpA = new MP();
        MP mpB = new MP();
        MP mpC = new MP();
        mpControllerSample.set(mpA, "9223372036854775807");
        mpControllerSample.set(mpB, "256");
        // 乗算処理
        mpControllerSample.multiple(mpA, mpB, mpC);
        mpControllerSample.print(mpC);
    }
}
