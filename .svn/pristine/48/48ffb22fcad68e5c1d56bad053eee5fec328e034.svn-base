package m.w.codegen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.nutz.lang.Files;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CodeGen {
    private Config config;
    private Configuration cfg;
    private Map<String, Object> root;
    
    public static void gen(Class<?>... domainClass){
        if(domainClass == null){
            System.err.println("请输入实体类！");
        }
        for(Class<?> clazz : domainClass){
            Config c = Config.make(clazz);
            if(c != null){
                (new CodeGen(c)).genCode();
            }
        }
    }

    private CodeGen(Config config) {
        try {
            this.config = config;
            cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(Files.findFile(config.templatePath()));
            cfg.setObjectWrapper(new DefaultObjectWrapper());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void genCode(){
        try {
            Class<?> domainClass = config.domainClass(); 
            root = new HashMap<String, Object>();
            root.put("basePackage",       config.basePackage());
            root.put("domainName",        config.domainName());
            root.put("uncapDomainName",   config.uncapDomainName());
            root.put("lowerDomainName",   config.lowerDomainName());
            root.put("uriPrefix",         config.uriPrefix());
            root.put("chineseName",       config.chineseName());
            root.put("domainFields",      config.domainFields());

            tempToFile("${Domain}Service.ftl", getServiceSrcPath(domainClass));
            tempToFile("${Domain}Module.ftl",  getModuleSrcPath(domainClass));

            tempToFile("index.ftl",  getPagePath("index",""));
            tempToFile("add.ftl",    getPagePath("add","\\ex"));
            tempToFile("update.ftl", getPagePath("update","\\ex"));
            tempToFile("view.ftl",   getPagePath("view","\\ex"));
            tempToFile("query.ftl",  getPagePath("query","\\ex"));
            tempToFile("_form.ftl",  getPagePath("_form","\\ex"));
            
            System.out.println("代码生成完毕，请刷新工程，运行后可通过访问地址["+config.uriPrefix()+"]来查看运行情况。");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    private void tempToFile(String tempFileName, String filePath) throws IOException, TemplateException {
        boolean isCreat = Files.createNewFile(new File(filePath));
        if (isCreat) {
            File f = new File(filePath);
            Writer out = new BufferedWriter(new FileWriter(f, true));
            Template temp = cfg.getTemplate(tempFileName);
            temp.process(root, out);
            out.flush();
            System.out.println("文件写入完成：" + filePath);
        } else {
            System.err.println("文件已存在，写入失败，跳过：" + filePath);
        }
    }

    private String getServiceSrcPath(Class<?> domainClass) {
        return FilenameUtils.normalize("app\\" + config.basePackage().replace('.', '\\') + "\\service\\" + domainClass.getSimpleName() + "Service.java");
    }

    private String getModuleSrcPath(Class<?> domainClass) {
        return FilenameUtils.normalize("app\\" + config.basePackage().replace('.', '\\') + "\\module\\" + domainClass.getSimpleName() + "Module.java");
    }

    private String getPagePath(String pageName, String dir) {
        return FilenameUtils.normalize("WebContent" + dir + config.uriPrefix().replace('/', '\\') + "\\"+pageName+".jsp");
    }
}
