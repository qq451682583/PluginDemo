package com.android.grouter;

import android.util.Log;

import java.util.HashMap;

/**
 * 耗时统计
 *
 * @author holy
 * @date 2019-09-15
 */
public class GCostManager {

    public static HashMap<String, Long> mStartTimes = new HashMap<>();
    public static HashMap<String, Long> mEndTimes = new HashMap<>();

    public static void addStartTime(String key, long time) {
        mStartTimes.put(key, time);
    }

    public static void addEndTime(String key, long time) {
        mEndTimes.put(key, time);
    }

    public static void startCost(String key) {
        long start = mStartTimes.get(key);
        long end = mEndTimes.get(key);
        Log.v("耗时统计","----->>>>>> method : " + key + " cost " + (end - start) + " ms <<<<<<-----");
    }
}
