package example.entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public enum StatusCode {
	//通用返回码
	@CodeAnnot("处理失败") FAIL(-1),//
	@CodeAnnot("正确处理") OK(1),//
	@CodeAnnot("发送成功") Miss(1011),//
	@CodeAnnot("验证码错误") wrongCode(1001),//
	@CodeAnnot("验证码发送失败") failCode(1012),//
	@CodeAnnot("该手机号已注册") phoneHasReg(1002),//
	@CodeAnnot("token已过期") tokenError(1003),//
	@CodeAnnot("该手机号未注册")phoneNeverReg(1004),//
	@CodeAnnot("验证码正确") rightCode(1005),//
	@CodeAnnot("用户未登录") unLogin(1006),//
	@CodeAnnot("没有该产品") noProducts(1007),//
	@CodeAnnot("点赞重复") reLike(1008),//
	@CodeAnnot("识别身份为安邦员工") EmployeeFlag(1009),
	@CodeAnnot("没有该版本") wrongVersion(1010),//
	@CodeAnnot("用户名为空") UserNameIsEmpty(1013),
	@CodeAnnot("密码为空") PasswordIsEmpty(1014),
	@CodeAnnot("您的操作过于频繁，请稍后再试") LateTry(1015),
	@CodeAnnot("门店不存在") NotExists(1016),//
	@CodeAnnot("应用程序错误") AppError(2), //
	@CodeAnnot("数据库错误") DBException(3), //
	@CodeAnnot("系统错误") SystemError(4),//
	@CodeAnnot("系统繁忙,请稍后再试") SystemBusy(5),//
	@CodeAnnot("MD5验证失败") Md5ValidEroor(6),//
	@CodeAnnot("参数错误") RequestParamsNotValid(7), //
	@CodeAnnot("邮箱格式不正确") EmailValid(8), //
	@CodeAnnot("接口错误") ItfcError(10),//
	@CodeAnnot("已存在") Exists(11),//


//	public static final String Result_Success = "2000";
//	public static final String Result_Error = "4000";
    ;

	private final int value;
	private static final Map<String, String> hMap = new HashMap<String, String>();

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	StatusCode(int value) {
		this.value = value;
	}

	static {
		Field[] fields = StatusCode.class.getFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(CodeAnnot.class)) {
				hMap.put(field.getName(), field.getAnnotation(CodeAnnot.class)
						.value());
			}
		}
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return hMap.get(this.toString());
	}
	
	public static void main(String[] args) {
		System.out.println("###"+ StatusCode.FAIL.getDescription());
		StatusCode.OK.getValue();
	}
}
