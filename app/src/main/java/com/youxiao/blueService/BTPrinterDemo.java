package com.youxiao.blueService;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 这是主要的活动，显示当前的会话
 *
 * Created by 张小布 on 2016/5/11.
 */
public class BTPrinterDemo {

    // 调试

    public static String sDate;
    // 意向请求代码

    // 布局视图
    public static String CUST_ID, VAN_ID, JOB_NO, DISTR_ID, DISTR_NAME,
            VAN_NAME, NAME, CUST_NAME,CUST_ADDR,TEL_NO,DISPLAY_MONTH,CUST_NO;
    // 本地蓝牙适配器
    public static BluetoothAdapter mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
    // 蓝牙服务
    public static BluetoothService mService=null;
//    static Common common;
    static Context mcontext;
//    private static LargessService largessService;
    static SharedPreferences printCollectCountPreferences;

    public BTPrinterDemo(BluetoothService mService) {
        // TODO Auto-generated constructor stub
//		if(DeviceListActivity.mService!=null)
//		{
//			mService=DeviceListActivity.mService;
//		}
        this.mService=mService;

    }

    /**
     * 发送一条消息
     *
     * @param message
     *            发送一条消息
     */
    private static void sendMessage(String message) {// 发送消息
        // 检查 我们在尝试连接 蓝牙 服务
        if (mService.getState() != BluetoothService.STATE_CONNECTED) {
//			 Toast.makeText(, R.string.not_connected,
//			 Toast.LENGTH_SHORT).show();
//			SaleActivity.m_Handler.sendEmptyMessage(6);
            return;
        }
        // 检查有实际的东西送
        if (message.length() > 0) {
            // 获得消息的字节，并告诉写入的蓝牙服务
            byte[] send;
            try {
                send = message.getBytes("GBK");
            } catch (Exception e) {
                send = message.getBytes();
                // 去除/n;
                int length = send.length;
                for (int i = 0; i < length; i++) {
                    if (send[i] == 10) {
                        for (int j = i; j < length - 1; j++) {
                            send[j] = send[j + 1];
                        }
                    }
                }
            }
            mService.write(send);
        }
    }


    private static StringBuffer setDistance(String DISTR_NAME, StringBuffer printHead) {
        // TODO Auto-generated method stub
        if (DISTR_NAME.length() < 22) {
            int len = 24 - DISTR_NAME.length();
            for (int i = 0; i < len / 2; i++) {
                printHead .append("  ");
            }
            printHead.append(DISTR_NAME);
            for (int i = 0; i < len / 2; i++) {
                printHead.append("  ");
            }
            printHead.append("\n\r");
        } else {
            printHead.append(DISTR_NAME.substring(0, 21) + "\n\r");
            int len = 24 - DISTR_NAME.substring(0, 21).length();
            for (int i = 0; i < len / 2; i++) {
                printHead.append(" ");
            }
            printHead.append(DISTR_NAME.substring(22) + "\n\r");
        }
        return printHead;
    }
    /**
     * 发送一条消息
     *
     * @param message
     *            发送一条消息
     */
    private static void sendMessages(String message) {// 发送消息
        // 检查 我们在尝试连接 蓝牙 服务
        if (mService.getState() != BluetoothService.STATE_CONNECTED) {
//            Qhspcx.m_Handler.sendEmptyMessage(6);
            return;
        }
        // 检查有实际的东西送
        if (message.length() > 0) {
            // 获得消息的字节，并告诉写入的蓝牙服务
            byte[] send;
            try {
                send = message.getBytes("GBK");
            } catch (Exception e) {
                send = message.getBytes();
                // 去除/n;
                int length = send.length;
                for (int i = 0; i < length; i++) {
                    if (send[i] == 10) {
                        for (int j = i; j < length - 1; j++) {
                            send[j] = send[j + 1];
                        }
                    }
                }
            }
            mService.write(send);
        }
    }

}
