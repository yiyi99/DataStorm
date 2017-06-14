/**    
 * �ļ�����CacheClientListenter.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��6��13��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package FactoryPackaget;


import com.google.common.cache.RemovalNotification;

import DataBus.CacheTimeListenter;
import JNetSocket.UDPClient;

/**    
 *     
 * ��Ŀ���ƣ�NetProtocol    
 * �����ƣ�CacheClientListenter    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��6��13�� ����11:39:29    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��6��13�� ����11:39:29    
 * �޸ı�ע��    
 * @version     
 * @param <K>
 * @param <V>
 *     
 */

public class CacheClientListenter  extends CacheTimeListenter<Long,UDPClient> {

    @Override
    public void onRemoval(RemovalNotification<Long, UDPClient> arg) {
      arg.getValue().close();
    }

    @Override
    public void putDB(Long key, UDPClient v) {
  
        
    }

    @Override
    public UDPClient getDB(Long key) {
    
        return null;
    }


}