/**    
 * 文件名：RspProcess.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DataProcess;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

import DataStrom.ServerBus;
import NetModel.NetAddress;
import NetProtocol.judpClient;
import Util.FactoryPackaget;
import Util.RspPackaget;

/**    
 *     
 * 项目名称：DataStrom    
 * 类名称：RspProcess    
 * 类描述：   注册中心接收到回执返回 
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 上午3:07:41    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 上午3:07:41    
 * 修改备注：    
 * @version     
 *     
 */
public class RspProcess {
    FactoryPackaget f=new FactoryPackaget();
    @AllowConcurrentEvents
    @Subscribe
public void recRsponse(RspPackaget rsp)
{
    //收到服务回执
    //转给客户端
     NetAddress netcall=     ServerBus.objSocket.getByKey(String.valueOf(rsp.sessionid));
     if(netcall!=null)
     {
       byte[]data=f.unDataModel(rsp);
        judpClient client=new judpClient();
       client.sendData(netcall.srcIP, netcall.srcPort, data);
     }
}
}
