/**    
 * �ļ�����judp.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��6��11��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package NetProtocol;



import java.util.LinkedList;

import CacheDataReset.CacheOrg;
import FactoryPackaget.SubNetPackaget;

import JNetSocket.UDPClient;
import NetPackaget.PackagetRandom;
import NetPackaget.SubPackaget;

/**    
 *     
 * ��Ŀ���ƣ�NetProtocol    
 * �����ƣ�judp    
 * ��������   upd�ͻ���
 * ����װ���� 
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��6��11�� ����7:12:40    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��6��11�� ����7:12:40    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class judpClient {
    UDPClient client=new UDPClient();
    boolean isColse=false;
    /**
     * ��������
     * 
     */
    public void sendData(String sIP,int sPort,byte[]data)
    {
       LinkedList<byte[]> list=SubPackaget.subData(data);
      if(list!=null)
    {
        long sessionid=PackagetRandom.getSessionID();
        long initseq=PackagetRandom.getSequeueID();
        int num=list.size();
        while(list.size()>0)
        {
             long packagetID=PackagetRandom.getInstanceID(this);
             byte[] sendData=SubNetPackaget.createNetPackaget(sessionid,initseq,packagetID,num,list.removeFirst());
            client.sendData(sIP, sPort, sendData);
            putCache(sessionid,packagetID,sendData,sIP,sPort);
        }
    }
    }
    /*
     * ��������
     * �󶨱��ص�ַ
     */
    public void sendData(String  localIP,int  localPort, String sIP,int sPort,byte[]data)
    {
        LinkedList<byte[]> list=SubPackaget.subData(data);
        if(list!=null)
        {
          long sessionid=PackagetRandom.getSessionID();
          long initseq=PackagetRandom.getSequeueID();
            int num=list.size();
            while(list.size()>0)
            {
                 long packagetID=PackagetRandom.getInstanceID(this);
                 byte[] sendData=SubNetPackaget.createNetPackaget(sessionid,initseq,packagetID,num,list.removeFirst());
                 client.bindLocal(localIP,localPort);
                 client.sendData(sIP, sPort, sendData);
                putCache(sessionid,packagetID,sendData,sIP,sPort);
            }
        }
      
    }
    
    /**
     * 
     * ����
    
     */
    private void putCache(long sessionid,long packagetid ,byte[]data,String host,int port)
    {
        String key=String.valueOf(sessionid)+String.valueOf(packagetid);
        CacheOrg.put(key, client, data, host, port);
    }
    
    /**
     * �߼��Ϲر�
     */
    public void close()
    {
        isColse=true;
        client.protocolClose();
    }
    /**
     * �����߼��ر�
     */
    public boolean isClose()
    {
        return isColse;
    }
    
    /*
     * ��������
     */
    public byte[]  getCallBackData()
    {
       return client.getCallBackData();
    }
}