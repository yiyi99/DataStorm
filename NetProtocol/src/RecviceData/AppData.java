/**    
 * 文件名：AppData.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package RecviceData;

/**    
 *     
 * 项目名称：DataStromUtil    
 * 类名称：AppData    
 * 类描述：  存储数据格  
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 上午11:11:46    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 上午11:11:46    
 * 修改备注：    
 * @version     
 *     
 */
public class AppData {
    final long sequenceNumber;
    final byte[] data;
    public AppData(long sequenceNumber, byte[]data){
        this.sequenceNumber=sequenceNumber;
        this.data=data;
    }
    public String toString(){
        return sequenceNumber+"["+data.length+"]";
    }

    public long getSequenceNumber(){
        return sequenceNumber;
    }

}
