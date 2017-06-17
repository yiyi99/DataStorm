/**    
 * 文件名：RegisterServer.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package StromServer;

import com.google.common.eventbus.AllowConcurrentEvents;

import CacheDataReset.CacheOrg;
import ComModel.YWModel;
import Config.CenterConfig;
import EventBus.MessageBus;
import FactoryPackaget.NetDataPackaget;
import JNetSocket.UDPClient;
import JNetSocket.UDPServerSocket;
import ListernerServer.ListenerRequest;
import Util.DataPackaget;
import Util.FactoryPackaget;
import Util.IDataPackaget;
import Util.ServerInfo;

/**    
 *     
 * 项目名称：DataStromServer    
 * 类名称：RegisterServer    
 * 类描述：启动对服务注册
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 上午3:25:33    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 上午3:25:33    
 * 修改备注：    
 * @version     
 *     
 */
public class RegisterServer {
  
    /**
     * 注册服务信息
     */
public boolean register(ServerInfo info)
{
    //
    boolean r=startServer(info);
    //
    if(r)
    {
       UDPClient client=new UDPClient();
       FactoryPackaget f=new FactoryPackaget();
       byte[]data=f.unDataModel(info);
       client.sendData(CenterConfig.masterCenter.IP, CenterConfig.masterCenter.port, data);
       CacheOrg.put(info.serverName, client, data, CenterConfig.masterCenter.IP, CenterConfig.masterCenter.port);
       MessageBus.register(String.valueOf(info.port), this);
       //
       ListenerRequest req=new ListenerRequest();
       MessageBus.register(info.serverName, req);;
    }
    return r;
}
@AllowConcurrentEvents
public void recviceData(NetDataPackaget data)
{
   
    //服务端只响应请求包
    YWModel model=this.create(data);
    //丢给注册对应的server
    //实际丢给了ListenerRequest，但是是多个实例
    //即每个服务一份ListenerRequest对象实例
    MessageBus.post(model.data.serverName, model);
}
private YWModel create(NetDataPackaget data)
{
    YWModel  model=new YWModel();
    FactoryPackaget f=new FactoryPackaget();
    IDataPackaget packaget= f.unPackaget(data.netPackaget);
    //服务端只响应请求包
    DataPackaget req=(DataPackaget)packaget;
    model.data=req;
    model.netType=data.netType;
    model.socket=data.socket;
    model.srcIP=data.srcIP;
    model.srcPort=data.srcPort;
    model.reqCall=req.reqCall==0?false:true;
    return model;
    
}

/*
 * 启动服务监听
 */
private boolean startServer(ServerInfo info)
{
    UDPServerSocket server=new UDPServerSocket();
   boolean r=  server.InitServer(info.IP, info.port);
   return r;
}
}
