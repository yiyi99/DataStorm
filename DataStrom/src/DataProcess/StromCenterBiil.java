/**    
 * 文件名：StromCenterProcess.java    
 *    
 * 版本信息：    
 * 日期：2017年6月17日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DataProcess;

import DataStrom.ServerBus;
import StromModel.ServerModel;
import StromModel.StromServerNode;
import hashingAlgorithm.cirALL;


/**    
 *     
 * 项目名称：DataStrom    
 * 类名称：StromCenterProcess    
 * 类描述：    处理业务
 * 创建人：jinyu    
 * 创建时间：2017年6月17日 下午3:21:43    
 * 修改人：jinyu    
 * 修改时间：2017年6月17日 下午3:21:43    
 * 修改备注：    
 * @version     
 *     
 */
public class StromCenterBiil {
  private  cirALL<ServerModel> cir=new cirALL<ServerModel>();
    /**
     * 返回使用的服务信息
     */
public ServerModel getServerAddr(String name)
{
    StromServerNode node=ServerBus.serverList.get(name);
    ServerModel tmp=null;
    if(node==null)
    {
        return null;
    }
    if(node.master_slave)
    {
       tmp= node.getMaster();
       if(tmp==null)
       {
            tmp=node.getFirst();
            if(tmp==null)
            {
                return null;
            }
            else
            {
                node.setMaster(tmp);
            }
       }
       else
       {
           if(tmp.isAction())
           {
               return tmp;
           }
           else
           {
               tmp=node.getFirst();
               if(tmp==null)
               {
                   return null;
               }
               else
               {
                   node.setMaster(tmp);
               }
           }
       }
    }
    else
    {
         tmp=cir.GetCurNode(node.getServerList());
         if(!tmp.isAction())
         {
             node.remove(tmp);
             getServerAddr(name);
         }
       
    }
    
    return tmp;
    
}
public void DataToServer(byte[]data,boolean isCall)
{
    
}
}
