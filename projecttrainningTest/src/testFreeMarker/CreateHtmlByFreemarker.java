package testFreeMarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class CreateHtmlByFreemarker {
	// private static final String TEMPLATE_PATH = "E:\\eclipse
	// space\\freemarkerpro\\src\\templates";
	// private static final String CLASS_PATH = "E:\\eclipse
	// space\\freemarkerpro\\product";
	// 使用相对路径比较好（Java默认定位到当前用户目录下，即根目录）
	private static final String TEMPLATE_PATH = "src/templates";
	private static final String CLASS_PATH = "product";

	public static void main(String[] args) {
		// 1 创建freeMarker配置实例
		Configuration configuration = new Configuration();
		Writer out = null;
		try {
			// 2 获取模版路径,指定模板文件从何处加载的数据源，这里设置成一个文件目录
			configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
			// 3 创建数据模型
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("username", "zhangsan");
			dataMap.put("password", "123456");
			dataMap.put("age", "18");
			dataMap.put("address", "test");
			// 4 加载模版文件
			Template template = configuration.getTemplate("user.ftl");
			// 5 生成数据
			File docFile = new File(CLASS_PATH + "\\" + "user.html");
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
			// 6 输出文件
			template.process(dataMap, out);
			System.out.println("user.ftl 文件创建成功 !");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out) {
					out.flush();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
