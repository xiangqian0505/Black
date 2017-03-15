package com.tyiti.test;


import java.util.ArrayList;
import java.util.HashMap;

import cacps.msg.security.FrontStdMessageCli;
import cacps.msg.security.FrontStdMsgHead;

public class Test {
	
	public String path = "D:\\dsf\\frontcli.xml";//该路径为xml文件的绝对路径，我这里是我本地的测试路径，企业使用时需要根据自己环境来修改
	public static final String CORP_NO = "16100138"; //企业代码
	public static final String PROD_CODE = "00100"; //代收费项代码
	public static final String ACCT_NO = "01520120210020343"; //企业账户
	public static final String BANK_NO = "313301008524"; //企业开户行
	public static final String CHANNEL_CODE = "1053"; //渠道代码
	
	/**
	 * 封装单笔实时代收代付交易
	 * @throws Exception 
	 * 说明：1、因为单笔实时代收付请求长连接和短连接方式请求报文内容是一样的，所以在封装交易码的时候要注意一下
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public byte[] tranRealReq() throws Exception{
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.getBody().put("batch_no", "tyxxkjgfyxgs");//报文标识号
		fsm.getBody().put("corp_no", CORP_NO);//企业代码
		fsm.getBody().put("prod_code", PROD_CODE);//代收费项代码
		fsm.getBody().put("busi_type", "1");//业务类型
		fsm.getBody().put("acct_no", ACCT_NO);//企业账户
		fsm.getBody().put("bank_no", BANK_NO);//企业开户行
//		fsm.getBody().put("proto_code", "");//代收协议号
		fsm.getBody().put("opp_acct_no", "111111");//对方账户
		fsm.getBody().put("opp_acct_name", "222222");//对方户名
		fsm.getBody().put("opp_bank_no", "333333");//对方开户行
		fsm.getBody().put("opp_bank_name", "444444");//对方开户行名称
		fsm.getBody().put("currency", "CNY");//币种
		fsm.getBody().put("money", "100.12");//金额
		fsm.getBody().put("postscript", "附言");//附言
		fsm.getBody().put("note", "备注");//备注
		
		fsm.getHead().setChannelCode(CHANNEL_CODE);//渠道代码
		fsm.getHead().setTrancode("Z0103");//交易码，注意，如果是长连接就填写Z0103，如果是短连接就填写Z0101
		fsm.getHead().setMsgtype((byte)FrontStdMsgHead.MSG_TYPE_REQ);//请求类型
		
		return fsm.packMsg();
	}
	
	/**
	 * 解析单笔实时代收代付交易响应(短连接Z0102的响应)
	 * @throws Exception 
	 * 说明：1、我只是把响应报文中的报体内容都解开了，至于客户端怎么去用那就是企业开发的事情了。
	 */
	public void tranRealZ0102Resp(byte[] respmsg) throws Exception{
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.parseMsg(respmsg);
		HashMap<String, Object> msgmap = fsm.getBody();
		String batch_no = (String)msgmap.get("batch_no");//交易标识码
		String seq_no = (String)msgmap.get("seq_no");//渠道批次号
		String state_code = (String)msgmap.get("state_code");//受理状态码
		String fail_desc = (String)msgmap.get("fail_desc");//受理失败描述
		String busi_date = (String)msgmap.get("busi_date");//业务日期
		String postscript = (String)msgmap.get("postscript");//附言
		String note = (String)msgmap.get("note");//备注
	}
	
	/**
	 * 解析单笔实时代付代付交易响应(长连接Z0104的响应)
	 * @throws Exception
	 */
	public void tranRealZ0104Resp(byte[] respmsg) throws Exception{
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.parseMsg(respmsg);
		HashMap<String, Object> msgmap = fsm.getBody();
		System.out.println(msgmap.size()+"===");
		String batch_no = (String)msgmap.get("batch_no");//交易标识码
		String seq_no = (String)msgmap.get("seq_no");//渠道批次号
		String state_code = (String)msgmap.get("state_code");//受理状态码
		String fail_desc = (String)msgmap.get("fail_desc");//受理失败描述
		String accept_date = (String)msgmap.get("accept_date");//受理时间
		String busi_date = (String)msgmap.get("busi_date");//业务日期
		String tran_state = (String)msgmap.get("tran_state");//交易状态码
		String ret_code = (String)msgmap.get("ret_code");//返回码
		String ret_desc = (String)msgmap.get("ret_desc");//返回描述
		String ret_pkg_no = (String)msgmap.get("ret_pkg_no");//回执报文标识号
		String postscript = (String)msgmap.get("postscript");//附言
		String note = (String)msgmap.get("note");//备注
	}
	
