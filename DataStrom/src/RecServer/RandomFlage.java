/**    
 * 文件名：RandomFlage.java    
 *    
 * 版本信息：    
 * 日期：2017年6月15日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package RecServer;


import java.util.Random;
import java.util.UUID;

/**    
 *     
 * 项目名称：DataStrom    
 * 类名称：RandomFlage    
 * 类描述：    输出标识
 * 创建人：jinyu    
 * 创建时间：2017年6月15日 下午11:24:32    
 * 修改人：jinyu    
 * 修改时间：2017年6月15日 下午11:24:32    
 * 修改备注：    
 * @version     
 *     
 */
public class RandomFlage {
    /*
     * 产生ID标识
     */
public static  int  getFlage()
{
    Random r = new Random(UUID.randomUUID().hashCode());
    return  r.nextInt(1000);
}
}
