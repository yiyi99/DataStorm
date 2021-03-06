/**    
 * 文件名：CacheClientListenter.java    
 *    
 * 版本信息：    
 * 日期：2017年6月13日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package FactoryPackaget;


import com.google.common.cache.RemovalNotification;

import DataBus.CacheTimeListenter;
import JNetSocket.UDPClient;

/**    
 *     
 * 项目名称：NetProtocol    
 * 类名称：CacheClientListenter    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年6月13日 下午11:39:29    
 * 修改人：jinyu    
 * 修改时间：2017年6月13日 下午11:39:29    
 * 修改备注：    
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
