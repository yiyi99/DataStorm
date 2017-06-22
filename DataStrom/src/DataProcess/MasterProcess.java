/**    
 * 文件名：MasterProcess.java    
 *    
 * 版本信息：    
 * 日期：2017年6月15日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DataProcess;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

import Config.CenterConfig;
import DataStrom.ServerBus;
import Model.MasterModel;
import NetModel.NetAddress;
import NetProtocol.judpClient;
import RecServer.CenterTimer;
import RecServer.RandomFlage;
import StromModel.ConfigModel;
import Util.FactoryPackaget;


/**    
 *     
 * 项目名称：DataStrom    
 * 类名称：MasterProcess    
 * 类描述：   处理主注册中心信息 
 * 创建人：jinyu    
 * 创建时间：2017年6月15日 下午10:38:55    
 * 修改人：jinyu    
 * 修改时间：2017年6月15日 下午10:38:55    
 * 修改备注：    
 * @version     
 *     
 */
public class MasterProcess {
    @Subscribe
    @AllowConcurrentEvents
    public void recMasterAsk(MasterModel req)
    {
        //
        if(req.centerByte==5)
        {
            //有中心通知重新产生标识
            if(!req.IP.equals(CenterConfig.localCenter.IP)||req.port!=CenterConfig.localCenter.port||!CenterConfig.localCenter.flage.equals(req.flage))
            {
                //说明不是通知自己的
                return;
            }
            CenterConfig.localCenter.intflage=RandomFlage.getFlage();
            CenterConfig.localCenter.flage=String.valueOf(CenterConfig.localCenter.intflage);
            return;
        }
        ConfigModel model=new ConfigModel();
        model.action=req.action;
        model.centerByte=req.centerByte;
        model.flage=req.flage;
        model.intflage=Integer.valueOf(req.flage);
        model.IP=req.IP;
        model.port=req.port;
        model.multIP=req.multIP;
        model.multPort=req.multPort;
        if(model.centerByte==4||model.centerByte==2)
        {
            //如果是探寻包；则比较查看是否自己是master;
            //当前如果是自己的选举或者探寻包，则已经取出
           //自己的选举则不加入
            if(CenterConfig.localCenter.centerByte==1)
            {
                //立即发送心跳包；
                CenterTimer.addMaster(CenterConfig.localCenter);
            }
            else
            {
                if(CenterConfig.localCenter.centerByte==2&&model.centerByte==2)
                {
                    //同时进入选举流程
                    if(model.intflage>CenterConfig.localCenter.intflage)
                    {
                        //
                        CenterTimer.addMaster(model);
                    }
                }
            }
        }
        else
        {
            //心跳包，所有心跳则加入
          //  if(model.equals(CenterConfig.localCenter))
                CenterTimer.addMaster(model);
        }
        //判断下注册中心标识
        if(model.intflage==CenterConfig.localCenter.intflage&&!CenterConfig.localCenter.IP.equals(model.IP))
        {
            //标识相同，IP不同说明需要重新选择；
            NetAddress netcall=ServerBus.objSocket.getByKey(String.valueOf(req.sessionid));
            judpClient client=new judpClient();
            MasterModel  ask=new MasterModel();
            ask.IP=CenterConfig.localCenter.IP;
            ask.port=CenterConfig.localCenter.port;
            FactoryPackaget f=new FactoryPackaget();
            byte[] data=  f.unDataModel(ask);
            client.sendData(netcall.srcIP, netcall.srcPort, data);
        }
    }
}
