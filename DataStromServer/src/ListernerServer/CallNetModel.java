/**    
 * 文件名：CallNetModel.java    
 *    
 * 版本信息：    
 * 日期：2017年6月12日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package ListernerServer;

/**    
 *     
 * 项目名称：DataStromServer    
 * 类名称：CallNetModel    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年6月12日 上午3:21:51    
 * 修改人：jinyu    
 * 修改时间：2017年6月12日 上午3:21:51    
 * 修改备注：    
 * @version     
 *     
 */
public class CallNetModel {
    /*
     * 来源Ip
     */
  public String srcIP;
  /*
  * 来源端口
  */
  public int srcPort;

  /**
   * 本地IP
   */
  public String localIP;

  /*
   * 本地IP
   */
  public int localPort;
  /*
  * 通讯协议类型 0 tcp  1 udp  2 组播 3 广播
  */
  public int  netType;
  
  public long  id;

  /*
  * 需要socket通讯时保持
  * 一般直接保持服务端的socket或者tcp客户端
  */
  public Object socket;//
  
}
