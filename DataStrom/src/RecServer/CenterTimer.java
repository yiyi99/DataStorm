/**    
 * �ļ�����CenterTimer.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��6��15��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package RecServer;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import Config.CenterConfig;
import JNetSocket.MulticastClient;
import Model.StromCenterModel;
import Util.FactoryPackaget;
import Util.ServerInfo;

/**    
 *     
 * ��Ŀ���ƣ�DataStrom    
 * �����ƣ�CenterTimer    
 * ��������  ��ʱ���
 * 1 ���������ӵķ���
 * 2 ����������Ϣ
 * 3 ����ע������״̬
 * 4 ��ʱȷ��ע�������Ƿ�ֹͣ
 * 5 ѡ��֪ͨ��ע������  
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��6��15�� ����1:53:48    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��6��15�� ����1:53:48    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class CenterTimer {
    private static Thread  stateReset=null;//����߳�
    private static ReentrantLock lock=new ReentrantLock();
    private static boolean isRuning;//�����߳�����
    private static LinkedBlockingQueue<ServerInfo> newadd=new LinkedBlockingQueue<ServerInfo>();
    public static void addServer(ServerInfo info)
    {
        newadd.offer(info);
    }
    /*
     * �����̷߳���state
     */
    public static void startThread()
    {
            try
            {
            if(stateReset==null)
            {
                stateReset=new   Thread(new Runnable()
                        {
                           @Override
                           public void run() {
                               FactoryPackaget f=new FactoryPackaget();
                               MulticastClient client=new MulticastClient();//�鲥ͨѶ
                              while(isRuning)
                              {
                               try {
                                       ServerInfo tmp=newadd.take();
                                        byte[]data= f.unDataModel(tmp);
                                        client.sendData(CenterConfig.localCenter.multIP, CenterConfig.localCenter.port, data);
                                        
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }
                               
                              }
                           }
                    
                        });
                stateReset.setDaemon(true);
                stateReset.setName("sendServerState");
                stateReset.start();
                    
                }
            }
            finally
            {
                lock.unlock();
            }
        }
    
    /*
     * �����̷߳���state
     */
    public static void startStateThread()
    {
            try
            {
         
                Thread  state=new   Thread(new Runnable()
                        {
                           @Override
                           public void run() {
                               FactoryPackaget f=new FactoryPackaget();
                               MulticastClient client=new MulticastClient();//�鲥ͨѶ
                              while(isRuning)
                              {
                             
                                     //
                                        StromCenterModel tmp=new StromCenterModel();
                                        byte[]data= f.unDataModel(tmp);
                                        client.sendData(CenterConfig.localCenter.multIP, CenterConfig.localCenter.port, data);
                                        
                            
                               
                              }
                           }
                    
                        });
                state.setDaemon(true);
                state.setName("centerState");
                state.start();
            }
            finally
            {
              
            }
        }
    
    
    
    
}