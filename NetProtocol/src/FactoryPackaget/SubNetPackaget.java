/**    
 * �ļ�����SubPackaget.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��6��11��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package FactoryPackaget;

import java.nio.ByteBuffer;

import RecviceData.AckPackaget;

/**    
 *     
 * ��Ŀ���ƣ�NetProtocol    
 * �����ƣ�SubPackaget    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��6��11�� ����11:13:54    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��6��11�� ����11:13:54    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class SubNetPackaget {
    
    /*
     * �������緢�Ͱ�
     */
public static byte[] createNetPackaget(long sessionid,long initseq,long packagetid, int packagetNum,byte[] data)
{
    if(data==null)
    {
        return null;
    }
    byte[] bytes=new byte[16+data.length+1];//һ���ֽ����Ͱ�
    ByteBuffer buf=ByteBuffer.wrap(bytes);
    buf.put((byte) 0);
    buf.putLong(sessionid);
    buf.putLong(initseq);
    buf.putInt(packagetNum);
    buf.putLong(packagetid);
    buf.put(data);
  return  buf.array();
}

/**
 * ����ack��byte[]
 * ����1
 */
public static byte[] createAckPackaget(AckPackaget ack)
{
  int len=0;
  if(ack.ackType==2)
  {
      len=22;
  }
  else
  {
      len=14;
  }
    byte[] bytes=new byte[len];//һ���ֽ���������Ͱ�;ack����
    ByteBuffer buf=ByteBuffer.wrap(bytes);
    buf.put((byte) 1);
    buf.put(ack.ackType);
    buf.putLong(ack.sessionid);
    buf.putInt(ack.packagetNum);
    if(ack.ackType==2)
    {
        buf.putLong(ack.packagetID);
    }
    return  buf.array();
}


/*
 * ����������հ�
 */
public static ReturnCode  AnalysisNetPackaget(byte[]netdata)
{
    ReturnCode code=new ReturnCode();
    try
    {
    ByteBuffer buf=ByteBuffer.wrap(netdata);
    byte type=buf.get();
    if(type==0)
    {
    long sessionid=buf.getLong();
    long initseq=buf.getLong();
    int  num=buf.getInt();
    long packagetid=buf.getLong();
    byte[] data=new byte[netdata.length-20];
    buf.get(data);
    code.data=data;
    code.InitSeq=initseq;
    code.PackagetID=packagetid;
    code.packagetNum=num;
    code.SessionID=sessionid;
    code.isAck=false;
    return code;
    }
    else
    {
        //˵����ack,û����������
        byte acktype=buf.get();
        long sessionid=buf.getLong();
        int  num=buf.getInt();
        AckPackaget ack=new AckPackaget();
        ack.ackType=acktype;
        ack.packagetNum=num;
        ack.sessionid=sessionid;
        if(acktype==2)
        {
            ack.packagetID=buf.getLong();
        }
        code.isAck=true;
    }
    }
    catch(Exception ex)
    {
        code.ErrorCode=ex.getMessage();
    }
    return code;
    
}
}