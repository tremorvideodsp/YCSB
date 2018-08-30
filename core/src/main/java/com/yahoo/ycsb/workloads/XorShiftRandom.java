package com.yahoo.ycsb.workloads;

import java.util.Random;

public class XorShiftRandom extends Random {

  private long seed;

  @Override
  public synchronized void setSeed(long seed) {
    this.seed = seed;
  }

  protected int next(int nbits) {
    long x = seed;
    x ^= (x << 21);
    x ^= (x >>> 35);
    x ^= (x << 4);
    seed = x;
    x &= ((1L << nbits) - 1);
    return (int) x;
  }
}
