/**
 * 
 */
package StromModel;

import DataStrom.TimerCount;

/**
 * @author jinyu
 *
 */
public class ServerModel {
/**
 * 服务IP
 */
public String IP;
/**
 * 服务端口
 */
public int port;

/**
 * 服务名称
 */
public String name;
/*
 * 通讯方式
 */
public byte netType;

/*
 * 是否是主从服务
 */
public boolean master_slave;

/*
 * 是否是主服务
 */
public boolean isMaster;

/*
 * 是否存活
 */
private boolean action=true;

public String falge;

/*
 * 判断存活时间（秒）
 */
private int timeFlash=3;

private long flashCount=0;

/**
 * 判断服务存活
 */
public boolean isAction()
{
    if(TimerCount.CountTime()-flashCount>timeFlash)
    {
        action=false;
    }
    return action;
}
public void update()
{
    action=true;
    flashCount=TimerCount.CountTime();
    
}
public boolean equals(Object obj)
{
    if (this == obj)      //传入的对象就是它自己，如s.equals(s)；肯定是相等的；  
        return true;   
    if (obj == null)     //如果传入的对象是空，肯定不相等  
        return false;  
    if (getClass() != obj.getClass())  //如果不是同一个类型的，如Studnet类和Animal类，  
                                       //也不用比较了，肯定是不相等的  
        return false;  
    ServerModel other = (ServerModel) obj;       
    if (name == null) {  
        if (other.name != null)  
            return false;  
    } else if (!name.equals(other.name))   //如果name属性相等，则相等  
        return false;  
    if(other.IP.equals(IP)&&other.port==port)
    {
        return true;
    }
    else
    {
        return false;  
    }
}

}