	/**
	 * 封装批量代收代付交易请求
	 * 说明：1、因为批量代收代付交易请求长连接和短连接方式请求报文内容是一样的，所以在封装交易码的时候要注意一下
	 * @throws Exception 
	 */
	public byte[] tranBtachReq() throws Exception{
		int load_num=0;//要封装交易明细总笔数，这个是根据企业要封装的交易个数来定。
		
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.getBody().put("batch_no", "这里面的是实际值的字符串");//报文标识号
		fsm.getBody().put("corp_no", "这里面的是实际值的字符串");//企业代码
		fsm.getBody().put("prod_code", "这里面的是实际值的字符串");//代收费项代码
		fsm.getBody().put("busi_type", "这里面的是实际值的字符串");//业务类型
		fsm.getBody().put("tran_type", "这里面的是实际值的字符串");//交易类型
		fsm.getBody().put("acct_no", "这里面的是实际值的字符串");//企业账户
		fsm.getBody().put("bank_no", "这里面的是实际值的字符串");//企业开户行
		fsm.getBody().put("load_amt", "这里面的是实际值的字符串");//总金额
		fsm.getBody().put("load_num", "这里面的是实际值的字符串");//总笔数
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> itmes = null;
		for(int i=0;i<load_num;i++){
			itmes = new HashMap<String, String>();
			itmes.put("seq_num", "这里面的是实际值的字符串");//明细序号
			itmes.put("crop_seq", "这里面的是实际值的字符串");//企业流水号
			itmes.put("opp_acct_no", "这里面的是实际值的字符串");//对方账户
			itmes.put("opp_acct_name", "这里面的是实际值的字符串");//对方户名
			itmes.put("opp_bank_no", "这里面的是实际值的字符串");//对方开户行
			itmes.put("opp_bank_name", "这里面的是实际值的字符串");//对方开户行名称
			itmes.put("proto_code", "这里面的是实际值的字符串");//代收协议号
			itmes.put("currency", "这里面的是实际值的字符串");//币种
			itmes.put("money", "这里面的是实际值的字符串");//金额
			itmes.put("postscript", "这里面的是实际值的字符串");//附言
			list.add(itmes);
		}
		fsm.getBody().put("ret_restri", "这里面的是实际值的字符串");//回执期限
		fsm.getBody().put("note", "这里面的是实际值的字符串");//备注
		fsm.getBody().put("load_num_items", list);//将循环迭代的list，put到报文体内
		
		
		fsm.getHead().setChannelCode("这里面的是代收付中心给分配的渠道代码");//渠道代码
		fsm.getHead().setTrancode("");//交易码，注意，如果是长连接就填写Z0107，如果是短连接就填写Z0105
		fsm.getHead().setMsgtype((byte)FrontStdMsgHead.MSG_TYPE_REQ);//请求类型
		return fsm.packMsg();
	}
	
	/**
	 * 解析批量代收代付交易响应（短连接Z0106响应）
	 * @throws Exception 
	 */
	public void tranBtachZ0106Resp(byte[] respmsg) throws Exception{
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.parseMsg(respmsg);
		HashMap<String, Object> msgmap = fsm.getBody();
		String batch_no = (String)msgmap.get("batch_no");//交易标识码
		String seq_no = (String)msgmap.get("seq_no");//渠道批次号
		String state_code = (String)msgmap.get("state_code");//受理状态码
		String fail_desc = (String)msgmap.get("fail_desc");//受理失败描述
		String busi_date = (String)msgmap.get("busi_date");//业务日期
		int fail_num = Integer.parseInt((String)msgmap.get("fail_num"));//受理失败总笔数
		ArrayList<HashMap<String, String>> list =  (ArrayList<HashMap<String, String>>)msgmap.get("fail_num_items");
		HashMap<String, String> items = null;
		for(int i=0;i<list.size();i++){
			items = list.get(i);
			String seq_num = (String)items.get("seq_num");//明细序号
			String crop_seq = (String)items.get("crop_seq");//企业明细流水号
			String acct_no = (String)items.get("acct_no");//对方账户
			String acct_name = (String)items.get("acct_name");//对方户名
			String fail_reason = (String)items.get("fail_reason");//失败原因
			String postscript = (String)items.get("postscript");//附言
			String note = (String)items.get("note");//备注
		}
	}
	
