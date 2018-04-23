import com.nascent.utils.dao.LoggerDao;
import com.nascent.utils.service.LoggerService;
import org.junit.Test;

/**
 * PACKAGE_NAME
 *
 * @Author guiping.Qiu
 * @Date 2018/3/22
 */
public class AppTest {


    @Test
    public void test(){
        LoggerDao loggerDao = new LoggerDao();
        LoggerService loggerService = new LoggerService();
        loggerDao.test();
        loggerService.test();
    }
}
