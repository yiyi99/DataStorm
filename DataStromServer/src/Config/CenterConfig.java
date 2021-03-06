/**    
 * 文件名：CenterConfig.java    
 *    
 * 版本信息：    
 * 日期：2017年6月14日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Config;

import java.util.concurrent.ConcurrentHashMap;

import Util.ServerInfo;

/**    
 *     
 * 项目名称：DataStromServer    
 * 类名称：CenterConfig    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年6月14日 下午10:46:16    
 * 修改人：jinyu    
 * 修改时间：2017年6月14日 下午10:46:16    
 * 修改备注：    
 * @version     
 *     
 */
public class CenterConfig {
    /**
     * 主注册中心
     */
 public  static  ConfigModel   masterCenter=new ConfigModel();
 
 /**
  * 所有服务中心
  */
 public static  ConcurrentHashMap<String,ConfigModel> allCenter=new  ConcurrentHashMap<String,ConfigModel>();
 
 /*
  * 本节点服务信息
  */
 public  static  ConcurrentHashMap<String,ServerInfo> localServer=new ConcurrentHashMap<String,ServerInfo>();
}
