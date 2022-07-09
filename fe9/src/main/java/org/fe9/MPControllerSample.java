package org.fe9;

public class MPControllerSample {

    /**
     * 文字列で与えられた多倍長整数strを変換して、MP型の構造体numに格納する。
     * numのメンバdataは、返還後の数値を格納するのに十分な要素数が確保されているものとする。
     * @param num
     * @param str
     */
    public void set(MP num, String str) {
        int strIdx = str.length() - 1;
        int numIdx = 0;
        int i;
        long mul;

        while (strIdx >= 0) {
            num.getData()[numIdx] = 0;
            mul = 1;
            // 下位から9桁ずつ抽出してnumのメンバdataに格納
            for (i = 0; i < 9 && strIdx >= 0; i++) {
                // strを数字に変換
                num.getData()[numIdx] += mul * (Long.parseLong(String.valueOf(str.charAt(strIdx--))));
                mul *= 10;
            }
            numIdx++;
        }
        num.setLength(numIdx);
    }

    /**
     * 多倍長整数を出力する。
     * @param num
     */
    public void print(MP num) {
        int i;

        System.out.printf("%d", num.getData()[num.getLength() - 1]);
        for (i = num.getLength() - 2; i >= 0; i--) {
            // ゼロ詰めして必ず9桁を表示する
            System.out.printf("%09d", num.getData()[i]);
        }
        System.out.print("\n");
    }

    /**
     * 二つの多倍長整数a, bの和を多倍長整数cに格納する。
     * cのメンバdataは、加算処理を行うのに十分な要素数が確保されているものとする。
     * @param a
     * @param b
     * @param c
     */
    public void add(MP a, MP b, MP c) {
        int i;
        int iMax;

        if (a.getLength() > b.getLength()) {
            iMax = a.getLength() + 1;
        } else {
            iMax = b.getLength() + 1;
        }
        c.setData(0, 0);

        for (i = 0; i < iMax; i++) {
            if (i <= a.getLength()) {
                c.getData()[i] += a.getData()[i];
            }
            if (i <= b.getLength()) {
                c.getData()[i] += b.getData()[i];
            }

            // 要素の大きさが9桁以内になるまで繰り上げを続ける
            if (c.getData()[i] >= 1000000000) {
                int counter = 1;
                while (c.getData()[i] >= 1000000000) {
                    c.setData(i + 1, counter);
                    c.getData()[i] -= 1000000000;
                    counter++;
                }
            } else {
                c.setData(i + 1, 0);
            }
        }
        if (c.getData()[i] == 0) {
            c.setLength(i);
        } else {
            c.setLength(i + 1);
        }
    }

    /**
     * 乗算処理
     * @param a
     * @param b
     * @param c
     */
    public void multiple(MP a, MP b, MP c) {
        int i;
        int iMax;

        if (a.getLength() > b.getLength()) {
            iMax = a.getLength() + 1;
        } else {
            iMax = b.getLength() + 1;
        }
        c.setData(0, 0);

        for (i = 0; i < iMax; i++) {

            // TODO b.getData[0]が0になるまでa.getData[i]を足す
            while (b.getData()[0] > 0) {
                if (i <= a.getLength()) {

                    // TODO オーバーフローの判定
                    c.getData()[i] += a.getData()[i];

                }
                b.getData()[0]--;
            }

            // 要素の大きさが9桁以内になるまで繰り上げを続ける
            if (c.getData()[i] >= 1000000000) {
                int counter = 1;
                while (c.getData()[i] >= 1000000000) {
                    c.setData(i + 1, counter);
                    c.getData()[i] -= 1000000000;
                    counter++;
                }
            } else {
                c.setData(i + 1, 0);
            }
        }
        if (c.getData()[i] == 0) {
            c.setLength(i);
        } else {
            c.setLength(i + 1);
        }
    }

}
