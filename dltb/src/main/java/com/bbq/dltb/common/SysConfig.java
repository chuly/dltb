package com.bbq.dltb.common;

import java.math.BigDecimal;

public class SysConfig {
	
	/** 付款最长等待时间(分钟) */
	public static int max_wait_pay_minutes = 30;
	/** 单次最大购买数 */
	public static int product_max_buy_count = 30;
	/** 能存在的最多未付款订单数 */
	public static int max_no_pay_order_count = 5;
	/** 人民币兑美元汇率 */
	public static BigDecimal rmb_doller_rate = null;
	/** 参与人数系数a（公式：x*a+b） */
	public static int up_scale_a = 1;
	/** 参与人数系数b（公式：x*a+b） */
	public static int up_scale_b = 0;
	
}
