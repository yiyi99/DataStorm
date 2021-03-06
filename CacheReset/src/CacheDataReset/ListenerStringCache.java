/**    
 * 文件名：ListenerCache.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package CacheDataReset;

import com.google.common.cache.RemovalNotification;

import DBTimer.DBClearTimer;
import DataBus.CacheTimeListenter;
import DataJsonSerializer.JsonSerializer;
import JNetSocket.UDPClient;

/**    
 *     
 * 项目名称：DataStromServer    
 * 类名称：ListenerCache    
 * 类描述：    以string作为key缓存
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 下午3:49:31    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 下午3:49:31    
 * 修改备注：    
 * @version     
 *     
 */
public class ListenerStringCache extends CacheTimeListenter<String, CacheModel>{

    @Override
    public void onRemoval(RemovalNotification<String, CacheModel> data) {
     String key=   data.getKey();
     CacheModel v=   data.getValue();
     DBCacheModel model=new DBCacheModel();
      model.remoteHost=v.client.getRemoteHost();
      model.remotePort=v.client.getRemotePort();
      model.localHost=v.client.getLocalHost();
      model.localPort=v.client.getLocalPort();
      model.data=v.data;
      byte[] k=JsonSerializer.serializerObject(key);
      byte[] vbyte=JsonSerializer.serializerObject(v);
      this.put(k, vbyte);
      DBClearTimer.addString(key);
    }

    @Override
    public void putDB(String key, CacheModel v) {
        
    }

    @Override
    public CacheModel getDB(String key) {
       byte[] keys=JsonSerializer.serializerObject(key);
       byte[]  v=this.get(keys);
       DBCacheModel model=(DBCacheModel) JsonSerializer.reserializerObject(v);
       CacheModel cache=new CacheModel();
       cache.data=model.data;
       UDPClient client=new UDPClient();
       client.bindLocal(model.localHost, model.localPort);
       cache.remoteHost=model.remoteHost;
       cache.remotePort=model.remotePort;
       return cache;
       
    }

}
