/**    
 * 文件名：IProcessServer.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package IServer;

import Util.IDataPackaget;

/**    
 *     
 * 项目名称：DataStromServer    
 * 类名称：IProcessServer    
 * 类描述：  业务处理类  
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 下午5:13:53    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 下午5:13:53    
 * 修改备注：    
 * @version     
 *     
 */
public interface IProcessServer {
public void  recRequest(IDataPackaget packaget);
public IDataPackaget  take();

}
