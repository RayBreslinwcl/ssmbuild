import com.ray.pojo.Books;
import com.ray.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {

    @Test
    public void TestQueryAll(){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = (BookService) context.getBean("BookServiceImpl");
        for (Books books : bookServiceImpl.queryAllBook()) {
            System.out.println(books);
        }

        /**
         * 成功返回
         * Books(bookID=1, bookName=Java, bookCounts=1, detail=从入门到放弃)
         * Books(bookID=2, bookName=MySQL, bookCounts=10, detail=从删库到跑路)
         * Books(bookID=3, bookName=Linux, bookCounts=5, detail=从进门到进牢)
         */
    }
}
