package com.android.grouter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
/**
 * 管理路由表
 *
 * @author holy
 * @date 2019-09-15
 */
public class GRouterManager {

    private static class Holder {
        private static GRouterManager INSTANCE = new GRouterManager();
    }

    public static GRouterManager getInstance() {
        return Holder.INSTANCE;
    }

    private HashMap<String, String> mRouters = new HashMap<>();

    /**
     * 初始化路由
     */
    public void initRouter() {
        try {
            Class clazz = Class.forName("com.android.grouter.GRouterManager");
            Object newInstance = clazz.newInstance();
            Field field = clazz.getField("map");
            field.setAccessible(true);
            HashMap<String, String> temps = (HashMap<String, String>) field.get(newInstance);
            if (temps != null && !temps.isEmpty()) {
                mRouters.putAll(temps);
                Log.w("holy", "=== mRouters.Size === " + mRouters.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据协议找寻路由实现跳转
     */
    public void startIntent(Context context, String protocol, Bundle bundle) {
        if (TextUtils.isEmpty(protocol)) {
            return;
        }
        String protocolValue = mRouters.get(protocol);
        try {
            Class destClass = Class.forName(protocolValue);
            Intent intent = new Intent(context, destClass);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
