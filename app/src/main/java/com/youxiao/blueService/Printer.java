package com.youxiao.blueService;

public class Printer {
	private static final String TAG = "BTPrinter";
	private static final boolean D = true;

	// 蓝牙服务处理程序发送的消息类型
	public static final int MESSAGE_STATE_CHANGE = 1;// 改变消息状态
	public static final int MESSAGE_READ = 2;//读取消息
	public static final int MESSAGE_WRITE = 3;// 写消息
	public static final int MESSAGE_DEVICE_NAME = 4;// 信息设备名称
	public static final int MESSAGE_TOAST = 5;// 消息吐司

	// 接收到的蓝牙服务处理程序的按键名称
	public static final String DEVICE_NAME = "device_name";// 设备名称
	public static final String TOAST = "toast";

	// 意向请求代码
	private static final int REQUEST_CONNECT_DEVICE = 1;// 请求连接装置
	private static final int REQUEST_ENABLE_BT = 2;// 请求启用蓝牙(BT)

}
