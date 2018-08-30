package com.yahoo.ycsb.workloads;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.yahoo.ycsb.Client;
import com.yahoo.ycsb.measurements.Measurements;
import org.testng.annotations.Test;

import java.util.Properties;

/*

 */
public class TestFixedSizeKeyWordload {

  @Test
  public void buildKeyName() throws Exception {

    int keySize = 32;

    long seed1 = 1000;
    long seed2 = 1001;

    Properties p = getUTProperties(keySize);
    Measurements.setProperties(p);

    FixedSizeKeyWorkload wl = new FixedSizeKeyWorkload();

    wl.init(p);

    String key1 = wl.buildKeyName(seed1);
    String key2 = wl.buildKeyName(seed1);
    String key3 = wl.buildKeyName(seed2);

    assertEquals(key1.length(), keySize);
    assertEquals(key2.length(), keySize);
    assertEquals(key3.length(), keySize);
    assertEquals(key1, key2);
    assertNotEquals(key1, key3);
  }

  private Properties getUTProperties(int keySize) {
    final Properties p = new Properties();
    p.put(Measurements.MEASUREMENT_TYPE_PROPERTY, "hdrhistogram");
    p.put(FixedSizeKeyWorkload.KEY_LENGTH_PROPERTY, keySize);
    return p;
  }
}
