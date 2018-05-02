package example.web;

/**
 * Created by AB053735 on 2018/5/2.
 */
public class test01 {

    public static void main(String[] args){  // 主方法

        //"www.baidu.com/admin/test" 必须是后面
        String testUrl="http://localhost:9999/admin/testa";

        //String regex = "^(?:https?://)?[\\w]{1,}(?:\\.?[\\w]{1,})+[\\w-_/?&=#%:]*$";
       //String regex = "^(?:https?://)?[\\w]{1,}(?:\\.?[\\w]{1,})+(?:admin){1}[\\w-_/?&=#%:]*$";


        String regex = "^(?:https?://)?[\\w]{1,}(?:\\.?[\\w]{1,})+(?:\\:[0-9]{4})?/{1}(?:admin){1}[\\w-_/?&=#%:]*$";

        if(testUrl.matches(regex)){
            System.out.println(testUrl + "是个合法的url!");
        }
        else{
            System.out.println(testUrl + "是个非法的url");
        }

    }

}
