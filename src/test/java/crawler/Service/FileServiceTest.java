package crawler.Service;

import com.sandbox.crawler.Service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by Ouasmine on 16/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    public void readTxtFile() throws Exception {
        assertNotEquals(0, fileService.readTxtFileByUrl("http://www.3suisses.fr/robots.txt").size());
    }

}