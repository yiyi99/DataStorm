/**    
 * 文件名：ProcessRegister.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package StromRegister;



import IServer.IProcessServer;
import ListernerServer.CurrentServer;
import ListernerServer.ServerTimer;

/**    
 *     
 * 项目名称：DataStromServer    
 * 类名称：ProcessRegister    
 * 类描述:服务业务处理,添加服务对应的处理类
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 下午5:12:35    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 下午5:12:35    
 * 修改备注：    
 * @version     
 *     
 */
public class ProcessRegister {
  private static volatile boolean isStart=true;
  
  /**
   * 注册服务处理
   */
public  static void addServer(String name,IProcessServer process)
{
    CurrentServer.addServer(name, process);
    if(isStart)
    {
        isStart=false;
        ServerTimer.startThread();
    }
}

/*
 * 移除服务处理
 */
public static void removeServer(String name)
{
    CurrentServer.removeServer(name);
}

}
