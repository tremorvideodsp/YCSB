package com.yahoo.ycsb.workloads;

import com.yahoo.ycsb.WorkloadException;

import java.util.Properties;
import java.util.Random;

public class FixedSizeKeyWorkload extends CoreWorkload {

  private Random rnd = new XorShiftRandom();

  private int keylength;

  public static final String KEY_LENGTH_PROPERTY = "keylength";
  public static final long KEY_LENGTH_PROPERTY_DEFAULT = 32;

  private char[] chars;

  private char[] subset = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
  private int subsetSize = subset.length;

  @Override
  public void init(Properties p) throws WorkloadException {
    keylength = Integer.parseInt(p.getProperty(KEY_LENGTH_PROPERTY, String.valueOf(KEY_LENGTH_PROPERTY_DEFAULT)));

    chars = new char[keylength];

    super.init(p);
  }

  @Override
  protected String buildKeyName(long keynum) {
    rnd.setSeed(keynum);
    int size = keylength;
    for (int i = 0; i < size; i++) {
        int index = rnd.nextInt(subsetSize);
        chars[i] = subset[index];
    }
    return new String(chars, 0, size);
  }
}
