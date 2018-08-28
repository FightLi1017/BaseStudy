package android.com.navi_kotlin

/**
 * @author：lichenxi
 * @date 2018/7/11 11
 * email：525603977@qq.com
 * Fighting
 */
 interface Listener<in T> {
     fun call(t:T)
}
