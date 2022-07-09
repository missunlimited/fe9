package org.fe9;

public class MP {
    private int length; // 配列のサイズ
    private final long[] data;   // 多倍長整数を格納する配列

    public MP() {
        this.length = 0;
        this.data = new long[100];
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long[] getData() {
        return data;
    }

    public void setData(int i, long data) {
        this.data[i] = data;
    }
}
