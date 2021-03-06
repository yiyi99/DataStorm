/**    
 * 文件名：CurrentServer.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package ListernerServer;

import java.util.concurrent.ConcurrentHashMap;

import IServer.IProcessServer;

/**    
 *     
 * 项目名称：DataStromServer    
 * 类名称：CurrentServer    
 * 类描述：  服务注册信息
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 下午6:10:49    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 下午6:10:49    
 * 修改备注：    
 * @version     
 *     
 */
public class CurrentServer {
    static  ConcurrentHashMap<String,IProcessServer> hash=new ConcurrentHashMap<String,IProcessServer>();
    public  static void addServer(String name,IProcessServer process)
    {
        hash.put(name, process);
    }
    public static void removeServer(String name)
    {
        hash.remove(name);
    }
    public static IProcessServer getProcess(String name)
    {
   return     hash.getOrDefault(name, null);
       
    }
}
