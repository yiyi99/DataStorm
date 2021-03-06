/**    
 * 文件名：Session.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package RecviceData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import FactoryPackaget.ReturnCode;
import FactoryPackaget.SubNetPackaget;
import JNetSocket.UDPClient;


/**    
 *     
 * 项目名称：DataStromUtil    
 * 类名称：Session    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 下午5:17:26    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 下午5:17:26    
 * 修改备注：    
 * @version     
 *     
 */
public class Session {
  public Session(int num)
  {
     buffer=new ReceiveBuffer(num,0);
  }
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

/*
* 需要socket通讯时保持
* 一般直接保持服务端的socket或者tcp客户端
*/
public Object socket;//
public long id;
public String flage;
private ReceiveBuffer buffer;
private AppData currentChunk=null;

/**
 * 保存数据
 */
public void addData(ReturnCode  data)
{
    if(data.isAck)
    {
        return;
    }
    AppData buf=new AppData(data.PackagetID, data.data);
    setData(buf);
    
}

/*
 * 保存
 */
private boolean  setData(AppData data)
{
    return buffer.offer(data);
}
public byte[] read()
{
    ArrayList<byte[]> list=new   ArrayList<byte[]>();
    int len=0;
    while(true)
    {
            updateCurrentChunk(false);//取一次数据
            while(currentChunk!=null){
                byte[]data=currentChunk.data;
                list.add(data);
                len+=data.length;
                updateCurrentChunk(true);
            }
           if(buffer.isEmpty())
           {
               //读取完成
               byte[] all=new byte[len];
               int offset=0;
               for(int i=0;i<list.size();i++)
               {
                   byte[] tmp=list.get(i);
                   System.arraycopy(tmp, 0, all, offset, tmp.length);
                   offset+=tmp.length;
               }
               list.clear();
               return all;
               }
        
    }
         
}
private void updateCurrentChunk(boolean block)
{
    if(currentChunk!=null)return;
    while(true){
        try{
            if(block){
                 currentChunk=buffer.poll(1, TimeUnit.MILLISECONDS);//整个取出
                while (currentChunk==null){
                    //循环直到取出
                    currentChunk=buffer.poll(1000, TimeUnit.MILLISECONDS);
                    if(currentChunk==null)
                    {
                        //可能丢包了
                        UDPClient client=new UDPClient();
                        AckPackaget ack=new AckPackaget();
                        ack.packagetID=buffer.waitSequenceNumber();
                        ack.sessionid=this.id;
                        ack.ackType=2;
                        byte[]data= SubNetPackaget.createAckPackaget(ack);
                        client.sendData(this.srcIP, this.srcPort, data);
                        break;
                        
                    }
                }
            }
            else currentChunk=buffer.poll(100, TimeUnit.MILLISECONDS);
           //不管取不取都执行
        }catch(InterruptedException ie){
            IOException ex=new IOException();
            ex.initCause(ie);
        }
        return;//跳出循环
    }
}
}
