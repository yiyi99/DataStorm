/**    
 * 文件名：AckCache.java    
 *    
 * 版本信息：    
 * 日期：2017年6月12日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package RecviceData;

import JNetSocket.UDPClient;

/**    
 *     
 * 项目名称：NetProtocol    
 * 类名称：AckCache    
 * 类描述：    缓存发送
 * 创建人：jinyu    
 * 创建时间：2017年6月12日 下午9:54:20    
 * 修改人：jinyu    
 * 修改时间：2017年6月12日 下午9:54:20    
 * 修改备注：    
 * @version     
 *     
 */
public class AckCache {
    /**
     * 对应的包
     */
public AckPackaget ack;

/**
 * 发送端口
 */
 public UDPClient client;
 
 /*
  * 发送次数（最大5次消失)
  */
 public int  ackNum=0;
 
 public String srcIP;
 public int srcPort;
 
}
