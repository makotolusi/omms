package m.w.sys.quartz;

import m.w.sys.util.Webs;

import org.nutz.lang.Mirror;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

/**
 * 提供简单的调用 IoC 对象方法的任务支持，当前仅支持类型 IoC 对象中无参方法的调用.
 * <br>
 * 该类型的调用需要在 job-detail 的 job-data-map 中至少配置targetObject、targetMethod两个参数的值，targetClass为可选配参数。<br>
 * 需要特别配置的参数为<br>
 * targetObject：任务对象在IoC中的名称，为必选参数<br>
 * targetMethod：任务对象中的无参方法名，为必选参数<br>
 * targetClass：任务对象的类型，为可选参数<br>
 * 
 * @author WenWu
 *
 */
public class SimpleIocMethodInvokingJob implements Job{
    private static Log log = Logs.get();
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        log.infof("开始执行任务 %s...", jobKey);
        
        String targetClass  = context.getJobDetail().getJobDataMap().getString("targetClass");
        String targetObject = context.getJobDetail().getJobDataMap().getString("targetObject");
        String targetMethod = context.getJobDetail().getJobDataMap().getString("targetMethod");
        if(!Strings.isBlank(targetObject) && !Strings.isBlank(targetMethod)){
            try {
                Class<?> clazz = null;
                if(!Strings.isBlank(targetClass)){
                    try {
                        clazz = Class.forName(targetClass);
                        log.debugf("目标对象类型为  %s", clazz.getName());
                    }
                    catch (Exception e) {
                        log.error(e);
                    }
                }
                Object obj = Webs.ioc().get(clazz, targetObject);
                if(obj != null){
                    log.debugf("目标对象为  %s", targetObject);
                    Mirror<?> mirror = Mirror.me(obj);
                    mirror.invoke(obj, targetMethod);
                    log.debugf("调用的方法为  %s", targetMethod);
                    log.infof("任务 %s 执行完毕！", jobKey);
                }
            }
            catch (Exception e) {
                log.errorf("任务 %s 执行失败！！！", jobKey);
                log.error(e);
            }
        }else{
            log.infof("任务 %s 的配置有误，必须至少配置targetObject、targetMethod两个参数的值，targetClass为可选配参数", jobKey);
        }
    }
}
