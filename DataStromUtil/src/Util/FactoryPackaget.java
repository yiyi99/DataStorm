/**    
 * 文件名：FactoryPackaget.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import Model.MasterModel;

/**    
 *     
 * 项目名称：DataStromUtil    
 * 类名称：FactoryPackaget    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 上午2:03:23    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 上午2:03:23    
 * 修改备注：    
 * @version     
 *     
 */
public class FactoryPackaget {
   private ReqPackaget createReqPackaget(ByteBuffer buf)
   {
       ReqPackaget packaget=new ReqPackaget();
       packaget.packagetType=2;
       packaget.packagetID=buf.getInt();
       packaget.reqType=buf.get();
       short len=buf.getShort();
        byte[] name=new byte[len];
        buf.get(name);
        packaget.serverName=new String(name);
       byte[]args=new byte[buf.limit()-buf.position()];
       buf.get(args);
       packaget.args=args;
       return packaget;
   }
   private RspPackaget createRspPackaget(ByteBuffer buf)
   {
       RspPackaget packaget=new RspPackaget();
       packaget.packagetType=3;
       packaget.packagetID=buf.getInt();
       short len=buf.getShort();
        byte[] name=new byte[len];
        buf.get(name);
        packaget.serverName=new String(name);
        byte[]data=new byte[buf.limit()-buf.position()];
        buf.get(data);
        packaget.result=data;
       return packaget;
   }

