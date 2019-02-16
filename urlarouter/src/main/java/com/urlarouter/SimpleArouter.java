package com.urlarouter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.util.Log;
import org.json.JSONException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：lichenxi
 * @date 2018/12/10 15
 * email：525603977@qq.com
 * Fighting
 */
public class SimpleArouter implements Navigation {

   private static final String TAG="SimpleArouter";

   private static final Configuration CONFIGURATION=new Configuration();

   private @NonNull final Context context;
   private @NonNull final Bundle bundle;
   private @NonNull final Map<String,String> queires;
   private @NonNull Uri sourseUri;

   private @NonNull Uri targetUri;

   private Integer integerFlags;

   @NonNull @CheckResult
   public static Configuration configuration(){
      return CONFIGURATION;
   }

   private SimpleArouter(@NonNull Context context,@NonNull String url) {
      this.context = context;
      this.bundle=new Bundle();
      this.sourseUri=Uri.parse(url);
      this.queires=new HashMap<>();
   }

   public static void apply(@NonNull final Map<String,Target> map){
      configuration().apply(map);
   }

   //根据json文件 解析成为map对象 然后注册到中心中
   public static void apply(String targetMapJson) throws JSONException{

   }

   public static  Navigation navigation(@NonNull Context context,@NonNull String url){
      return new SimpleArouter(context,url.trim());
   }

   @NonNull
   @Override
   public Navigation setFlags(int intentFlags) {
      this.integerFlags=intentFlags;
      return this;
   }

   @NonNull
   @Override
   public Navigation appendQueryParams(String key, String value) {
      queires.put(key,value);
      return null;
   }

   @NonNull
   @Override
   public Navigation putExtras(@NonNull Bundle bundle) {
      bundle.putAll(bundle);
      return null;
   }

   @NonNull
   @Override
   public Navigation putExtras(@NonNull Intent intent) {
      Bundle extras=intent.getExtras();
      if (extras!=null){
         return putExtras(extras);
      }
      return this;
   }

   @NonNull
   @Override
   public Navigation putExtra(@NonNull String key, int value) {
      bundle.putInt(key,value);
      return this;
   }

   @NonNull
   @Override
   public Navigation putExtra(@NonNull String key, double value) {
      bundle.putDouble(key,value);
      return this;
   }

   @NonNull
   @Override
   public Navigation putExtra(@NonNull String key, long value) {
      bundle.putLong(key,value);
      return this;
   }

   @NonNull
   @Override
   public Navigation putExtra(@NonNull String key, float value) {
      bundle.putFloat(key,value);
      return this;
   }

   @NonNull
   @Override
   public Navigation putExtra(@NonNull String key, byte value) {
      bundle.putByte(key,value);
      return this;
   }

   @NonNull
   @Override
   public Navigation putExtra(@NonNull String key, short value) {
      bundle.putShort(key,value);
      return this;
   }

   @NonNull
   @Override
   public Navigation putExtra(@NonNull String key, String value) {
      bundle.putString(key,value);
      return this;
   }

   @NonNull
   @Override
   public Navigation putExtra(@NonNull String key, Serializable value) {
     bundle.putSerializable(key,value);
      return this;
   }

   @NonNull
   @Override
   public Navigation putExtra(@NonNull String key, Parcelable value) {
      bundle.putParcelable(key,value);
      return this;
   }

   @NonNull
   @Override
   public Navigation putExtra(@NonNull String key, CharSequence value) {
      bundle.putCharSequence(key,value);
      return this;
   }

   @Override
   public void start() {
        final Intent intent=getIntent();
      if (intent != null) {

         configuration().getIntentHandler().onIntentCreated(context, intent);

      } else {
         log(Log.WARN, "The target intent is null, " +
                 "it may has been intercepted or dispatched to your TargetNotFoundHandlers");
      }
   }

   @NonNull
   @Override @CheckResult
   public Intent getIntent() {
      sourseUri=appendSourseUri(sourseUri,queires);
      Chain chain=interceptRequest(sourseUri);
      sourseUri=chain.request();
      if (chain.isProcess()){
         Target target=configuration().getTarget(getIndexUrl());
         if (target!=null){
            targetUri=createTargetUri(sourseUri,target);
         } else {
            log(Log.ERROR,getIndexUrl()+" target not found");
            onTargetNotFound(sourseUri,bundle);
            chain=chain.abort();
         }
      }
      if (chain.isProcess()){
         chain=interceptTarget(targetUri);
         targetUri=chain.request();
         if (chain.isProcess()){
            return createIntent();
         }
      }
      return null;
   }


