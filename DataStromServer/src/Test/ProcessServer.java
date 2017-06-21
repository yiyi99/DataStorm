/**    
 * 文件名：ProcessServer.java    
 *    
 * 版本信息：    
 * 日期：2017年6月19日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Test;

import IServer.IProcessServer;
import Util.IDataPackaget;

/**    
 *     
 * 项目名称：DataStromServer    
 * 类名称：ProcessServer    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年6月19日 上午12:04:02    
 * 修改人：jinyu    
 * 修改时间：2017年6月19日 上午12:04:02    
 * 修改备注：    
 * @version     
 *     
 */
public class ProcessServer implements IProcessServer {

    /* (non-Javadoc)    
     * @see IServer.IProcessServer#recRequest(Util.IDataPackaget)    
     */
    @Override
    public void recRequest(IDataPackaget packaget) {
       System.out.println(packaget.serverName+"收到请求");

    }

    /* (non-Javadoc)    
     * @see IServer.IProcessServer#take()    
     */
    @Override
    public IDataPackaget take() {
        // TODO Auto-generated method stub
        return null;
    }

}