	/**
	 * 解析批量代收代付交易响应（长连接Z0108响应）
	 * @throws Exception 
	 */
	public void tranBtachZ0108Resp(byte[] respmsg) throws Exception{
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.parseMsg(respmsg);
		HashMap<String, Object> msgmap = fsm.getBody();
		String batch_no = (String)msgmap.get("batch_no");//交易标识码
		String seq_no = (String)msgmap.get("seq_no");//渠道批次号
		String state_code = (String)msgmap.get("state_code");//受理状态码
		String fail_desc = (String)msgmap.get("fail_desc");//受理失败描述
		String accept_date = (String)msgmap.get("accept_date");//受理时间
		String busi_date = (String)msgmap.get("busi_date");//业务日期
		int success_num = Integer.parseInt((String)msgmap.get("success_num"));//交易成功笔数
		double success_amt = Double.parseDouble((String)msgmap.get("success_amt"));//交易成功金额
		int fail_num = Integer.parseInt((String)msgmap.get("fail_num"));//交易失败笔数
		double fail_amt = Double.parseDouble((String)msgmap.get("fail_amt"));//交易失败金额
		double total_amt = Double.parseDouble((String)msgmap.get("total_amt"));//总金额
		int total_num = Integer.parseInt((String)msgmap.get("total_num"));//总笔数
		ArrayList<HashMap<String, String>> list =  (ArrayList<HashMap<String, String>>)msgmap.get("total_num_items");
		HashMap<String, String> items = null;
		for(int i=0;i<list.size();i++){
			items = list.get(i);
			String seq_num = (String)items.get("detail_num");//明细序号
			String crop_seq = (String)items.get("crop_seq");//企业明细流水号
			String tran_state = (String)items.get("tran_state");//交易状态码
			String ret_code = (String)msgmap.get("ret_code");//返回码
			String ret_desc = (String)msgmap.get("ret_desc");//返回描述
			String postscript = (String)msgmap.get("postscript");//附言
			String note = (String)msgmap.get("note");//备注
		}
	}
	
	/**
	 * 封装代收付交易查询
	 * @throws Exception 
	 * @return 返回的是封装完的数据，把这个数据直接通过socket发给服务端就可以了
	 * 说明：1、【这里面的是实际值的字符串】这是客户端实际要封装的报体内容字符串。
	 * 2、fsm.getBody().put方法中的key值是和frontcli.xml文件中的<msgname>节点中的值一致的。
	 */
	public byte[] tranQryReq() throws Exception{
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.getBody().put("batch_no","这里面的是实际值的字符串");//交易标识码
		fsm.getBody().put("corp_code","这里面的是实际值的字符串");//企业代码
		fsm.getBody().put("ori_batch_no","这里面的是实际值的字符串");//原交易标识码
		fsm.getBody().put("seq_no","这里面的是实际值的字符串");//渠道批次号
		
		fsm.getHead().setChannelCode("这里面的是代收付中心给分配的渠道代码");//渠道代码
		fsm.getHead().setTrancode("Z0303");//交易码
		fsm.getHead().setMsgtype((byte)FrontStdMsgHead.MSG_TYPE_REQ);//请求类型
		return fsm.packMsg();
	}
	
