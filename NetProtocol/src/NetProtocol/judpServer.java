/**    
 * 文件名：judpServer.java    
 *    
 * 版本信息：    
 * 日期：2017年6月11日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package NetProtocol;

import EventBus.MessageBus;
import JNetSocket.UDPServerSocket;

/**    
 *     
 * 项目名称：NetProtocol    
 * 类名称：judpServer    
 * 类描述：udp服务端接收
 * 创建人：jinyu    
 * 创建时间：2017年6月11日 下午7:23:58    
 * 修改人：jinyu    
 * 修改时间：2017年6月11日 下午7:23:58    
 * 修改备注：    
 * @version     
 *     
 */
public class judpServer {
    UDPServerSocket  server=null;
    ListenerServer listener=null;
    /*
     * 初始化监听
     */
    public boolean InitServer(String host,int port)
    {
        if(server==null)
        {
            server=new UDPServerSocket();
            boolean    r= server.InitServer(host, port);
            if(r)
            {
                //初始化成功
                listener=new ListenerServer();
                MessageBus.register("udp", listener);
            }
        }
        return true;
    }
    public void close()
    {
        if(server!=null)
        {
            server.close();
        }
    }
}
