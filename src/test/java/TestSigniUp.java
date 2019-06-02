//import com.nemk.educator.model.Course;
//import com.nemk.educator.model.User;
//import com.nemk.educator.repository.UserRepository;
//import com.nemk.educator.service.MainService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestSigniUp {
//    @Autowired
//    private MainService mainService;
//
//    @Before
//    public void initDB(){
//        User user = new User("Dmytro", "Korish@gmail.com", "111");
//        mainService.createUser(user);
//
//        Course course = new Course("Java ", "lalalalallalallaalallallal");
//        mainService.createCourse(course,user);
//
//    }
//
//    @Test
//    public void testUser(){
//        User user = mainService.findOne("Korish@gmail.com");
//        System.out.println(user);
//    }
//}
