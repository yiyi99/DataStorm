/**    
 * �ļ�����IDataPackaget.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��6��10��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package Util;

/**    
 *     
 * ��Ŀ���ƣ�DataStromUtil    
 * �����ƣ�IDataPackaget    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��6��10�� ����2:05:59    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��6��10�� ����2:05:59    
 * �޸ı�ע��    
 * @version     
 *     
 */
public abstract class IDataPackaget {
    /**
     * ������
     * 0 ������Ϣ
     * 1 ����״̬
     * 2  �ͻ�������
     * 3 ���ذ�
     * 4 ���ݰ�
     * 5 ������Ϣ��
     */
public  byte packagetType;
/**
 * ��������
 */
public String serverName;

/**
 * session id
 */
public long sessionid;
}