   //合并参数
   private Uri createTargetUri(@NonNull Uri base,Target target){
      final String targetUrl=target.getTargetUrl();
      final Uri targetUri=Uri.parse(targetUrl);
      final String mergedEncodedQuery=mergeEncodeQuery(sourseUri,targetUri);
      return  base.buildUpon().scheme(targetUri.getScheme())
              .authority(targetUri.getAuthority())
              .path(targetUri.getPath())
              .encodedQuery(mergedEncodedQuery)
              .fragment(targetUri.getFragment())
              .build();

   }

   private String mergeEncodeQuery(@NonNull Uri sourseUri, @NonNull Uri targetUri) {
      final Map<String,String> map=new HashMap<>();
      map.putAll(encodedQueryParams(sourseUri));
      map.putAll(encodedQueryParams(targetUri));
      StringBuilder builder=new StringBuilder();
      for (Map.Entry<String,String> query:map.entrySet()){
         builder.append(query.getKey()).append("=").append(query.getValue()).append("&");
      }
      String result=builder.toString();
      if (result.endsWith("&")){
         result=result.substring(0,result.length()-1);
      }
      return result;
   }

   @NonNull
   private static Map<String,String>  encodedQueryParams(@NonNull final Uri uri){
      final HashMap<String,String> map=new HashMap<>();
      final String query=uri.getEncodedQuery();
      if (query!=null){
         String[] ands=query.split("&");
         for (String  and:ands){
            int splitIndex=and.indexOf("=");
            if (splitIndex!=-1){
               map.put(and.substring(0,splitIndex),and.substring(splitIndex+1));
            }else {
               Log.w("ParametersParseError","query"+query);
            }
         }
      }
      return map;
   }


   private void onTargetNotFound(Uri sourseUri, Bundle bundle) {
      boolean handled;
      for (TargetNotFoundHandler targetNotFoundHandler:configuration().getTargetNotFoundHandlers()){
         handled=targetNotFoundHandler.onTargetNotFound(context,sourseUri,bundle,integerFlags);
         if (handled){
             //对于没有发现target的 已经进行了处理
            break;
         }
      }
   }

   private Intent createIntent() {
      final Intent intent = new Intent(Intent.ACTION_VIEW, targetUri);
      if (configuration().isDebugEnabled()) {

         if (intent.getStringExtra("--sourse--") == null) {
            intent.putExtra("--sourse--", sourseUri.toString());
         }
         if (intent.getStringExtra("--target--") == null) {
            intent.putExtra("--target--", targetUri.toString());
         }

      }
      intent.putExtras(bundle);
      if (integerFlags != null) {
         intent.setFlags(integerFlags);
      }
      return intent;
   }


   //对url进行拦截 看要不要对特定对url进行处理
   private Chain interceptRequest(@NonNull Uri sourseUri) {
      Chain chain=new Chain(sourseUri);
      for (Interceptor interceptor:configuration().getRequestInterceptors()){
         chain=interceptor.intercept(chain);
         if (!chain.isProcess()){
             log(Log.INFO,"uri已经被"+interceptor.getClass().getName()+"处理了");
             break;
         }
      }
      return  chain;
   }

   @NonNull
   private Chain interceptTarget(@NonNull final Uri target){
      Chain chain=new Chain(target);
      for (Interceptor interceptor:configuration().getTargetInterceptors()){
         chain=interceptor.intercept(chain);
         if (!chain.isProcess()){
             //请求被拦截器拦截了 不会往下传递了
            break;
         }
      }
      return chain;
   }

  @NonNull
    public static Map<String,Target> getTargetMap(){
      return Collections.unmodifiableMap(configuration().getTargetMap());
  }
  @NonNull
  public static List<Target> getTargets(){
      return Collections.unmodifiableList(new ArrayList<>(configuration().getTargetMap().values()));
  }
   /**
    * 将参数和uri进行合并
    * @param base
    * @param params
    * @return
    */
   @NonNull
   private Uri appendSourseUri(@NonNull final Uri base,@NonNull final Map<String,String> params){
       final Uri.Builder sourseBuild=base.buildUpon();
       for (Map.Entry<String,String> query:params.entrySet()){
          sourseBuild.appendQueryParameter(query.getKey(),query.getValue());
       }
       return sourseBuild.build();
   }

   private static void log(int priority,String message){
      if (configuration().isDebugEnabled()){
         Log.println(priority,TAG,message);
      }
   }

  @NonNull
   private String getIndexUrl(){
      return Urls.indexUrl(sourseUri);
  }
}
