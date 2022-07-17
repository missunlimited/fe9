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
            iMax = a.getLength();
        } else {
            iMax = b.getLength();
        }
        c.setData(0, 0L);

        for (i = 0; i < iMax; i++) {
            if (i <= a.getLength()) {
                c.getData()[i] += a.getData()[i];
            }
            if (i <= b.getLength()) {
                c.getData()[i] += b.getData()[i];
            }

            // 要素の大きさが9桁以内になるまで繰り上げを続ける
            if (c.getData()[i] >= 1000000000L) {
                    c.setData(i + 1, 1L);
                    c.getData()[i] -= 1000000000L;
            } else {
                c.setData(i + 1, 0L);
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
        int iMax;

        MP none = new MP(); // 空配列
        MP tmp = new MP();  // 計算時に用いる一時的な配列
        MP holder = new MP();   // 計算結果を保持する配列

        if (a.getLength() > b.getLength()) {
            iMax = a.getLength();
        } else {
            iMax = b.getLength();
        }

        for (int i = 0; i < b.getData()[0]; i++) {
            add(a, none, tmp);
            for (int j = 0; j < 100; j++) {
                holder.setData(j, holder.getData()[j] + tmp.getData()[j]);
            }
        }

        // 最終的な配列をcに格納
        for (int i = 0; i < 100; i++) {
            c.setData(i, c.getData()[i] + holder.getData()[i]);

            while (c.getData()[i] >= 1000000000L) {
                    c.setData(i + 1, c.getData()[i + 1] + 1L);
                    c.getData()[i] -= 1000000000L;
            }
        }

        if (c.getData()[iMax] == 0) {
            c.setLength(iMax);
        } else {
            c.setLength(iMax + 1);
        }
    }
}
