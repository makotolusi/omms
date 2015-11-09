package m.w.sys.quartz.util;

import java.util.Date;

import m.w.App;

import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.TriggerUtils;
import org.quartz.spi.OperableTrigger;

public class Quartzs {
	private static Log log = Logs.get();

	public static Scheduler getScheduler() {
	    Scheduler sched = App.getScheduler();
	    if(sched == null){
	        sched = Schedulers.init();
	    }
	    if(sched == null){
	        throw Lang.makeThrow("获取Scheduler对象失败!");
	    }
		return sched;
	}

	public static Date add(JobDetail job, Trigger trigger) {
		Date nextFireTime = null;
		Scheduler sched = getScheduler();
		try {
			if (!sched.checkExists(job.getKey()) && !sched.checkExists(trigger.getKey())) {
				sched.scheduleJob(job, trigger);
				nextFireTime = TriggerUtils.computeFireTimes((OperableTrigger) trigger, null, 1).get(0);
			} else {
				nextFireTime = sched.getTrigger(trigger.getKey()).getNextFireTime();
			}
		} catch (SchedulerException e) {
		    log.error("调度任务异常", e);
		}

		return nextFireTime;
	}

	public static boolean delete(JobDetail job) {
		Scheduler sched = getScheduler();
		try {
			if (!sched.checkExists(job.getKey())) {
				return sched.deleteJob(job.getKey());
			}
		} catch (SchedulerException e) {
		    log.error("调度任务异常", e);
		}
        return false;
	}

	public static void pauseTrigger(TriggerKey key) {
		Scheduler sched = getScheduler();
		try {
			if (!sched.checkExists(key)) {
				sched.pauseTrigger(key);
			}
		} catch (SchedulerException e) {
		    log.error("调度任务异常", e);
		}
	}

	public static void resumeTrigger(TriggerKey key) {
		Scheduler sched = getScheduler();
		try {
			if (!sched.checkExists(key)) {
				sched.resumeTrigger(key);
			}
		} catch (SchedulerException e) {
		    log.error("调度任务异常", e);
		}
	}

	public static void pauseJob(JobKey key) {
		Scheduler sched = getScheduler();
		try {
			if (!sched.checkExists(key)) {
				sched.pauseJob(key);
			}
		} catch (SchedulerException e) {
		    log.error("调度任务异常", e);
		}
	}

	public static void resumeJob(JobKey key) {
		Scheduler sched = getScheduler();
		try {
			if (!sched.checkExists(key)) {
				sched.resumeJob(key);
			}
		} catch (SchedulerException e) {
		    log.error("调度任务异常", e);
		}
	}
}
