/**    
 * 文件名：DataPackaget.java    
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
 * 类名称：DataPackaget    
 * 类描述： 发给服务的数据包
 * 与客户端服务端的交互
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 下午5:27:55    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 下午5:27:55    
 * 修改备注：    
 * @version     
 *     
 */
public class DataPackaget extends IDataPackaget {
    public DataPackaget()
    {
        this.packagetType=4;
    }
    public  String  serverName;
    /*
     * 0 不需要返回
     * 1  需要返回
     */
    public  byte  reqCall;

    /*
     * 转发时的数据
     */
    public  byte[] data;

    /*
     * 数据包ID
     */
    public int packagetID;
    
}
