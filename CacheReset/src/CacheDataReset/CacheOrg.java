/**    
 * 文件名：CacheOrg.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package CacheDataReset;

import JNetSocket.UDPClient;


/**    
 *     
 * 项目名称：DataStromServer    
 * 类名称：CacheOrg    
 * 类描述：  数据缓存并超时持久化
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 下午4:39:51    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 下午4:39:51    
 * 修改备注：    
 * @version     
 *     
 */
public class CacheOrg {
public static boolean isLoadDB=true;
public static void put(String key,UDPClient client,byte[]data,String host,int port)
{
    CacheModel cache=new CacheModel();
    cache.data=data;
    cache.client=client;
    cache.remoteHost=host;
    cache.remotePort=port;
    CacheBus.isLoadDB=isLoadDB;
    CacheBus.getInstance().put(key, cache);
}
public static void put(long key,UDPClient client,byte[]data,String host,int port)
{
    CacheModel cache=new CacheModel();
    cache.data=data;
    cache.client=client;
    cache.remoteHost=host;
    cache.remotePort=port;
    CacheBus.isLoadDB=isLoadDB;
    CacheBus.getInstance().put(key, cache);
}
}