	/**
	 * 解析代收付交易响应
	 * @param respmsg 通讯层接收到的数据
	 * @throws Exception 
	 * 说明：1、我只是把响应报文中的报体内容都解开了，至于客户端怎么去用那就是企业开发的事情了。
	 * 2、还用应该注意一点就是里面msgmap.get的时候还应该做非空判断，要不强转可能会造成异常。
	 */
	public void tranQryResp(byte[] respmsg) throws Exception{
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.parseMsg(respmsg);
		HashMap<String, Object> msgmap = fsm.getBody();
		String batch_no = (String)msgmap.get("batch_no");//交易标识码
		String accept_state = (String)msgmap.get("accept_state");//受理状态码
		String fail_reason = (String)msgmap.get("fail_reason");//查询失败原因
		String disp_state = (String)msgmap.get("disp_state");//处理状态码
		String ori_batch_no = (String)msgmap.get("ori_batch_no");//原交易标识码
		String seq_no = (String)msgmap.get("seq_no");//渠道批次号
		String busi_type = (String)msgmap.get("busi_type");//业务类型
		String tran_type = (String)msgmap.get("tran_type");//交易类型
		String prod_code = (String)msgmap.get("prod_code");//费项代码
		String acct_no = (String)msgmap.get("acct_no");//企业账号
		String bank_no = (String)msgmap.get("bank_no");//企业开户行行号
		String accept_date = (String)msgmap.get("accept_date");//受理时间
		String busi_date = (String)msgmap.get("busi_date");//业务日期
		String success_num = (String)msgmap.get("success_num");//交易成功笔数
		double success_amt = Double.parseDouble((String)msgmap.get("success_amt"));//交易成功金额
		String fail_num = (String)msgmap.get("fail_num");//交易失败笔数
		double fail_amt = Double.parseDouble((String)msgmap.get("fail_amt"));//交易失败金额
		double total_amt = Double.parseDouble((String)msgmap.get("total_amt"));//总金额
		String total_num = (String)msgmap.get("total_num");//总笔数
		//以上就是packtype为1的部分，其中packtype为2的“total_num”解析也是和packtype为1的一样。
		ArrayList<HashMap<String, String>> list2 =  (ArrayList<HashMap<String, String>>)msgmap.get("total_num_items");//规则为packtype为2的别名+“_items”,"total_num_items"
		HashMap<String, String> item2 = null;
		for(int i=0;i<list2.size();i++){
			item2 = list2.get(i);
			String detail_num = item2.get("detail_num");//明细序号
			String crop_seq = item2.get("crop_seq");//企业流水号
			String opp_acct_no = item2.get("opp_acct_no");//对方账号
			String opp_acct_name = item2.get("opp_acct_name");//对方户名
			String currency = item2.get("currency");//交易币种
			double money = Double.parseDouble((String)item2.get("money"));//交易金额
			String tran_state = item2.get("tran_state");//交易状态码
			String tran_fail_desc = item2.get("tran_fail_desc");//交易失败描述
			String ret_code = item2.get("ret_code");//返回码
			String ret_desc = item2.get("ret_desc");//返回描述
			String postscript = item2.get("postscript");//附言
		}
		String note= (String)msgmap.get("note");//备注
	}
	
	
	/**
	 * 封装代收账户协议批量上传交易请求
	 * @throws Exception 
	 */
	public byte[] tranProtReq() throws Exception{
		int proto_num = 1;//要封装的协议总个数，企业根据实际情况来封装
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.getBody().put("batch_no", String.valueOf(System.currentTimeMillis()));//报文标识号
		fsm.getBody().put("corp_no", CORP_NO);//企业代码
		fsm.getBody().put("oper_type", "1");//操作类型
		fsm.getBody().put("prod_code", PROD_CODE);//费项代码
		fsm.getBody().put("proto_num", "1");//协议总条数
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> itmes = new HashMap<String, String>();
		for(int i=0;i<proto_num;i++){
			itmes.put("proto_code", "5699");//协议号
			itmes.put("acct_no", "01520120210020343");//协议账户
			itmes.put("acct_name", "2108565608");//协议户名
			itmes.put("bank_no", "313301008524");//协议账户开户行
			itmes.put("mobile", "13511111111");//手机号
			itmes.put("bdate", "2016-12-12");//协议有效起始日期
			itmes.put("edate", "2017-12-12");//协议有效终止日期
			itmes.put("limit", "10000.00");//协议单笔限额
			list.add(itmes);
		}
		fsm.getBody().put("note", "测试");//附言
		fsm.getBody().put("proto_num_items", list);
		
		fsm.getHead().setChannelCode(CHANNEL_CODE);//渠道代码
		fsm.getHead().setTrancode("Z0601");//交易码
		fsm.getHead().setMsgtype((byte)FrontStdMsgHead.MSG_TYPE_REQ);//请求类型
		fsm.getHead().setBodyLength(fsm.getBody().toString().length());
		return fsm.packMsg();
	}
	
