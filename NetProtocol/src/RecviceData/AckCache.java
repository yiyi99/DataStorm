/**    
 * �ļ�����AckCache.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��6��12��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package RecviceData;

import JNetSocket.UDPClient;

/**    
 *     
 * ��Ŀ���ƣ�NetProtocol    
 * �����ƣ�AckCache    
 * ��������    ���淢��
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��6��12�� ����9:54:20    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��6��12�� ����9:54:20    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class AckCache {
    /**
     * ��Ӧ�İ�
     */
public AckPackaget ack;

/**
 * ���Ͷ˿�
 */
 public UDPClient client;
 
 /*
  * ���ʹ��������5����ʧ)
  */
 public int  ackNum=0;
 
 public String srcIP;
 public int srcPort;
 
}