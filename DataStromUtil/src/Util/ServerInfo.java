/**    
 * 文件名：ServerInfo.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Util;

/**    
 *     
 * 项目名称：DataStromUtil    
 * 类名称：ServerInfo    
 * 类描述：    服务信息；服务端发送给中心
 * 注册使用
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 上午1:49:01    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 上午1:49:01    
 * 修改备注：    
 * @version     
 *     
 */
public class ServerInfo extends IDataPackaget{
    public ServerInfo()
    {
        this.packagetType=0;
    }
    public String IP;
    public int port;
    public byte netType=0;
    public String serverName;
    public boolean master_slave=false;
    public boolean isMaster;
}
