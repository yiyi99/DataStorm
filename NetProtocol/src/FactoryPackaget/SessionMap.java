/**    
 * �ļ�����SessionMap.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��6��12��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package FactoryPackaget;

import java.util.concurrent.ConcurrentHashMap;

/**    
 *     
 * ��Ŀ���ƣ�NetProtocol    
 * �����ƣ�SessionMap    
 * ��������    �����洢
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��6��12�� ����2:11:49    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��6��12�� ����2:11:49    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class SessionMap<K,V> {
 private   ConcurrentHashMap<K,V> hashMap=new ConcurrentHashMap<K,V>();
public void put(K key, V value)
{
    hashMap.put(key, value);
}
public V get(K key)
{
  return  hashMap.get(key);
}
}