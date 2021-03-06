/**    
 * 文件名：StromServerNode.java    
 *    
 * 版本信息：    
 * 日期：2017年6月17日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package StromModel;


import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**    
 *     
 * 项目名称：DataStrom    
 * 类名称：StromServerNode    
 * 类描述：    服务信息，用于服务使用
 * 创建人：jinyu    
 * 创建时间：2017年6月17日 下午3:28:06    
 * 修改人：jinyu    
 * 修改时间：2017年6月17日 下午3:28:06    
 * 修改备注：    
 * @version     
 *     
 */
public class StromServerNode {
    public StromServerNode()
    {
        serverList=new CopyOnWriteArraySet<ServerModel>();
    }
  /**
   * 服务名称
   */
public String  serverName;
/**
 * 是否是主从复制服务
 */
 public boolean master_slave=false;
 
/**
 * 主从复制服务时的master
 */
 private  ServerModel master=null;
 //CopyOnWriteArrayList
 CopyOnWriteArraySet<ServerModel> serverList;
 
 /**
  * 添加进入服务
  */
 public void addServer(ServerModel server)
 {
     serverList.add(server);
 }
 
 /**
  * 获取主服务
  * 服务列表是主从服务时
  */
 public ServerModel getMaster()
 {
     if(master_slave)
     {
         return master;
     }
     return null;
 }
 
 /**
  * 主从服务；返回一个action的服务
  */
 public ServerModel getFirst()
 {
     //特别返回一个action服务
     if(master_slave)
     {
      
         Iterator<ServerModel>  it=serverList.iterator();
          while(it.hasNext())
          {
              ServerModel tmp=it.next();
              if(tmp.isAction())
              {
                  return tmp;
          }
         
     }
     }
    return null;
 }
 
/**
 * 直接设置主服务
 */
 public boolean setMaster(ServerModel server)
 {
       if(master_slave)
     {
           master=server;
           return true;
     }
       else
       {
           return false;
       }
 }
 
 /**
  * 添加服务
  * 并且根据isMaster是否根据server设置主从服务
  */
 public void addServer(ServerModel server,boolean  isMaster)
 {
      //
     serverList.add(server);
     if(isMaster)
     {
         master_slave=server.master_slave;
         if(server.isMaster)
         {
             master=server;
         }
     }
     
 }
 
 /**
  * 直接设置服务列表是否是主从服务
  */
 public void setServerType(boolean isMaster)
 {
     master_slave=isMaster;
 }
 
 /**
  * 返回服务列表
  */
public  CopyOnWriteArrayList<ServerModel> getServerList()
{

    CopyOnWriteArrayList<ServerModel> list=new CopyOnWriteArrayList<ServerModel>();
     list.addAll(serverList);
     return list;
  
}
/*
 * 接收状态后更新时间
 */
public void update(ServerModel server)
{
   boolean isFind=false;
    Iterator<ServerModel>  it=serverList.iterator();
     while(it.hasNext())
     {
         ServerModel tmp=it.next();
         if(tmp.equals(server))
         {
             tmp.update();
             isFind=true;
             break;
         }
     }
     if(!isFind)
     {
         serverList.add(server);
         server.update();
     }
}

/*
 * 删除服务
 */
public void remove(ServerModel server)
{
    Iterator<ServerModel>  it=serverList.iterator();
    while(it.hasNext())
    {
        ServerModel tmp=it.next();
        if(tmp.equals(server))
        {
              it.remove();
              break;
        }
    }
}
}