	/**
	 * 解析代收账户协议批量上传交易响应
	 * @throws Exception 
	 */
	public void tranProtResp(byte[] respmsg) throws Exception{
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.parseMsg(respmsg);
		HashMap<String, Object> msgmap = fsm.getBody();
		String batch_no = (String)msgmap.get("batch_no");//交易标识码
		String accept_state = (String)msgmap.get("accept_state");//受理状态码
		String fail_desc = (String)msgmap.get("fail_desc");//受理失败描述
		int fail_num = Integer.parseInt((String)msgmap.get("fail_num"));//操作失败条数
		ArrayList<HashMap<String, String>> list =  (ArrayList<HashMap<String, String>>)msgmap.get("fail_num_items");
		HashMap<String, String> items = null;
		for(int i=0;i<list.size();i++){
			items = list.get(i);
			String proto_code = (String)items.get("proto_code");//协议号
			String proto_process = (String)items.get("proto_process");//协议处理码
			String process_desc = (String)items.get("process_desc");//处理失败原因
		}
		String note = (String)msgmap.get("note");//受理失败描述
	}
	
	/**
	 * 封装代收账户协议查询交易请求
	 * @throws Exception 
	 */
	public byte[] tranProtQryReq() throws Exception{
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.getBody().put("batch_no", "这里面的是实际值的字符串");//报文标识号
		fsm.getBody().put("corp_no", "这里面的是实际值的字符串");//企业代码
		fsm.getBody().put("proto_code", "这里面的是实际值的字符串");//协议号
		fsm.getBody().put("prod_code", "这里面的是实际值的字符串");//费项代码
		fsm.getBody().put("proto_acct_no", "这里面的是实际值的字符串");//协议账号
		
		fsm.getHead().setChannelCode("这里面的是代收付中心给分配的渠道代码");//渠道代码
		fsm.getHead().setTrancode("Z0301");//交易码
		fsm.getHead().setMsgtype((byte)FrontStdMsgHead.MSG_TYPE_REQ);//请求类型
		return fsm.packMsg();
	}
	
	/**
	 * 解析代收账户协议查询交易响应
	 * @throws Exception 
	 */
	public void tranProtQryResp(byte[] respmsg) throws Exception{
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.parseMsg(respmsg);
		HashMap<String, Object> msgmap = fsm.getBody();
		
		String batch_no = (String)msgmap.get("batch_no");//交易标识码
		String accept_state = (String)msgmap.get("accept_state");//受理状态码
		String fail_desc = (String)msgmap.get("fail_desc");//受理失败描述
		int proto_num = Integer.parseInt((String)msgmap.get("proto_num"));//协议总条数
		ArrayList<HashMap<String, String>> list =  (ArrayList<HashMap<String, String>>)msgmap.get("proto_num_items");
		HashMap<String, String> items = null;
		for(int i=0;i<list.size();i++){
			items = list.get(i);
			String prod_code = (String)items.get("prod_code");//费项代码
			String proto_code = (String)items.get("proto_code");//协议号
			String acct_no = (String)items.get("acct_no");//协议账户
			String acct_name = (String)items.get("acct_name");//协议户名
			String bank_no = (String)items.get("bank_no");//协议账户开户行
			String mobile = (String)items.get("mobile");//手机号
			String bdate = (String)items.get("bdate");//协议有效起始日期
			String edate = (String)items.get("edate");//协议有效终止日期
			String limit = (String)items.get("limit");//协议单笔限额
			String update = (String)items.get("update");//更新时间
			String status = (String)items.get("status");//协议状态
		}
	}
	
	/**
	 * 心跳报文交易请求
	 */
	public byte[] tranHeartBeatMsg() throws Exception{
		FrontStdMessageCli fsm = new FrontStdMessageCli(this.path);
		fsm.getHead().setVersion("10");
		fsm.getHead().setBodytype((byte) 0);
		fsm.getHead().setTrancode("00000");
		fsm.getHead().setErrcode("00000");
		fsm.getHead().setBodytype((byte)48);
		fsm.getHead().setChannelCode(CHANNEL_CODE);
		fsm.getHead().setMsgid(String.valueOf(System.currentTimeMillis()));
		fsm.getHead().setBodyLength(0);
		
		return fsm.packMsg();
	}

}
