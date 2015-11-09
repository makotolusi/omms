package m.w.init;

public interface Initializer {

    /** 执行初始化工作 */
    public void init();

    /** 初始化工作的依赖，如果没有则返回null */
    public String[] dependence();
}
