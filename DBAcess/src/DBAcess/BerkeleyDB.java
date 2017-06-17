/**    
 * 文件名：BerkeleyDB.java    
 *    
 * 版本信息：    
 * 日期：2017年5月27日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DBAcess;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.sleepycat.je.Cursor;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

/**    
 *     
 * 项目名称：DBAcess    
 * 类名称：BerkeleyDB    
 * 类描述： BerkeleyDB 数据库初始化使用   
 * 创建人：jinyu    
 * 创建时间：2017年5月27日 上午2:05:51    
 * 修改人：jinyu    
 * 修改时间：2017年5月27日 上午2:05:51    
 * 修改备注：    
 * @version     
 *     
 */
public class BerkeleyDB {
    Environment myDbEnvironment = null;
    private Database myDatabase;
    private Cursor myCursor;
    private String dbDir="BerkeleyDB";
    public void setDir(String dir)
    {
        dbDir=dir;
    }
public void open()
{
    try {

        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);//如果不存在则创建一个
         File f=  new File(dbDir);
         if(!f.exists())
         {
             f.mkdirs();
         }
        myDbEnvironment = new Environment(f, envConfig);
        setConfig();

    } catch (DatabaseException dbe) {

        // 错误处理

    }
}
public void close()
{
    try {

        if (myDbEnvironment != null) {

            myDbEnvironment.close();

        }

    } catch (DatabaseException dbe) {

        // Exception handling goes here

    }
}
public void clearLog()
{
    try {

        if (myDbEnvironment != null) {

            myDbEnvironment.cleanLog(); // 在关闭环境前清理下日志

            myDbEnvironment.close();

        }

    } catch (DatabaseException dbe) {

        // Exception handling goes here

    }
}
public void setConfig()
{
    try {

        DatabaseConfig dbConfig = new DatabaseConfig();

        dbConfig.setAllowCreate(true);

        dbConfig.setSortedDuplicates(true);

        myDatabase =

                myDbEnvironment.openDatabase(null,

                                 "BerDB",

                                 dbConfig);

    } catch (DatabaseException dbe) {

        // Exception handling goes here.

    }
}
public void clearData()
{
    long numDiscarded=myDbEnvironment.truncateDatabase(null, myDatabase.getDatabaseName(), true);
  System.out.println("一共删除了 " + numDiscarded +" 条记录 从数据库 " + myDatabase.getDatabaseName());
}
public void insert(byte[]key,byte[]value)
{
    try {

        DatabaseEntry theKey = new DatabaseEntry(key);

        DatabaseEntry theData = new DatabaseEntry(value);

        myDatabase.put(null, theKey, theData);

    } catch (Exception e) {

        // Exception handling goes here

    }

}
public byte[] get(byte[]key)
{
    try {

        DatabaseEntry theKey = new DatabaseEntry(key);

        DatabaseEntry theData = new DatabaseEntry();

    if (myDatabase.get(null, theKey, theData, LockMode.DEFAULT) ==

            OperationStatus.SUCCESS) {

            byte[] retData = theData.getData();
            return retData;

        } else {
            return null;

        }

    } catch (Exception e) {

       return null;

    }
}
public void delete(byte[]key)
{
    try {


        DatabaseEntry theKey = new DatabaseEntry(key);

        myDatabase.delete(null, theKey);

    } catch (Exception e) {

    }
}
public void openCursor()
{
    myCursor = myDatabase.openCursor(null, null);
}
public void closeCursor()
{
    if (myCursor != null) {

        myCursor.close();

    }
}
public  HashMap<byte[] , byte[]> getCursor()
{
    DatabaseEntry foundKey = new DatabaseEntry();
    DatabaseEntry foundData = new DatabaseEntry();
    HashMap<byte[] , byte[]> map = new  HashMap<byte[] , byte[]>();
    if(myCursor.getNext(foundKey, foundData, LockMode.DEFAULT) ==OperationStatus.SUCCESS) {
        map.put(foundKey.getData(), foundData.getData());
return map;
        }
    return null;
}
public  List<byte[]> getCursor(byte[]key)
{
    LinkedList<byte[]> link=new LinkedList<byte[]>();
    DatabaseEntry foundKey = new DatabaseEntry(key);
    DatabaseEntry foundData = new DatabaseEntry();
   
    while(myCursor.getNext(foundKey, foundData, LockMode.DEFAULT) ==OperationStatus.SUCCESS) {
        link.add(foundData.getData());
        }
    return link;
}
}
