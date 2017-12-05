package com.x.validate;

<<<<<<< HEAD:src/main/java/com/qunar/x/validate/XValidators.java
import com.qunar.x.validate.base.BaseValidator;
import com.qunar.x.validate.base.XValidator;

import java.util.HashMap;
=======
import com.x.validate.base.XValidator;
>>>>>>> 247c0dda2e0e35fe7dcae2569cb06e370435d6b7:src/main/java/com/x/validate/XValidators.java

public class XValidators {
    public static XValidator commonValidator(){
        return BaseValidator.generateValidator();
    }
}
