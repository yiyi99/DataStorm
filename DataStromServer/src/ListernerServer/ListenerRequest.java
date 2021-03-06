/**    
 * 文件名：ListenerServer.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package ListernerServer;

import com.google.common.eventbus.AllowConcurrentEvents;
import ComModel.YWModel;
import DataBus.CacheData;
import IServer.IProcessServer;
import NetPackaget.PackagetRandom;
import NetProtocol.judpClient;


/**    
 *     
 * 项目名称：DataStromServer    
 * 类名称：ListenerServer    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 下午1:33:12    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 下午1:33:12    
 * 修改备注：    
 * @version     
 *     
 */
public class ListenerRequest{
 
    /**
     * 缓存返回的网络信息
     * 等待返回值
     */
    CacheData<Long,CallNetModel> cache=new CacheData<Long,CallNetModel>(100000,30,false);
   @AllowConcurrentEvents
public void receviceData(YWModel data)
{
    //处理业务
     //所有查找
       if(data.reqCall)
       {
           CallNetModel model=new CallNetModel();
           model.srcIP=data.srcIP;
           model.srcPort=data.srcPort;
           model.socket=data.socket;
           model.netType=data.netType;
           model.id=PackagetRandom.getInstanceID(this);
           //hashMap.put(model.id, model);
           cache.put(model.id, model);
           data.data.sessionid=model.id;
       }
       IProcessServer cur= CurrentServer.getProcess(data.data.serverName);
       cur.recRequest(data.data);
}
  @AllowConcurrentEvents
public void reCallData(ResetCallModel recall)
{
      CallNetModel model=cache.getByKey(recall.id);
    if(model!=null)
    {
        if(model.netType!=0)
        { 
            judpClient client=new judpClient();
            client.sendData(model.srcIP, model.srcPort, recall.data);
        }
    }
}
}
    

