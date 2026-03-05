import com.example.bntu.models.*;

import javax.persistence.*;
import java.util.List;

public class ttt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private int group;
    private String diploma;
    private String lessonMarks;

}