   private ServerInfo createServerInfo(ByteBuffer buf)
   {
       ServerInfo packaget=new ServerInfo();
       packaget.packagetType=0;
       packaget.sessionid=buf.getLong();
       //
       short len=buf.getShort();
        byte[] name=new byte[len];
        buf.get(name);
        packaget.serverName=new String(name);
        //
        short ipLen=buf.getShort();
       byte[]srcIP=new byte[ipLen];
       buf.get(srcIP);
       packaget.IP=new String(srcIP);
       //
       packaget.port=buf.getInt();
       
       packaget.netType=buf.get();
       
       packaget.master_slave=buf.get()==0?false:true;
       packaget.isMaster=buf.get()==0?false:true;
       return packaget;
   }
   private ServerState createServerState(ByteBuffer buf)
   {
       ServerState packaget=new ServerState();
       packaget.packagetType=1;
       packaget.sessionid=buf.getLong();
       //
       short len=buf.getShort();
        byte[] name=new byte[len];
        buf.get(name);
        packaget.serverName=new String(name);
        //
        short ipLen=buf.getShort();
       byte[]srcIP=new byte[ipLen];
       buf.get(srcIP);
       packaget.srcIP=new String(srcIP);
       //
       packaget.srcPort=buf.getInt();
       //
       short fLen=buf.getShort();
       byte[] flage=new byte[fLen];
       packaget.flage=new String(flage);
       //
      
       return packaget;
   }
   private DataPackaget createDataPackaget(ByteBuffer buf)
   {
       DataPackaget packaget=new DataPackaget();
       packaget.packagetType=4;
       packaget.sessionid=buf.getLong();
       packaget.reqCall=buf.get();
       //
       short len=buf.getShort();
        byte[] name=new byte[len];
        buf.get(name);
        packaget.serverName=new String(name);
      //
        int pid=buf.getInt();
        packaget.packagetID=pid;
        byte[] data=new byte[buf.limit()-buf.position()];
        buf.get(data);
       return packaget;
   }
   private MasterModel createMasterPackaget(ByteBuffer buf,byte type)
   {
       MasterModel packaget=new MasterModel();
       packaget.packagetType=type;
       packaget.sessionid=buf.getLong();
       //
       short len=buf.getShort();
        byte[] IP=new byte[len];
        buf.get(IP);
        packaget.IP=new String(IP);
      //
        packaget.port=buf.getInt();
      //
        short mlen=buf.getShort();
        byte[]multIP=new byte[mlen];
        buf.get(multIP);
        packaget.multIP=new String(multIP);
        packaget.multPort=buf.getInt();
        packaget.centerByte=buf.get();
        packaget.action=buf.get()==0?false:true;
        //
        int flen=buf.getInt();
         byte[] falge=new byte[flen];
       // byte[] data=new byte[buf.limit()-buf.position()];
        buf.get(falge);
        packaget.flage=new String(falge);
       return packaget;
   }
   /*
    * 组包
    */
   public byte[] unDataModel(IDataPackaget packaget)
   {
       if(packaget==null)
       {
           return null;
       }
       byte[] data=null;
       if(packaget instanceof ReqPackaget)
       {
           ReqPackaget tmp=(ReqPackaget)packaget;
           int len=1024;
           if(tmp.args!=null)
           {
               len+=tmp.args.length;
           }
           byte[] all=new byte[len];
           ByteBuffer buf=ByteBuffer.wrap(all);
           buf.put(packaget.packagetType);
           buf.putLong(tmp.sessionid);
           buf.putInt(tmp.packagetID);
           buf.put(tmp.reqType);
           byte[] name = null;
        try {
            name = tmp.serverName.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
           buf.putShort((short)name.length);
           buf.put(name);
           buf.put(tmp.args);
           data=buf.array();
       }
       else if(packaget instanceof RspPackaget)
       {
           RspPackaget tmp=(RspPackaget)packaget;
           int len=1024;
           if(tmp.result!=null)
           {
               len+=tmp.result.length;
           }
           byte[] all=new byte[len];
           ByteBuffer buf=ByteBuffer.wrap(all);
           buf.put(packaget.packagetType);
           buf.putLong(tmp.sessionid);
           buf.putInt(tmp.packagetID);
      
           byte[] name = null;
        try {
            name = tmp.serverName.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
           buf.putShort((short)name.length);
           buf.put(name);
           buf.put(tmp.result);
           data=buf.array();
       }
       else if(packaget instanceof ServerInfo)
       {
           ServerInfo tmp=(ServerInfo)packaget;
           int len=1024;
           byte[] all=new byte[len];
           ByteBuffer buf=ByteBuffer.wrap(all);
           buf.put(tmp.packagetType);
           buf.putLong(tmp.sessionid);
           byte[] name = null;
        try {
              name = tmp.serverName.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
           buf.putShort((short)name.length);
           buf.put(name);
           //
           byte[] ip = null;
           try {
                 ip = tmp.IP.getBytes("utf-8");
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
              buf.putShort((short)ip.length);
              buf.put(ip);
              //
              buf.putInt(tmp.port);
              buf.put(tmp.netType);
              byte tmpbyte=0;
              tmpbyte=(byte) (tmp.master_slave==true?1:0);
              buf.put(tmpbyte);
              tmpbyte=(byte) (tmp.isMaster==true?1:0);
              buf.put(tmpbyte);
              buf.flip();
             data=new byte[buf.limit()];
             System.arraycopy(all, 0, data, 0, data.length);
      
       }
       else if(packaget instanceof ServerState)
       {
           ServerState tmp=(ServerState)packaget;
           int len=1024;
           byte[] all=new byte[len];
           ByteBuffer buf=ByteBuffer.wrap(all);
           buf.put(tmp.packagetType);
           buf.putLong(tmp.sessionid);
           byte[] name = null;
        try {
              name = tmp.serverName.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
           buf.putShort((short)name.length);
           buf.put(name);
           //
           byte[] ip = null;
           try {
                 ip = tmp.srcIP.getBytes("utf-8");
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
              buf.putShort((short)ip.length);
              buf.put(ip);
              //
              buf.putInt(tmp.srcPort);
             //
              byte[] flage = null;
              try {
                  flage = tmp.flage.getBytes("utf-8");
              } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
              }
                 buf.putShort((short)flage.length);
                 buf.put(flage); 
                 //
             
                data=buf.array();
       }
       else if(packaget instanceof DataPackaget)
       {
           DataPackaget tmp=(DataPackaget)packaget;
           int len=1024;
           byte[] all=new byte[len];
           ByteBuffer buf=ByteBuffer.wrap(all);
           buf.put(tmp.packagetType);
           buf.putLong(tmp.sessionid);
           buf.put(tmp.reqCall);
           byte[] name = null;
        try {
              name = tmp.serverName.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
           buf.putShort((short)name.length);
           buf.put(name);
           //
          buf.putInt(tmp.packagetID);
             //
            buf.put(tmp.data);
            data=buf.array();
       }
       else if(packaget instanceof MasterModel)
       {
           MasterModel tmp=(MasterModel)packaget;
           int len=1024;
           byte[] all=new byte[len];
           ByteBuffer buf=ByteBuffer.wrap(all);
           buf.put(tmp.packagetType);
           buf.putLong(tmp.sessionid);
           byte[]ip=tmp.IP.getBytes();
           //
           int iplen=ip.length;
           buf.putShort((short) iplen);
           buf.put(ip);
           //
           buf.putInt(tmp.port);
           //
           byte[]multIP=tmp.multIP.getBytes();
           short mIPLen=(short)multIP.length;
           buf.putShort(mIPLen);
           buf.put(multIP);
           //
           buf.putInt(tmp.multPort);
           //
           buf.put(tmp.centerByte);
           //
           byte action=(byte) (tmp.action==true?1:0);
           buf.put(action);
           //
           byte[]flagebyte=tmp.flage.getBytes();
            buf.putInt(flagebyte.length);
           buf.put(flagebyte);
           buf.flip();
           data=new byte[buf.limit()];
           System.arraycopy(all, 0, data, 0, data.length);
       }
       return data;
   }

   /*
    * 解析包
    */
   public IDataPackaget unPackaget(byte[]data)
{
    IDataPackaget packaget = null;
    ByteBuffer buf=ByteBuffer.wrap(data);
     byte  type=buf.get();
     switch(type)
     {
     case 0:
         packaget=this.createServerInfo(buf);
         break;
     case 1:
         packaget=this.createServerState(buf);
         break;
     case 2:
         packaget=this.createReqPackaget(buf);
         break;
     case 3:
         packaget=this.createRspPackaget(buf);
         break;
     case  6:
         packaget=this.createMasterPackaget(buf,(byte)6);
         break;
         default:
             packaget=this.createDataPackaget(buf);
             break;
             
     }
    return packaget;
    
}
}
