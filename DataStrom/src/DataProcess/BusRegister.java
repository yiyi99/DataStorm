/**    
 * 文件名：BusRegister.java    
 *    
 * 版本信息：    
 * 日期：2017年6月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DataProcess;

import EventBus.MessageBus;

/**    
 *     
 * 项目名称：DataStrom    
 * 类名称：BusRegister    
 * 类描述：    所有监听注册
 * 创建人：jinyu    
 * 创建时间：2017年6月10日 上午3:10:55    
 * 修改人：jinyu    
 * 修改时间：2017年6月10日 上午3:10:55    
 * 修改备注：    
 * @version     
 *     
 */
public class BusRegister {
    
    /*
     * 启动注册
     */
public void start()
{
    ReqProcess req=new ReqProcess();
    MessageBus.register("req", req);
    //
    RspProcess rsp=new RspProcess();
    MessageBus.register("rsp", rsp);
    //
    StateProcess state=new StateProcess();
    MessageBus.register("state", state);
    //
//    CenterStateProcess center=new CenterStateProcess();
//    MessageBus.register("stromState", center);
    //
    MasterProcess master=new MasterProcess();
    MessageBus.register("master", master);
    //
//    CenterServerProcess serverInfo=new CenterServerProcess();
//    MessageBus.register("stromserverinfo", serverInfo);
}
}
