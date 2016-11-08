package com.tt.traffic.common.util;

public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_USER_RIGHTS = "sessionUserRights";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(toUpload)|(uploadFile)|(logout)|(code)|(register)|(regist)|(index)|(data)).*";	//不对匹配该值的访问路径拦截（正则）
	//public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	//public static Config CONFIG_OBJECT;
	//获取用户群组地址
	public static final String BASE_PATH = "base_path";
	//上传文件和图片的的地址前缀
	public static final String BASE_FILER = "base_filer";
	//图片上传路径
	public static final String UPLOAD_FOLDER = "upload_folder";
	//常量
	public static final int YN_STATUS = 1;
	//public static final Map<String, List<CodeItem>> codeMap = new HashMap<String, List<CodeItem>>();
	
	public interface MODEL_TYPE{
		public byte  MACRO = 1;
		public byte	MEDIUM = 2;
		public byte  MICRO = 3;
		public byte INTERSECTION = 4;
		
	}
}
