/**    
 * �ļ�����CallNetModel.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��6��12��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package ListernerServer;

/**    
 *     
 * ��Ŀ���ƣ�DataStromServer    
 * �����ƣ�CallNetModel    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��6��12�� ����3:21:51    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��6��12�� ����3:21:51    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class CallNetModel {
    /*
     * ��ԴIp
     */
  public String srcIP;
  /*
  * ��Դ�˿�
  */
  public int srcPort;

  /**
   * ����IP
   */
  public String localIP;

  /*
   * ����IP
   */
  public int localPort;
  /*
  * ͨѶЭ������ 0 tcp  1 udp  2 �鲥 3 �㲥
  */
  public int  netType;
  
  public long  id;

  /*
  * ��ҪsocketͨѶʱ����
  * һ��ֱ�ӱ��ַ���˵�socket����tcp�ͻ���
  */
  public Object socket;//
  
}